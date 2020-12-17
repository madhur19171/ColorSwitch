import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoadGameSceneController {

    @FXML
    private Label user_names_label;

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
    void madhurClicked(MouseEvent event) throws IOException, ClassNotFoundException {
        loadGame(madhur.getText());
    }


    @FXML
    void muditClicked(MouseEvent event) throws IOException, ClassNotFoundException {
        loadGame(mudit.getText());
    }

    void loadGame(String name) throws ClassNotFoundException, IOException {
        SaveGame obj = new SaveGame();
        GameState p = obj.loadGame(name);        //Pass the User name as argument to load
        if (p != null) {                                    //if no records of a user is found the p object will be null
            System.out.println(p.getUser_name());
            System.out.println(p.getBallY());
            System.out.println(p.getLevel());
            Stage stage = (Stage) user_names_label.getScene().getWindow();
            if (NewUserInputController.getGamePlay() != null)
                NewUserInputController.getGamePlay().killGame();
            NewUserInputController.setGamePlay(p.getGamePlay());
            NewUserInputController.getGamePlay().setUser_name(p.getUser_name());
            NewUserInputController.getGamePlay().initialize(stage, 700);
        }
    }

    void initialize(Stage stage) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("LoadGameScene.fxml"));

        BufferedReader objReader = null;
        try {
            String strCurrentLine;

            objReader = new BufferedReader(new FileReader("src/Names.txt"));

            int i = 3;
            while ((strCurrentLine = objReader.readLine()) != null) {
                ((Label) ((root.getChildren()).get(i++))).setText(strCurrentLine);
                System.out.println(strCurrentLine);
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {
                if (objReader != null)
                    objReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        stage.setScene(new Scene(root));
    }
}
