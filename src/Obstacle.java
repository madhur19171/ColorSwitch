import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public abstract class Obstacle extends Avatar {

    protected double velocity;
    protected double storeVelocity;
    protected Group obstacle;
    protected final Obstacle thisObstacle;

    public Obstacle(double velocity, Cordinate position, Color color) {
        super(position, color);
        this.velocity = velocity;
        this.storeVelocity = velocity;
        thisObstacle = this;
    }

    @Override
    abstract boolean checkVicinity(Ball ball);

    abstract AnimationTimer getAnimationTimer();

    public Group getObstacle() {
        return obstacle;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    protected void stop() {
        this.storeVelocity = this.velocity;
        this.velocity = 0;
    }

    protected void start() {
        this.velocity = this.storeVelocity;
    }
}
