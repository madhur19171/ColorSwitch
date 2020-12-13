import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import java.io.IOException;

public class Obstacle1 extends Obstacle {


    private final AnimationTimer animationTimer;

    public Obstacle1(int size, double velocity, Cordinate position) throws IOException {
        super(velocity, position, ColorList.getColorList().get(0));
        AnchorPane root = (FXMLLoader.load(getClass().getResource("Obstacle.fxml")));

        obstacle = (Group) root.lookup("#square_obstacle");
        obstacle.setLayoutX(cordinate.getX());
        obstacle.setLayoutY(cordinate.getY());
        obstacle.setScaleX(size);
        obstacle.setScaleY(size);


        animationTimer = new AnimationTimer() {
            long lastStamp = 0;
            double angle = 0;

            @Override
            public void handle(long now) {
                if (lastStamp == 0) {
                    lastStamp = now;
                    return;
                }
                double elapsed = (now - lastStamp) / 1000000000.0;
                lastStamp = now;
                angle += velocity * elapsed;
                obstacle.setRotate(angle);
            }
        };
        animationTimer.start();
    }

    @Override
    public Group getObstacle() {
        return obstacle;
    }

    @Override
    public Cordinate getCordinate() {
        return null;
    }

    @Override
    public void setCordinate(Cordinate cordinate) {

    }

    @Override
    boolean checkVicinity(Ball ball) {
        for (Node line : obstacle.getChildren()) {
            Shape intersect = Shape.intersect((Line) line, ball.getBall());
            if ((intersect.getBoundsInLocal().getWidth() != -1) && !((Line) line).getStroke().equals(ball.getBall().getFill())) {
                return true;
            }
        }
        return false;
    }
}
