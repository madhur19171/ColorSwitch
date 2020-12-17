import javafx.scene.paint.Color;

import java.io.Serializable;

public abstract class Avatar implements Positionable, Colorable {
    protected Cordinate cordinate;
    protected transient Color color;
    protected transient ColorList colorList;

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
