import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class GamePlay {
    private Ball ball;
    private int level;
    private int score;
    private Scene scene;
    private Stage stage;
    HashMap<String, Boolean> currentlyActiveKeys = new HashMap<>();

    //Since This class has provided its own default constructor which will be called by FXML loader,
    //We need to initialize the instance variables using initialize class.
    public void initialize(Stage stage) throws IOException {
        this.stage = stage;
        AnchorPane root = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
        scene = new Scene(root);
        int ballOffsetY = 50;   //Initial offset of ball from the ground

        //Creating Ball Object
        ball = new Ball(20, 15, new Cordinate(root.getPrefWidth() / 2, root.getPrefHeight() - ballOffsetY));

        //Creating Star object for initial development purposes only.
        //All Avatar objects other than Ball will be created in Stag Class.
        Star star = new Star(40, new Cordinate(root.getPrefWidth() / 2, root.getPrefHeight() - 400));


        Switch switches = new Switch(25, new Cordinate(root.getPrefWidth() / 2, root.getPrefHeight() - 700));

        //Adding the Ball and star to the pane
        root.getChildren().add(ball.getBall());
        root.getChildren().add(star.getStar());
        root.getChildren().add(switches.getSwitches());
        stage.setScene(scene);


        scene.setOnKeyPressed(event -> {
            String codeString = event.getCode().toString();
            if (!currentlyActiveKeys.containsKey(codeString)) {
                currentlyActiveKeys.put(codeString, true);
            }
        });
        scene.setOnKeyReleased(event ->
                currentlyActiveKeys.remove(event.getCode().toString())
        );

        //This timer Detects the Key Press Events, And Start vicinity check. Can be extended.
        new AnimationTimer() {
            boolean switched = false;
            boolean stared = false;

            @Override
            //Left and Right arrow keys will pause the game
            //Up and Down arrow keys will jump the Ball
            public void handle(long now) {
                if (removeActiveKey("LEFT")) {
                    //pause
                }

                if (removeActiveKey("RIGHT")) {
                    //pause
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
                }
                if (!switched && switches.checkVicinity(ball)) {
                    switches.switchColor(ball);
                    switches.killSwitch();//Stops the animation
                    switched = true;//Once switch has been touched, no more switching.
                    root.getChildren().remove(switches.getSwitches());//Removes from the scene
                }
            }
        }.start();

        stage.show();
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
