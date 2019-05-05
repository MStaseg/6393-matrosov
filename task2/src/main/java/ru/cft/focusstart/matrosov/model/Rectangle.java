package ru.cft.focusstart.matrosov.model;

import ru.cft.focusstart.matrosov.exception.ShapeFormatException;

import java.util.*;

/**
 * Class-implementation of Geometric2DShape that describes an rectangle
 */
public class Rectangle implements Geometric2DShape {

    private double width;
    private double height;

    /**
     * Creates an instance of rectangle with it's width and height
     * @param firstSide width of the rectangle
     * @param secondSide height of the rectangle
     */
    public Rectangle(double firstSide, double secondSide) {
        if (firstSide <= 0 || secondSide <= 0)
            throw new ShapeFormatException("Сторона прямоугольника не может быть отрицательной или нулевой длины");

        this.width = Math.max(firstSide, secondSide);
        this.height = Math.min(firstSide, secondSide);
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return (width + height) * 2;
    }

    private double getDiagonal() {
        return Math.sqrt(width * width + height * height);
    }

    @Override
    public List<GeometricShapeProperty> getParameters() {
        List<GeometricShapeProperty> list = new ArrayList<>();

        list.add(new GeometricShapeProperty("width", width));
        list.add(new GeometricShapeProperty("height", height));
        list.add(new GeometricShapeProperty("diagonal", getDiagonal()));

        return list;
    }

    @Override
    public GeometricShapeType getType() {
        return GeometricShapeType.RECTANGLE;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Rectangle))
            return false;

        Rectangle rect = (Rectangle)obj;
        return this.width == rect.width && this.height == rect.height;
    }

    @Override
    public String toString() {
        return "Rectangle[width = " + width + ", height = " + height + "]";
    }
}
