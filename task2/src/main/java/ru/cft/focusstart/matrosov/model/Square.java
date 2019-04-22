package ru.cft.focusstart.matrosov.model;

import java.util.*;

public class Square implements Geometric2DShape {
    private double size;

    public Square(double size) {
        if (size < 0)
            throw new IllegalArgumentException("Сторона квадрата не может быть отрицательной длины");
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    @Override
    public double area() {
        return size * size;
    }

    @Override
    public double perimeter() {
        return 4 * size;
    }

    @Override
    public Map<String, Double> parameters() {
        Map<String, Double> map = new HashMap<>();
        map.put("size", size);
        return map;
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
