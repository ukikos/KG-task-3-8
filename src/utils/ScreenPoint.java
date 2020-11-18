package utils;

public class ScreenPoint {
    private int x;
    private int y;

    public ScreenPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "ScreenPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
