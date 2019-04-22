package ru.cft.focusstart.matrosov.model;

public abstract class ShapeManager {
    public static Geometric2DShape rectangle(double width, double height) {
        return new Rectangle(width, height);
    }

    public static Geometric2DShape square(double size) {
        return new Square(size);
    }

    public static Geometric2DShape triangle(double firstSide, double secondSide, double thirdSize) {
        return new Triangle(firstSide, secondSide, thirdSize);
    }

    public static  Geometric2DShape circle(double radius) {
        return new Circle(radius);
    }
}
