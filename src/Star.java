import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Star extends Avatar {

    private double size;
    private final SVGPath star;
    private final ScaleTransition scaleTransition;

    public Star(double size, Cordinate position) throws IOException {
        super(position, Color.ALICEBLUE);
        this.size = size;//Not even required

        //Making Star using Star image
        //AnchorPane root = (FXMLLoader.load(getClass().getResource("Star.fxml")));
        AnchorPane root = (FXMLLoader.load(getClass().getResource("Star.fxml")));
        star = (SVGPath) root.getChildren().get(0);

        star.setTranslateX(cordinate.getX());
        star.setTranslateY(cordinate.getY());

        star.setScaleX(size);
        star.setScaleY(size);

        //Animating the Star
        float scale = 0.1f;
        scaleTransition = new ScaleTransition();
        scaleTransition.setDuration(Duration.millis(400));
        scaleTransition.setNode(star);
        scaleTransition.setByY(scale);//Setting the max extra amount by
        // which it will be scaled. At max, it will be scaled by 1.4 times.
        scaleTransition.setByX(scale);
        scaleTransition.setCycleCount(Animation.INDEFINITE);//Run the animation forever
        scaleTransition.setAutoReverse(true);//increase the size then decrease. If set to false, size will only decrease.
        scaleTransition.play();//Start the Animation.

        //Study Animation timer, it will play a phenomenal role in making this project.
//        new AnimationTimer() {
//
//            @Override
//            public void handle(long now) {
//                //System.out.println(star.getScaleX());
//            }
//        }.start();
    }

    public SVGPath getStar() {
        return star;
    }

    @Override
    public Cordinate getCordinate() {
        return cordinate;
    }

    @Override
    public void setCordinate(Cordinate cordinate) {
        //This will help in moving the star using only its center cordinates.
        this.cordinate = cordinate;
        star.setTranslateX(cordinate.getX());
        star.setTranslateY(cordinate.getY());
    }

    @Override
    public boolean checkVicinity(Ball ball) {
        Shape intersect = Shape.intersect(star, ball.getBall());
        return intersect.getBoundsInLocal().getWidth() != -1;
        //Fuck Yourself
    }

    public void killStar() {
        scaleTransition.stop();
    }

}
