import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;

public class ColorList implements Serializable {
    private static ArrayList<Color> colorList;

    public ColorList() {
        colorList = new ArrayList<>(0);
        colorList.add(Color.rgb(142, 19, 250));
        colorList.add(Color.rgb(247, 223, 13));
        colorList.add(Color.rgb(55, 226, 243));
        colorList.add(Color.rgb(255, 0, 126));
    }

    public static ArrayList<Color> getColorList() {
        return colorList;
    }
}
