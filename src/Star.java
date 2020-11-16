import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.ScaleTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Star extends Avatar {

    private int size;
    private final ImageView star;

    public Star(int size, Cordinate position) throws FileNotFoundException {
        super(position, Color.ALICEBLUE);
        this.size = size;//Not even required

        //Making Star using Star image
        Image image = new Image(new FileInputStream("src\\images\\Star.jpg"));
        star = new ImageView(image);

        //Fitting the Star image in a 68X60 pixel box.
        star.setFitHeight(60);
        star.setFitWidth(68);

        //Since the setX and setY will anchor the image with the top left corner and we are given cordinates of Center,
        //We need to compute the cordinate of corner using the cordinate of center and position the Start accordingly.
        star.setX(cordinate.getX() - star.getFitWidth() / 2);
        star.setY(cordinate.getY() - star.getFitHeight() / 2);

        //Animating the Star
        float scale = 0.4f;
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setDuration(Duration.millis(400));
        scaleTransition.setNode(star);
        scaleTransition.setByY(scale);//Setting the max extra amount by
                                        // which it will be scaled. At max, it will be scaled by 1.4 times.
        scaleTransition.setByX(scale);
        scaleTransition.setCycleCount(Animation.INDEFINITE);//Run the animation forever
        scaleTransition.setAutoReverse(true);//increase the size then decrease. If set to false, size will only decrease.
        scaleTransition.play();//Start the Animation.

        //Study Animation timer, it will play a phenomenal role in making this project.
        new AnimationTimer() {

            @Override
            public void handle(long now) {
                //System.out.println(star.getScaleX());
            }
        }.start();
    }

    public ImageView getStar() {
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
        star.setX(cordinate.getX() - star.getFitWidth() / 2);
        star.setY(cordinate.getY() - star.getFitHeight() / 2);
    }

    @Override
    public boolean checkVicinity(Ball ball) {
        return ball.getBall().getBoundsInParent().intersects(star.getBoundsInParent());
        //Fuck Yourself
    }

}
