import javafx.scene.paint.Color;

public class Star extends Avatar {

    private int size;

    public Star(int size, Cordinate position, Color color) {
        super(position, color);
        this.size = size;
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

}
