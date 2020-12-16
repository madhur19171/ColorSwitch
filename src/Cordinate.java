import java.io.Serializable;
import java.util.Objects;

public class Cordinate implements Serializable {
    private double x;
    private double y;

    public Cordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Cordinate(Cordinate cordinate) {
        this.x = cordinate.getX();
        this.y = cordinate.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void incX(double x) {
        this.x += x;
    }

    public void incY(double y) {
        this.y -= y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cordinate cordinate = (Cordinate) o;
        return Double.compare(cordinate.x, x) == 0 &&
                Double.compare(cordinate.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
