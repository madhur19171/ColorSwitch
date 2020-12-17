import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.Serializable;

public class LoadGameSceneController {

    @FXML
    private Label madhur;

    @FXML
    private Label raghav;

    @FXML
    private Label arjun;

    @FXML
    private Label mudit;

    @FXML
    private Label rachit;

    @FXML
    private Label manan;

    @FXML
    void madhurClicked(MouseEvent event) {
        System.out.println("Clicked");
        madhur.setText("Kumar");
    }

    @FXML
    void muditClicked(MouseEvent event) {

    }

}
