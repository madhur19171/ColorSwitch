import javafx.scene.paint.Color;

public class Star extends Avatar {

    private int size;

    public Star(int size, Cordinate position, Color color) {
        super(position, color);
        this.size = size;
    }

    @Override
    public Cordinate getPosition() {
        return position;
    }

    @Override
    public void setPosition(Cordinate position) {
        this.position = position;
    }

    @Override
    public boolean checkVicinity(Ball ball) {
        return false;
    }

}
