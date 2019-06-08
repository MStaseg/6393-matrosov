package ru.cft.focusstart.matrosov.util;

import java.util.Objects;

/**
 * Class-bean that represents the pair of x and y like geometric coordinates
 */
public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        if (x < 0 || y < 0) {
            throw new UnsupportedOperationException("Некорректные координаты x = " + x + " и y = " + y);
        }

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
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Coordinates)) {
            return false;
        }

        Coordinates coordinates = (Coordinates) obj;
        return this.x == coordinates.x && this.y == coordinates.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
