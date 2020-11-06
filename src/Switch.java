import javafx.scene.paint.Color;

public class Switch extends Avatar {
    public Switch(Cordinate cordinate) {
        super(cordinate, Color.ALICEBLUE);
    }

    @Override
    public Cordinate getPosition() {
        return position;
    }

    @Override
    public void setPosition(Cordinate position) {
        this.position = position;
    }

    public void switchColor(Ball ball) {
        for (Color color : ColorList.getColorList())
            if (ball.getColor() != color) {
                ball.setColor(color);
                return;
            }
    }

    public boolean checkVicinity(Ball ball) {
        return false;
    }
}
