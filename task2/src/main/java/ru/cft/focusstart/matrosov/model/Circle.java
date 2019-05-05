package ru.cft.focusstart.matrosov.model;

import ru.cft.focusstart.matrosov.exception.ShapeFormatException;

import java.util.*;

/**
 * Class-implementation of Geometric2DShape that describes an circle
 */
public class Circle implements Geometric2DShape {

    private double radius;

    /**
     * Creates an instance of circle with existing radius
     * @param radius of the circle needed
     */
    public Circle(double radius) {
        if (radius <= 0) {
            throw new ShapeFormatException("Радиус окружности не может быть отрицательной или нулевой длины");
        }

        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public List<GeometricShapeProperty> getParameters() {
        List<GeometricShapeProperty> list = new ArrayList<>();
        list.add(new GeometricShapeProperty("radius", radius));
        list.add(new GeometricShapeProperty("diameter", radius * 2));
        return list;
    }

    @Override
    public GeometricShapeType getType() {
        return GeometricShapeType.CIRCLE;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Circle)) {
            return false;
        }

        Circle circle = (Circle) obj;
        return this.radius == circle.radius;
    }

    @Override
    public String toString() {
        return "Circle[radius = " + radius + "]";
    }
}
