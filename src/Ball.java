import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.AnimationTimer;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Ball extends Avatar {

    private int size;
    private double velocity;
    private final double acceleration;
    private final Circle ball;
    //////////////////////////////
    private Circle burst_ball[]=new Circle[10];
    //////////////////////////////
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
        //System.out.println("JUMP"); //Debugging purposes

        if (animationTimer != null) //If the animation timer already exists, stop it.
            animationTimer.stop();

        animationTimer = new AnimationTimer() {
            long lastStamp = 0;//Last time when the handle() method was called.

            @Override
            public void handle(long now) {
//            	ball.setVisible(false);
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

    @Override
    public void setColor(Color color) {
        this.color = color;
        ball.setFill(color);
    }

    public void freeFall() {
        //cordinate.incY(velocity);
        velocity -= acceleration;//Decelerating the Ball.
    }
    
    public void playBurst(AnchorPane root) {
    	
    	Circle burst[] = new Circle[12];
    	ParallelTransition pt=new ParallelTransition();
    	for(int i=0;i<12;i++) {
    		burst[i]=new Circle(this.getCordinate().getX(),this.getCordinate().getY(),size/4);
    	}
    	burst[1].setFill(color.YELLOW);
    	burst[2].setFill(color.AQUA);
    	burst[3].setFill(color.DEEPPINK);
    	burst[4].setFill(color.DARKBLUE);
    	burst[5].setFill(color.YELLOW);
    	burst[6].setFill(color.AQUA);
    	burst[7].setFill(color.DEEPPINK);
    	burst[8].setFill(color.DARKBLUE);
    	burst[9].setFill(color.YELLOW);
    	burst[0].setFill(color.AQUA);
    	burst[10].setFill(color.DEEPPINK);
    	burst[11].setFill(color.AQUA);
    	//int add=0;
    	
    	
    	TranslateTransition up=new TranslateTransition();
    	up.setByY(-500);
    	up.setNode(burst[0]);
    	up.setDuration(Duration.seconds(1));
    	TranslateTransition down=new TranslateTransition();
    	down.setByY(500);
    	down.setNode(burst[1]);
    	down.setDuration(Duration.seconds(1));
    	TranslateTransition right=new TranslateTransition();
    	right.setByX(500);
    	right.setNode(burst[2]);
    	right.setDuration(Duration.seconds(1));
    	TranslateTransition left=new TranslateTransition();
    	left.setByX(-500);
    	left.setNode(burst[3]);
    	left.setDuration(Duration.seconds(1));
    	
    	TranslateTransition west1=new TranslateTransition();
    	west1.setByX(500);
    	west1.setByY(-220);
    	west1.setNode(burst[4]);
    	west1.setDuration(Duration.seconds(1));
    	
    	
    	TranslateTransition west2=new TranslateTransition();
    	west2.setByX(500);
    	west2.setByY(-480);
    	west2.setNode(burst[5]);
    	west2.setDuration(Duration.seconds(1));
    	
    	
    	TranslateTransition east2=new TranslateTransition();
    	east2.setByX(-500);
    	east2.setByY(-480);
    	east2.setNode(burst[6]);
    	east2.setDuration(Duration.seconds(1));
    	
    	TranslateTransition east1=new TranslateTransition();
    	east1.setByX(-500);
    	east1.setByY(-220);
    	east1.setNode(burst[7]);
    	east1.setDuration(Duration.seconds(1));
    	
    	TranslateTransition west3=new TranslateTransition();
    	west3.setByX(-500);
    	west3.setByY(220);
    	west3.setNode(burst[8]);
    	west3.setDuration(Duration.seconds(1));
    	
    	TranslateTransition west4=new TranslateTransition();
    	west4.setByX(-500);
    	west4.setByY(480);
    	west4.setNode(burst[9]);
    	west4.setDuration(Duration.seconds(1));
    	
    	TranslateTransition east3=new TranslateTransition();
    	east3.setByX(500);
    	east3.setByY(220);
    	east3.setNode(burst[10]);
    	east3.setDuration(Duration.seconds(1));
    	
    	TranslateTransition east4=new TranslateTransition();
    	east4.setByX(500);
    	east4.setByY(480);
    	east4.setNode(burst[11]);
    	east4.setDuration(Duration.seconds(1));
    	
    	pt.getChildren().addAll(up,down,left,right,west1,west2,east1,east2,west3,west4,east3,east4);
    	pt.play();
    	root.getChildren().addAll(burst);
    	
    }
    
    

}
