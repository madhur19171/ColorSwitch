import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ColorList {
    private static ArrayList<Color> colorList;

    public ColorList() {
        colorList.add(Color.BLUE);
        colorList.add(Color.ORANGE);
        colorList.add(Color.PINK);
        colorList.add(Color.LIME);
    }

    public static ArrayList<Color> getColorList() {
        return colorList;
    }
}
