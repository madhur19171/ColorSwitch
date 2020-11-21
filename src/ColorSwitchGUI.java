import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ColorSwitchGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        ColorList colorList = new ColorList();
        Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        primaryStage.getIcons().add(new Image("images/ColorSwitchIcon.png"));
        primaryStage.setTitle("ColorSwitch");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        System.out.println("Surprise Mother Fucker!");
    }
}
