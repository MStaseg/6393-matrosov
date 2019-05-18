package ru.cft.focusstart.matrosov.model;

import ru.cft.focusstart.matrosov.exception.ShapeFormatException;

import java.util.*;

/**
 * Class-implementation of Geometric2DShape that describes an square
 */
public class Square implements Geometric2DShape {

    private double size;

    /**
     * Creates a rectangle instance from existing size
     * @param size of square greater than 0
     */
    public Square(double size) {
        if (size <= 0)
            throw new ShapeFormatException("Сторона квадрата не может быть отрицательной или нулевой длины");
        this.size = size;
    }

    @Override
    public double getArea() {
        return size * size;
    }

    @Override
    public double getPerimeter() {
        return 4 * size;
    }

    @Override
    public List<GeometricShapeProperty> getParameters() {
        List<GeometricShapeProperty> list = new ArrayList<>();
        list.add(new GeometricShapeProperty(GeometricShapeParameter.SIZE, size));
        return list;
    }

    @Override
    public GeometricShapeType getType() {
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
