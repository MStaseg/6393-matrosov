package util;

public class Coordinates {
    int x;
    int y;

    public Coordinates(int x, int y) {
        if (x < 0 || y < 0) {
            throw new UnsupportedOperationException("Некорректные координаты x = " + x + " и y = " + y);
        }

        this.x = x;
        this.y = y;
    }
}
