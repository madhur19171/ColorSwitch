import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.io.IOException;

public class Switch extends Avatar {

    private final Group switches;
    private final AnimationTimer animationTimer;

    public Switch(int size, Cordinate cordinate) throws IOException {
        super(cordinate, Color.ALICEBLUE);
        AnchorPane root = (FXMLLoader.load(getClass().getResource("Switch.fxml")));
        ObservableList<Node> childrenList = root.getChildren();
        switches = (Group) childrenList.get(0);
        for (Node arc : switches.getChildren()) {
            ((Arc) arc).setRadiusX(size);
            ((Arc) arc).setRadiusY(size);
        }
        switches.setLayoutX(cordinate.getX());
        switches.setLayoutY(cordinate.getY());

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
                angle += 100 * elapsed;
                switches.setRotate(angle);
            }
        };
        animationTimer.start();
    }


    public void switchColor(Ball ball) {
        for (Color color : ColorList.getColorList())
            if (!ball.getColor().equals(color)) {
                ball.setColor(color);
                return;
            }
    }

    public Group getSwitches() {
        return switches;
    }

    @Override
    public Cordinate getCordinate() {
        return cordinate;
    }

    @Override
    public void setCordinate(Cordinate cordinate) {
        this.cordinate = cordinate;
    }

    public boolean checkVicinity(Ball ball) {
//        if (ball.getBall().localToScreen(ball.getBall().getBoundsInParent()).intersects(switches.localToScreen(switches.getBoundsInParent()))) {
//            System.out.println("Switch");
//            return true;
//        }

        for (Node arc : switches.getChildren()) {
            Shape intersect = Shape.intersect((Arc) arc, ball.getBall());
            if ((intersect.getBoundsInLocal().getWidth() != -1)) {
                return true;
            }
        }
        return false;
    }

    public void killSwitch() {
        animationTimer.stop();
    }
}
