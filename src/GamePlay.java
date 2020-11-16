import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GamePlay {
    private Ball ball;
    private int level;
    private int score;
    private Scene scene;
    private Stage stage;

    public void initialize(Stage stage) throws IOException {
        this.stage = stage;
        AnchorPane root = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
        scene = new Scene(root);
        int ballOffsetY = 50;
        ball = new Ball(20, 3, new Cordinate(root.getPrefWidth() / 2, root.getPrefHeight() - ballOffsetY));
        root.getChildren().add(ball.getBall());
        stage.setScene(scene);
    }
}
