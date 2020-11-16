import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class StartSceneController {

    @FXML
    private Button new_game_button;

    @FXML
    private Button load_game_button;

    @FXML
    void loadGame(MouseEvent event) {

    }

    @FXML
    void newGame(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("NewUserInput.fxml"));
        Stage stage = (Stage) new_game_button.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

}