import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;

public class StartSceneController {

    @FXML
    private Button new_game_button;

    @FXML
    private Button load_game_button;

    @FXML
    private Group stars;

    @FXML
    private Button exit_game;
    

    @FXML
    void loadGame(MouseEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("LoadGameScene.fxml"));
        Stage stage = (Stage) load_game_button.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void newGame(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("NewUserInput.fxml"));
        Stage stage = (Stage) new_game_button.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void exit(MouseEvent event) {
        Stage stage = (Stage) load_game_button.getScene().getWindow();
        stage.close();
    }
}