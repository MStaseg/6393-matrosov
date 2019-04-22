package ru.cft.focusstart.matrosov.model;

import java.util.*;

public class Rectangle implements Geometric2DShape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        if (width < 0 || height < 0)
            throw new IllegalArgumentException("Сторона прямоугольника не может быть отрицательной длины");
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
}

    @Override
    public double perimeter() {
        return (width + height) * 2;
    }

    @Override
    public Map<String, Double> parameters() {
        Map<String, Double> map = new HashMap<>();
        map.put("width", width);
        map.put("height", height);
        return map;
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
