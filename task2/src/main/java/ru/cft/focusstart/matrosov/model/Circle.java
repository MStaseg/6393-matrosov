package ru.cft.focusstart.matrosov.model;

import ru.cft.focusstart.matrosov.exception.ShapeFormatException;
import ru.cft.focusstart.matrosov.util.MathUtils;

import java.util.*;

/**
 * Class-implementation of Geometric2DShape that describes an circle
 */
public class Circle implements Geometric2DShape {

    private double radius;

    /**
     * Creates an instance of circle with existing radius
     * @param radius of the circle needed greater than 0
     */
    public Circle(double radius) {
        if (radius <= 0) {
            throw new ShapeFormatException("Радиус окружности не может быть отрицательной или нулевой длины");
        }

        this.radius = radius;
    }

    @Override
    public double getArea() {
        double fullValue = Math.PI * radius * radius;
        return MathUtils.round(fullValue);
    }

    @Override
    public double getPerimeter() {
        double fullValue = 2 * Math.PI * radius;
        return MathUtils.round(fullValue);
    }

    @Override
    public List<GeometricShapeProperty> getParameters() {
        List<GeometricShapeProperty> list = new ArrayList<>();
        list.add(new GeometricShapeProperty(GeometricShapeParameter.RADIUS, radius));
        list.add(new GeometricShapeProperty(GeometricShapeParameter.DIAMETER, radius * 2));
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
