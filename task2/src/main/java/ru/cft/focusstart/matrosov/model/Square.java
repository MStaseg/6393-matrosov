package ru.cft.focusstart.matrosov.model;

import java.util.*;

/**
 * Class-implementation of Geometric2DShape that describes an square
 */
public class Square implements Geometric2DShape {

    private double size;

    /**
     * Creates a rectangle instance from existing size
     * @param size double
     */
    public Square(double size) {
        if (size < 0)
            throw new IllegalArgumentException("Сторона квадрата не может быть отрицательной длины");
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    /**
     * An double area value for square
     * @return double
     */
    @Override
    public double area() {
        return size * size;
    }

    /**
     * An double perimeter value for square
     * @return double
     */
    @Override
    public double perimeter() {
        return 4 * size;
    }

    /**
     * Return a list of all square properties
     * @return List<GeometricShapeProperty>
     */
    @Override
    public List<GeometricShapeProperty> parameters() {
        List<GeometricShapeProperty> list = new LinkedList<>();
        list.add(new GeometricShapeProperty("size", size));
        return list;
    }

    @Override
    public GeometricShapeType type() {
        return GeometricShapeType.SQUARE;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Square))
            return false;

        Square square = (Square)obj;
        return this.size == square.size;
    }

    @Override
    public String toString() {
        return "Square[size = " + size + "]";
    }
}
