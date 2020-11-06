import javafx.scene.paint.Color;

public class Ball extends Avatar {

    private int size;
    private double velocity;
    private double acceleration;

    public Ball(int size, double acceleration, Cordinate position, Color color) {
        super(position, color);
        this.size = size;
        this.acceleration = acceleration;
        velocity = 0.0;
    }

    @Override
    public Cordinate getCordinate() {
        return cordinate;
    }

    @Override
    public void setCordinate(Cordinate cordinate) {
        this.cordinate = cordinate;
    }

    @Override
    public boolean checkVicinity(Ball ball) {
        return false;
    }

    public void jump() {
        velocity += 5;
    }

    public void freeFall() {
        cordinate.incY(velocity);
        velocity -= acceleration;
    }
}
