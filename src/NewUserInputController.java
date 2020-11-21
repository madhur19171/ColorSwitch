import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class NewUserInputController {

    @FXML
    private TextField username_input;

    @FXML
    private Button start_button;

    @FXML
    void StartNewGame(MouseEvent event) throws IOException {
        Stage stage = (Stage) start_button.getScene().getWindow();
        GamePlay gamePlay = new GamePlay();
        gamePlay.initialize(stage);
    }

}
