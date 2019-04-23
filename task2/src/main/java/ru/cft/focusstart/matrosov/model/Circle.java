package ru.cft.focusstart.matrosov.model;

import java.util.*;

/**
 * Class-implementation of Geometric2DShape that describes an circle
 */
public class Circle implements Geometric2DShape {

    private double radius;

    /**
     * Generates an instance of circle with existing radius
     * @param radius double
     */
    public Circle(double radius) {
        if (radius < 0)
            throw new IllegalArgumentException("Радиус окружности не может быть отрицательной длины");

        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    /**
     * An double perimeter value for circle
     * @return double
     */
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    /**
     * An double perimeter value for circle
     * @return double
     */
    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    /**
     * Return a list of all circle properties
     * @return List<GeometricShapeProperty>
     */
    @Override
    public List<GeometricShapeProperty> parameters() {
        List<GeometricShapeProperty> list = new LinkedList<>();
        list.add(new GeometricShapeProperty("radius", radius));
        list.add(new GeometricShapeProperty("diameter", radius * 2));
        return list;
    }

    @Override
    public GeometricShapeType type() {
        return GeometricShapeType.CIRCLE;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Circle))
            return false;

        Circle circle = (Circle)obj;
        return this.radius == circle.radius;
    }

    @Override
    public String toString() {
        return "Circle[radius = " + radius + "]";
    }
}
