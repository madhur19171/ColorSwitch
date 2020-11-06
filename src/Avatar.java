import javafx.scene.paint.Color;

public abstract class Avatar {
    protected Cordinate position;
    protected Color color;
    protected ColorList colorList;

    public Avatar(Cordinate position, Color color) {
        this.position = position;
        this.color = color;
        colorList = new ColorList();
    }

    public ColorList getColorList() {
        return colorList;
    }

    public abstract Cordinate getPosition();

    public abstract void setPosition(Cordinate position);

    abstract boolean checkVicinity(Ball ball);

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    // BRO this comment is by ur bro!!!!!
    ///never delete this comment
    /// else our bro ship will end 
}