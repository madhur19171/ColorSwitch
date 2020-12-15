import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public abstract class Obstacle extends Avatar {

    protected double velocity;
    protected Group obstacle;

    public Obstacle(double velocity, Cordinate position, Color color) {
        super(position, color);
        this.velocity = velocity;
    }

    @Override
    abstract boolean checkVicinity(Ball ball);

    abstract AnimationTimer getAnimationTimer();

    public Group getObstacle() {
        return obstacle;
    }
}
