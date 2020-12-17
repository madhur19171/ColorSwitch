import javafx.animation.RotateTransition;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;

import javafx.animation.Animation;

import javafx.animation.ScaleTransition;
import javafx.animation.RotateTransition;

import javax.swing.*;

public class PauseSceneController{

    @FXML
    private Label user_name;

    @FXML
    private Label score;
    @FXML
    private ImageView resumeBtn;
    @FXML
    private ImageView restartBtn;
    @FXML
    private ImageView saveBtn;

    private Stage stage;

    public static Stag currentStag;
    private GameState gameState;
    @FXML
    public void initialize(Stage stage, GamePlay gamePlay, Stag currentStag,GameState game) throws IOException {
        this.gameState=game;
        PauseSceneController.currentStag = currentStag;
        //System.out.println(currentStag);
        AnchorPane root = FXMLLoader.load(getClass().getResource("PauseScene.fxml"));
        this.stage = stage;
        stage.setScene(new Scene(root));

        user_name = (Label) root.lookup("#user_name");
        score = (Label) root.lookup("#score");

        resumeBtn = (ImageView) root.lookup("#resumeBtn");
        restartBtn = (ImageView) root.lookup("#restartBtn");
        saveBtn = (ImageView) root.lookup("#saveBtn");

        user_name.setText(gamePlay.getUser_name());
        score.setText("Score: " + gamePlay.getScore());

        RotateTransition rsm = new RotateTransition(Duration.seconds(2), resumeBtn);
        RotateTransition rst = new RotateTransition(Duration.seconds(2), restartBtn);
        ScaleTransition sbt = new ScaleTransition(Duration.seconds(1), saveBtn);
        rsm.setAutoReverse(false);
        rst.setAutoReverse(false);
        sbt.setAutoReverse(true);
        rsm.setCycleCount(Animation.INDEFINITE);
        rst.setCycleCount(Animation.INDEFINITE);
        sbt.setCycleCount(Animation.INDEFINITE);
        rsm.setByAngle(360);

        rst.setByAngle(-360);

        sbt.setFromX(1);
        sbt.setToX(1.1);
        sbt.setFromY(1);
        sbt.setToY(1.1);

        rsm.play();
        sbt.play();
        rst.play();

    }

    public void resume() {
        System.out.println("Resumed");
        currentStag.resume();
    }

    @FXML
    void resumeClicked(MouseEvent event) {
        if (NewUserInputController.getGamePlay().isPaused())
            resume();
        else {
            if (NewUserInputController.getGamePlay().getScore() >= 5) {
                NewUserInputController.getGamePlay().setScore(NewUserInputController.getGamePlay().getScore() - 5);
                resume();
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Sorry You Need At Least 5 points To Revive.", "Revival", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @FXML
    void restartClicked(MouseEvent event) throws IOException {
        System.out.println("Restarted");
        Stage stage = (Stage) user_name.getScene().getWindow();
        NewUserInputController.getGamePlay().killGame();
        NewUserInputController.setGamePlay(new GamePlay());
        NewUserInputController.getGamePlay().setUser_name(user_name.getText());
        AnchorPane root = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
        NewUserInputController.getGamePlay().initialize(stage, root.getPrefHeight());
    }

    @FXML
    void saveClicked(MouseEvent event) throws IOException, ClassNotFoundException {
        new SaveGame(gameState).saveIntoFile();
        JOptionPane.showMessageDialog(new JFrame(), "Successfully Saved Your Game", "Saved", JOptionPane.INFORMATION_MESSAGE);
        Stage stage = (Stage) resumeBtn.getScene().getWindow();
        AnchorPane root = FXMLLoader.load(getClass().getResource("LoadGameScene.fxml"));
        stage.setScene(new Scene(root));
    }
}
