import javafx.scene.paint.Color;

public abstract class Obstacle extends Avatar{

    protected double velocity;

    public Obstacle(double velocity, Cordinate position, Color color) {
        super(position, color);
        this.velocity = velocity;
    }

    @Override
    abstract boolean checkVicinity(Ball ball);
}
