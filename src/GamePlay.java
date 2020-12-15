import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;


import java.io.IOException;
import java.util.*;

public class GamePlay {
    public Label score_label;
    private Ball ball;
    private int level;
    private int score;
    private Scene scene;
    private Stage stage;
    private Stag stag;
    private String user_name;
    private ArrayList<Stag> stagArrayList;
    HashMap<String, Boolean> currentlyActiveKeys = new HashMap<>();
    private boolean show = false;

    //Since This class has provided its own default constructor which will be called by FXML loader,
    //We need to initialize the instance variables using initialize class.

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public int getScore() {
        return score;
    }

    public void initialize(Stage stage) throws IOException {
        this.stage = stage;
        AnchorPane root = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
        scene = new Scene(root);

        score_label = (Label) root.lookup("#score_label");


        int ballOffsetY = 50;   //Initial offset of ball from the ground
        //Creating Ball Object
        ball = new Ball(20, 15, new Cordinate(root.getPrefWidth() / 2, root.getPrefHeight() - ballOffsetY));

        stagArrayList = new ArrayList<>(0);

        this.stag = new Stag(this, ball, 1);
        stag.initialize(root, 0);
        stagArrayList.add(stag);
        GamePlay currentObject = this;

        new AnimationTimer() {
            int i = 1;
            Random random = new Random();

            @Override
            public void handle(long now) {
                //System.out.println(stagArrayList.get(stagArrayList.size() - 1).getAvatarGroup().getTranslateY());
                if (stagArrayList.get(stagArrayList.size() - 1).getAvatarGroup().getTranslateY() > 0) {
                    try {
                        i = random.nextInt(3);
                        Stag newStage = new Stag(currentObject, ball, ++i);
//                        System.out.println(i);
                        stagArrayList.add(newStage);
                        newStage.initialize(root, (int) stagArrayList.get(stagArrayList.size() - 1).getAvatarGroup().getTranslateY() - 800);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


//        Stag stag2 = new Stag(this, ball, 2);
//        stag2.initialize(root, -800);
//
//        Stag stag3 = new Stag(this, ball, 3);
//        stag3.initialize(root, -1600);
//
//        Stag stag4 = new Stag(this, ball, 1);
//        stag4.initialize(root, -2400);

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
}
