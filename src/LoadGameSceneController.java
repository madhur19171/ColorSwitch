import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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
    void madhurClicked(MouseEvent event) throws ClassNotFoundException {		// calls loadgame from SaveGame class!!!
        GameState game=new SaveGame().loadGame("kumar");
        madhur.setText("Kumar");
    }

    @FXML
    void muditClicked(MouseEvent event) {

    }

}
