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
    void madhurClicked(MouseEvent event) throws ClassNotFoundException {
        SaveGame obj=new SaveGame();
        GameState p=obj.loadGame("");        //Pass the User name as argument to load
        if(p!=null){                                    //if no records of a user is found the p object will be null
        System.out.println(p.getUser_name());
        System.out.println(p.getBallY());
        System.out.println(p.getLevel());}
    }

    @FXML
    void muditClicked(MouseEvent event) {

    }

}
