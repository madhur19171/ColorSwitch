import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
        gamePlay.setUser_name(getUserName());
        gamePlay.initialize(stage);
    }

    public String getUserName() throws IOException {
//        AnchorPane root = FXMLLoader.load(getClass().getResource("NewUserInput.fxml"));
//        username_input = (TextField) root.lookup("#username_input");
        return username_input.getText();
    }

}
