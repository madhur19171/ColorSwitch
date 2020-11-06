import javafx.scene.paint.Color;

public class Switch extends Avatar {
    public Switch(Cordinate cordinate) {
        super(cordinate, Color.ALICEBLUE);
    }

    public void switchColor(Ball ball) {
        for (Color color : ColorList.getColorList())
            if (ball.getColor() != color) {
                ball.setColor(color);
                return;
            }
    }

    @Override
    public Cordinate getCordinate() {
        return cordinate;
    }

    @Override
    public void setCordinate(Cordinate cordinate) {
        this.cordinate = cordinate;
    }

    public boolean checkVicinity(Ball ball) {
        return false;
    }
}
