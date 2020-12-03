import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class Stag {
    private final GamePlay mainGame;
    private Obstacle obstacle;
    private Switch switches;
    private Star star;
    private final Ball ball;
    private final Scene scene;
    private final Stage stage;
    HashMap<String, Boolean> currentlyActiveKeys = new HashMap<>();

    public Stag(GamePlay mainGame, Ball ball) {
        this.mainGame = mainGame;
        this.ball = ball;
        scene = mainGame.getScene();
        stage = mainGame.getStage();
    }

    public void initialize(AnchorPane root) throws IOException {
        //Creating Star object for initial development purposes only.
        //All Avatar objects other than Ball will be created in Stag Class.
        star = new Star(40, new Cordinate(root.getPrefWidth() / 2, root.getPrefHeight() - 400));

        switches = new Switch(25, new Cordinate(root.getPrefWidth() / 2, root.getPrefHeight() - 800));

        obstacle = new Obstacle1(40, star.getCordinate());
        obstacle.getObstacle().setScaleX(0.9);
        obstacle.getObstacle().setScaleY(0.9);

        //Obstacle2 obstacle2 = new Obstacle2(100, 40, switches.getCordinate());

        //Adding the Ball and star to the pane
        root.getChildren().add(ball.getBall());
        root.getChildren().add(star.getStar());
        root.getChildren().add(switches.getSwitches());
        root.getChildren().add(obstacle.getObstacle());
        //root.getChildren().add(obstacle2.getObstacle());


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
        new AnimationTimer() {
            boolean switched = false;
            boolean stared = false;
            boolean collision = false;

            @Override
            //Left and Right arrow keys will pause the game
            //Up and Down arrow keys will jump the Ball
            public void handle(long now) {
                if (removeActiveKey("LEFT") || removeActiveKey("RIGHT")) {
                    try {
                        pauseSceneController.initialize(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                if (removeActiveKey("UP")) {
                    ball.jump();
                }

                if (removeActiveKey("DOWN")) {
                    ball.jump();
                }
                //If Ball has touched the star, the star will disappear.
                //The animation has to be added as well.
                if (!stared && star.checkVicinity(ball)) {
                    stared = true;//Once stared, no more score increment.
                    star.killStar();//Stops The animation
                    root.getChildren().remove(star.getStar());//Removes from the scene
                    mainGame.increaseScore();
                }
                if (!switched && switches.checkVicinity(ball)) {
                    switches.switchColor(ball);
                    switches.killSwitch();//Stops the animation
                    switched = true;//Once switch has been touched, no more switching.
                    root.getChildren().remove(switches.getSwitches());//Removes from the scene
                }

                if (!collision && obstacle.checkVicinity(ball)) {
                    System.out.println("OBSTACLE1 COLLISION");
                    collision = true;
                    try {
                        pauseSceneController.initialize(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


//                if (!collision && obstacle2.checkVicinity(ball)) {
//                    System.out.println("OBSTACLE2 COLLISION");
//                    collision = true;
//                    try {
//                        pauseSceneController.initialize(stage);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }.start();

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
}
