import javafx.scene.paint.Color;

public abstract class Avatar implements Positionable, Colorable {
    protected Cordinate cordinate;
    protected Color color;
    protected ColorList colorList;

    public Avatar(Cordinate cordinate, Color color) {
        this.cordinate = cordinate;
        this.color = color;
        colorList = new ColorList();
    }

    public ColorList getColorList() {
        return colorList;
    }

    public abstract Cordinate getCordinate();

    public abstract void setCordinate(Cordinate cordinate);

    abstract boolean checkVicinity(Ball ball);

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    // Bro i wish u die a virgin!!!!
}
