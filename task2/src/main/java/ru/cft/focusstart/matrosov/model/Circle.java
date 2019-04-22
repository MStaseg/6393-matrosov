package ru.cft.focusstart.matrosov.model;

import java.util.*;

public class Circle implements Geometric2DShape {
    private double radius;

    public Circle(double radius) {
        if (radius < 0)
            throw new IllegalArgumentException("Радиус окружности не может быть отрицательной длины");

        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public Map<String, Double> parameters() {
        Map<String, Double> map = new HashMap<>();
        map.put("radius", radius);
        return map;
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
