import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Avatar {

    private int size;
    private double velocity;
    private final double acceleration;
    private final Circle ball;

    public Ball(int size, double acceleration, Cordinate position) {
        super(position, ColorList.getColorList().get(0));
        this.size = size;
        this.acceleration = acceleration;
        velocity = 0.0;
        ball = new Circle(position.getX(), position.getY(), size);
        ball.setStrokeWidth(0.0);
        ball.setFill(color);
    }

    public Circle getBall() {
        return ball;
    }

    @Override
    public Cordinate getCordinate() {
        return cordinate;
    }

    @Override
    public void setCordinate(Cordinate cordinate) {
        this.cordinate = cordinate;
        ball.setCenterX(cordinate.getX());
        ball.setCenterY(cordinate.getY());
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
