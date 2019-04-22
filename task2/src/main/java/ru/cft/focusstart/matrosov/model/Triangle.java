package ru.cft.focusstart.matrosov.model;

import java.util.*;

public class Triangle implements Geometric2DShape {
    private double firstSize;
    private double secondSize;
    private double thirdSize;

    public Triangle(double firstSide, double secondSide, double thirdSide) {
        if (firstSide < 0.0 || secondSide < 0.0 || thirdSide < 0.0)
            throw new IllegalArgumentException("Сторона треугольника не может быть отрицательной длины");
        if (firstSide + secondSide < thirdSide || firstSide + thirdSide < secondSide || secondSide + thirdSide < firstSide)
            throw new IllegalArgumentException("Из заданных сторон невозможно составить треугольник");

        this.firstSize = firstSide;
        this.secondSize = secondSide;
        this.thirdSize = thirdSide;
    }

    @Override
    public double area() {
        double semiPerimeter = perimeter() / 2;
        return Math.sqrt(semiPerimeter * (semiPerimeter - firstSize) * (semiPerimeter - secondSize) * (semiPerimeter - thirdSize));
    }

    @Override
    public double perimeter() {
        return firstSize + secondSize + thirdSize;
    }

    @Override
    public Map<String, Double> parameters() {
        Map<String, Double> map = new HashMap<>();
        map.put("firstSize", firstSize);
        map.put("secondSize", secondSize);
        map.put("thirdSize", thirdSize);
        return map;
    }

    @Override
    public GeometricShapeType type() {
        return GeometricShapeType.TRIANGLE;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Triangle))
            return false;

        Triangle triangle = (Triangle)obj;
        return this.firstSize == triangle.firstSize && this.secondSize == triangle.firstSize && this.thirdSize == triangle.firstSize;
    }

    @Override
    public String toString() {
        return "Triangle[firstSide = " + firstSize + ", secondSide = " + secondSize + ", thirdSide = " + thirdSize + "]";
    }
}
