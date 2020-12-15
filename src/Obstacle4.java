import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Obstacle4 extends Obstacle {

    private final Group obstacle;
    private final AnimationTimer animationTimer;

    public Obstacle4(double velocity, Cordinate position) throws IOException {

        super(velocity, position, ColorList.getColorList().get(0));
        // TODO Auto-generated constructor stub
        AnchorPane root = (FXMLLoader.load(getClass().getResource("Obstacle.fxml")));
        obstacle = (Group) root.lookup("#cross_obstacle");


        obstacle.setTranslateX(cordinate.getX());
        obstacle.setTranslateY(cordinate.getY());

        final Group cross1 = (Group) obstacle.getChildren().get(0);
        final Group cross2 = (Group) obstacle.getChildren().get(1);

        System.out.println(obstacle.getTranslateX() + " " + obstacle.getTranslateY());
        System.out.println(obstacle.getLayoutX() + " " + obstacle.getLayoutY());

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
                cross1.setRotate(180-angle);
                cross2.setRotate(angle);
            }
        };
        animationTimer.start();

    }

    public Group getObstacle() {
        return obstacle;
    }

    @Override
    boolean checkVicinity(Ball ball) {
        for (Node line : ((Group) obstacle.getChildren().get(0)).getChildren()) {
            Shape intersect = Shape.intersect((Line) line, ball.getBall());
            if ((intersect.getBoundsInLocal().getWidth() != -1) && !((Line) line).getStroke().equals(ball.getBall().getFill())) {
                return true;
            }
        }
        for (Node line : ((Group) obstacle.getChildren().get(1)).getChildren()) {
            Shape intersect = Shape.intersect((Line) line, ball.getBall());
            if ((intersect.getBoundsInLocal().getWidth() != -1) && !((Line) line).getStroke().equals(ball.getBall().getFill())) {
                return true;
            }
        }
        return false;
    }



    @Override
    public Cordinate getCordinate() {
        return cordinate;
    }


    @Override
    public void setCordinate(Cordinate cordinate) {
        this.cordinate = cordinate;
        obstacle.setTranslateX(cordinate.getX());
        obstacle.setTranslateY(cordinate.getY());
    }

    @Override
    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }
}
