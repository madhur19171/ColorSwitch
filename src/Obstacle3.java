import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Obstacle3 extends Obstacle{
	
	private final Group obstacle;
    private final AnimationTimer animationTimer;
	
	public Obstacle3(double velocity, Cordinate position) throws IOException {
		
		super(velocity, position, ColorList.getColorList().get(0));
		// TODO Auto-generated constructor stub
		AnchorPane root = (FXMLLoader.load(getClass().getResource("/triangle.fxml")));
		obstacle = (Group) root.lookup("#triangle_obstacle");
		System.out.println(obstacle.isVisible());
		//obstacle.setLayoutX(cordinate.getX());
        //obstacle.setLayoutY(cordinate.getY());
        
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

	@Override
	public Group getObstacle() {
        return obstacle;
    }
	
	@Override
	boolean checkVicinity(Ball ball) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Cordinate getCordinate() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setCordinate(Cordinate cordinate) {
		// TODO Auto-generated method stub
		
	}
	
}
