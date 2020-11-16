import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Ball extends Avatar {

    private int size;
    private double velocity;
    private final double acceleration;
    private final Circle ball;
    AnimationTimer animationTimer;//After every jump, new Animation timer needs to be created.

    public Ball(int size, double acceleration, Cordinate position) {
        super(position, ColorList.getColorList().get(0));
        this.size = size;
        this.acceleration = acceleration;
        velocity = 0.0;
        ball = new Circle(position.getX(), position.getY(), size);
        ball.setStrokeWidth(0.0);//Border width of ball
        ball.setFill(color);//Color of ball.
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
        velocity = 350; //Velocity of every jump is 350
        System.out.println("JUMP"); //Debugging purposes

        if (animationTimer != null) //If the animation timer already exists, stop it.
            animationTimer.stop();

        animationTimer = new AnimationTimer() {
            long lastStamp = 0;//Last time when the handle() method was called.

            @Override
            public void handle(long now) {
                if (lastStamp == 0) {//For initializing the lastStamp at the start of animationTimer.
                    lastStamp = now;
                    return;
                }
                double elapsed = (now - lastStamp) / 1000000000.0;//How much time elapsed between
                                                                    // this frame and previous frame in seconds.
                lastStamp = now;//For next frame.

                cordinate.incY(velocity * elapsed);//Update new Cordinate for the class
                setCordinate(cordinate);//Update the new cordinate for the ball
                freeFall();//After jumping, the ball needs to do free fall under gravity
            }
        };
        animationTimer.start();
    }

    public void freeFall() {
        //cordinate.incY(velocity);
        velocity -= acceleration;//Decelerating the Ball.
    }

}
