import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;

public class NewUserInputController {

    private static GamePlay gamePlay;

    @FXML
    private TextField username_input;

    @FXML
    private Button start_button;

    @FXML
    void StartNewGame(MouseEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
        newGame(root.getPrefHeight());
    }

    public String getUserName() {
        return username_input.getText();
    }

    public static GamePlay getGamePlay() {
        return gamePlay;
    }

    public static void setGamePlay(GamePlay gamePlay) {
        NewUserInputController.gamePlay = gamePlay;
    }

    private void newGame(double ballY) throws IOException {
        Stage stage = (Stage) start_button.getScene().getWindow();
        if (gamePlay != null)
            gamePlay.killGame();
        gamePlay = new GamePlay();
        gamePlay.setUser_name(getUserName());
        gamePlay.initialize(stage, ballY);
    }
}
