import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

import java.io.IOException;

public class Obstacle2 extends Obstacle {

    private final Group obstacle;
    private final AnimationTimer animationTimer;

    public Obstacle2(int size, double velocity, Cordinate position) throws IOException {
        super(velocity, position, ColorList.getColorList().get(0));
        AnchorPane root = (FXMLLoader.load(getClass().getResource("Obstacle.fxml")));

        obstacle = (Group) root.lookup("#circle_obstacle");
        obstacle.setTranslateX(cordinate.getX());
        obstacle.setTranslateY(cordinate.getY());
        obstacle.setScaleX(size);
        obstacle.setScaleY(size);
        System.out.println(obstacle.getScaleX());

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
        for (Node arc : obstacle.getChildren()) {
            Shape intersect = Shape.intersect((SVGPath) arc, ball.getBall());
            if ((intersect.getBoundsInLocal().getWidth() != -1) && !((SVGPath) arc).getFill().equals(ball.getBall().getFill())) {
                return true;
            }
        }
        return false;
    }
}
