package ru.cft.focusstart.matrosov.model;

import java.util.*;

/**
 * Class-implementation of Geometric2DShape that describes an rectangle
 */
public class Rectangle implements Geometric2DShape {

    private double width;
    private double height;

    /**
     * Creates an instance of rectangle with it's width and height
     * @param width width of the rectangle
     * @param height height of the rectangle
     */
    public Rectangle(double width, double height) {
        if (width < 0 || height < 0)
            throw new IllegalArgumentException("Сторона прямоугольника не может быть отрицательной длины");

        if (width > height) {
            this.width = width;
            this.height = height;
        } else {
            this.width = height;
            this.height = width;
        }
    }

    /**
     * An double area value for rectangle
     * @return double
     */
    @Override
    public double area() {
        return width * height;
    }

    /**
     * An double perimeter value for rectangle
     * @return double
     */
    @Override
    public double perimeter() {
        return (width + height) * 2;
    }
    /**
     * An double diagonal value for rectangle
     * @return double
     */
    public double diagonal() {
        return Math.sqrt(width * width + height * height);
    }

    /**
     * Return a list of all rectangle properties
     * @return List<GeometricShapeProperty>
     */
    @Override
    public List<GeometricShapeProperty> parameters() {
        List<GeometricShapeProperty> list = new LinkedList<>();

        list.add(new GeometricShapeProperty("width", width));
        list.add(new GeometricShapeProperty("height", height));
        list.add(new GeometricShapeProperty("diagonal", diagonal()));

        return list;
    }

    @Override
    public GeometricShapeType type() {
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
