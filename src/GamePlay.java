import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;


import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class GamePlay {
    public Label score_label;
    private Ball ball;
    private int level;
    private int score;
    private Scene scene;
    private Stage stage;
    private Stag stag;
    private String user_name;
    HashMap<String, Boolean> currentlyActiveKeys = new HashMap<>();
    private boolean show=false;
    //Since This class has provided its own default constructor which will be called by FXML loader,
    //We need to initialize the instance variables using initialize class.
    public void initialize(Stage stage) throws IOException {
        this.stage = stage;
        AnchorPane root = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
        scene = new Scene(root);

        score_label = (Label) root.lookup("#score_label");
      
      
        int ballOffsetY = 50;   //Initial offset of ball from the ground
        //Creating Ball Object
        ball = new Ball(20, 15, new Cordinate(root.getPrefWidth() / 2, root.getPrefHeight() - ballOffsetY));


        this.stag = new Stag(this, ball);
        stag.initialize(root);

        stage.setScene(scene);
        stage.show();
    }


    public Scene getScene() {
        return scene;
    }

    public Stage getStage() {
        return stage;
    }
        scene.setOnKeyPressed(event -> {
            String codeString = event.getCode().toString();
            if (!currentlyActiveKeys.containsKey(codeString)) {
                currentlyActiveKeys.put(codeString, true);
            }
        });


        scene.setOnKeyReleased(event ->
                currentlyActiveKeys.remove(event.getCode().toString())
        );

        AnchorPane pauseScene = FXMLLoader.load(getClass().getResource("PauseScene.fxml"));
        //This timer Detects the Key Press Events, And Start vicinity check. Can be extended.
        new AnimationTimer() {
            boolean switched = false;
            boolean stared = false;
            boolean collision = false;
            boolean overYet=false;					// To check whether the burst animation is overYet or not!!
            @Override
            //Left and Right arrow keys will pause the game
            //Up and Down arrow keys will jump the Ball
            public void handle(long now) {
            	
                if (removeActiveKey("LEFT") || removeActiveKey("RIGHT")) {
                    try {
                        new PauseSceneController().initialize();

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
                    score++;
                    score_label.setText("Score:" + score);
                }
                if (!switched && switches.checkVicinity(ball)) {
                    switches.switchColor(ball);
                    switches.killSwitch();//Stops the animation
                    switched = true;//Once switch has been touched, no more switching.
                    root.getChildren().remove(switches.getSwitches());//Removes from the scene
                }

                if (!collision && obstacle1.checkVicinity(ball)) {
                    System.out.println("OBSTACLE1 COLLISION");
                    collision = true;
                    ball.getBall().setVisible(false);
                    GamePlay p=new GamePlay();
                    ball.playBurst(root);
                    
                    Timer timer=new Timer();				//Introduced a new Timer to set the variable overYet=True after 1 sec, because 1 sec is required for the burst animation to get over
                    
                    timer.schedule(new TimerTask() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							overYet=true;
						}
                    	
                    }, 1200);
                    
                    
                    
                }


                if (!collision && obstacle2.checkVicinity(ball)) {
                    System.out.println("OBSTACLE2 COLLISION");
                    collision = true;
                    ball.getBall().setVisible(false);
                    ball.playBurst(root);
                    
                    Timer timer=new Timer();				//Introduced a new Timer to set the variable overYet=True after 1.2 sec, because 1 sec is required for the burst animation to get over
                    
                    timer.schedule(new TimerTask() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							overYet=true;
						}
                    	
                    }, 1200);
                    
                    
                }
                
                if(overYet==true) {							// if overYet=true then put the next scene .
                	overYet=false;
                	stage.setScene(new Scene(pauseScene));
                    try {
            			new PauseSceneController().initialize();
            		} catch (IOException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
                }
                
                
            }
        }.start();

        stage.show();
        
    }
    
    
    
    private boolean removeActiveKey(String codeString) {
        Boolean isActive = currentlyActiveKeys.get(codeString);

    void increaseScore() {
        score++;
        score_label.setText("Score:" + score);
    }

}
