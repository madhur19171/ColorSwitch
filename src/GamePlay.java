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
    private boolean show = false;

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


        this.stag = new Stag(this, ball, 1);
        stag.initialize(root, 0);

        Stag stag2 = new Stag(this, ball, 2);
        stag2.initialize(root, -800);

        Stag stag3 = new Stag(this, ball, 3);
        stag3.initialize(root, -1600);

        Stag stag4 = new Stag(this, ball, 1);
        stag4.initialize(root, -2400);

        root.getChildren().add(ball.getBall());

        stage.setScene(scene);
        stage.show();
    }


    public Scene getScene() {
        return scene;
    }

    public Stage getStage() {
        return stage;
    }

    void increaseScore() {
        score++;
        score_label.setText("Score: " + score);
    }
    
    public int getLevel() {
		return level;
	}
	
	public int getScore() {
		return score;
	}
	
}
