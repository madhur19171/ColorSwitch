import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Stag{

    private final GamePlay mainGame;
    private Obstacle obstacle;
    private Switch switches;
    private Star star;
    private final Ball ball;
    private final Scene scene;
    private final Stage stage;
    private final Group avatarGroup;
    private final int obs;
    private int index;
    private Stag thisStag;
    private AnimationTimer animationTimer;
    private static ArrayList<Group> avatarGroupArray = new ArrayList<>();
    HashMap<String, Boolean> currentlyActiveKeys = new HashMap<>();

    public Stag(GamePlay mainGame, Ball ball, int obs) {
        this.mainGame = mainGame;
        this.ball = ball;
        scene = mainGame.getScene();
        stage = mainGame.getStage();
        avatarGroup = new Group();
        this.obs = obs;
        thisStag = this;
        avatarGroupArray.add(avatarGroup);
    }

    public Group getAvatarGroup() {
        return avatarGroup;
    }

    public void initialize(AnchorPane root, int translateGroup, int index) throws IOException {
        //Creating Star object for initial development purposes only.

        //All Avatar objects other than Ball will be created in Stag Class.

        this.index = index;

        star = new Star(0.5, new Cordinate(root.getPrefWidth() / 2, root.getPrefHeight() - 800));

        switches = new Switch(25, new Cordinate(root.getPrefWidth() / 2, root.getPrefHeight() - 400));


        double increment = 1 + (mainGame.getLevel() - 1) / 3.0;
        //obstacle = new Obstacle4(40, star.getCordinate());
        switch (obs) {
            case 1:
                obstacle = new Obstacle1(1, 60 * increment, star.getCordinate());
                break;
            case 2:
                obstacle = new Obstacle2(2, 60 * increment, star.getCordinate());
                break;
            case 3:
                obstacle = new Obstacle4(60 * increment, switches.getCordinate());
                break;

        }


        //Adding the Ball and star to the pane
        avatarGroup.setTranslateY(translateGroup);
        avatarGroup.getChildren().add(star.getStar());
        avatarGroup.getChildren().add(switches.getSwitches());
        avatarGroup.getChildren().add(obstacle.getObstacle());
        root.getChildren().add(avatarGroup);

        scene.setOnKeyPressed(event -> {
            String codeString = event.getCode().toString();
            if (!currentlyActiveKeys.containsKey(codeString)) {
                currentlyActiveKeys.put(codeString, true);
            }
        });


        scene.setOnKeyReleased(event ->
                currentlyActiveKeys.remove(event.getCode().toString())
        );


        PauseSceneController pauseSceneController = new PauseSceneController();
        //This timer Detects the Key Press Events, And Start vicinity check. Can be extended.
        animationTimer = new AnimationTimer() {
            boolean switched = false;
            boolean stared = false;
            boolean collision = false;
            boolean overYet = false;                    // To check whether the burst animation is overYet or not!!
            boolean moved = false;
            boolean paused = false;

            @Override
            //Left and Right arrow keys will pause the game
            //Up and Down arrow keys will jump the Ball
            public void handle(long now) {


                if (removeActiveKey("LEFT") || removeActiveKey("RIGHT")) {
                    try {
                        paused = true;
                        pauseAll();
                        pauseSceneController.initialize(stage, mainGame, thisStag);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                if (removeActiveKey("UP") || removeActiveKey("DOWN")) {
                    //System.out.println("Jumped");
                    if (paused) {
                        for (Stag stagg : mainGame.getStagArrayList()) {
                            stagg.getObstacle().start();
                        }
                        paused = false;
                        collision = false;
                    }
                    ball.jump(avatarGroupArray);
                }


                //If Ball has touched the star, the star will disappear.
                //The animation has to be added as well.
                if (!stared && star.checkVicinity(ball)) {
                    stared = true;//Once stared, no more score increment.
                    star.killStar();//Stops The animation
                    avatarGroup.getChildren().remove(star.getStar());//Removes from the scene
                    mainGame.increaseScore();
                }
                if (!switched && switches.checkVicinity(ball)) {
                    switches.switchColor(ball);
                    switches.killSwitch();//Stops the animation
                    switched = true;//Once switch has been touched, no more switching.
                    avatarGroup.getChildren().remove(switches.getSwitches());//Removes from the scene
                }

                if (!collision && ball.getCordinate().getY() > scene.getHeight()) {
                    System.out.println("Ball Falls Down!");
                    collision = true;
                    ball.getBall().setVisible(false);
                    ball.playBurst(root);

                    Timer timer = new Timer();                //Introduced a new Timer to set the variable overYet=True after 1 sec, because 1 sec is required for the burst animation to get over

                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            overYet = true;
                        }

                    }, 1200);
                }

                if (!collision && obstacle.checkVicinity(ball)) {
                    System.out.println("OBSTACLE " + obs + "COLLISION");
                    collision = true;
                    ball.getBall().setVisible(false);
                    obstacle.stop();
                    ball.playBurst(root);

                    Timer timer = new Timer();                //Introduced a new Timer to set the variable overYet=True after 1 sec, because 1 sec is required for the burst animation to get over

                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            overYet = true;
                        }

                    }, 1200);


                }

//                if(ball.getCordinate().getY() > )

                if (overYet) {                            // if overYet=true then put the next scene .
                    overYet = false;
                    try {
                        pauseSceneController.initialize(stage, mainGame, thisStag);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        };

        animationTimer.start();

    }

    private boolean removeActiveKey(String codeString) {
        Boolean isActive = currentlyActiveKeys.get(codeString);

        if (isActive != null && isActive) {
            currentlyActiveKeys.put(codeString, false);
            return true;
        } else {
            return false;
        }
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }

    public void resume() {
        mainGame.setPaused(false);
        stage.setScene(mainGame.getScene());
        ball.getBall().setVisible(true);
        for (Stag stagg : mainGame.getStagArrayList()) {
            stagg.getAnimationTimer().start();
            //stagg.getObstacle().start();
        }
    }

    public void pauseAll() {
        mainGame.setPaused(true);
        System.out.println(mainGame.getStagArrayList().size());
        System.out.println("Paused\t" + index);
        ball.getBall().setVisible(false);
        ball.getAnimationTimer().stop();
        for (Stag stagg : mainGame.getStagArrayList()) {
            stagg.getAnimationTimer().stop();
            stagg.getObstacle().stop();
        }
    }

    public void killStag() {
        ball.getAnimationTimer().stop();
        animationTimer.stop();
        obstacle.getAnimationTimer().stop();
    }
    

}
