package ru.cft.focusstart.matrosov.model;

import java.util.*;

/**
 * Class-implementation of Geometric2DShape that describes an rectangle
 */
public class Triangle implements Geometric2DShape {

    private double firstSize;
    private double secondSize;
    private double thirdSize;

    /**
     * Creates an implementation of triangle with three existing size of sides
     *
     * @param firstSide first side size value
     * @param secondSide second side size value
     * @param thirdSide third side size value
     */
    public Triangle(double firstSide, double secondSide, double thirdSide) {
        if (firstSide <= 0.0 || secondSide <= 0.0 || thirdSide <= 0.0)
            throw new IllegalArgumentException("Сторона треугольника не может быть отрицательной или нулевой длины");
        if (firstSide + secondSide < thirdSide || firstSide + thirdSide < secondSide || secondSide + thirdSide < firstSide)
            throw new IllegalArgumentException("Из заданных сторон невозможно составить треугольник");

        this.firstSize = firstSide;
        this.secondSize = secondSide;
        this.thirdSize = thirdSide;
    }

    /**
     * An double area value for triangle
     * @return double
     */
    @Override
    public double area() {
        double semiPerimeter = perimeter() / 2;
        return Math.sqrt(semiPerimeter * (semiPerimeter - firstSize) * (semiPerimeter - secondSize) * (semiPerimeter - thirdSize));
    }

    /**
     * An double perimeter value for triangle
     * @return double
     */
    @Override
    public double perimeter() {
        return firstSize + secondSize + thirdSize;
    }

    /**
     * Uses cosinus theorem to calculate an front angle value for current side. Returns value in radians
     *
     * @param currentSide an side we generate an angle size for
     * @param secondSide second side length
     * @param thirdSide third side length
     * @return double in radians
     */
    public static double frontAngle(double currentSide, double secondSide, double thirdSide) {
        double cos = (secondSide * secondSide + thirdSide * thirdSide - currentSide * currentSide) / (2 * secondSide * thirdSide);
        return Math.acos(cos);
    }

    /**
     * Degree-based version of method returning value for front angle of the triangle side
     *
     * @param currentSide an side we generate an angle size for
     * @param secondSide second side length
     * @param thirdSide third side length
     * @return double
     */
    public static double frontAngleDegrees(double currentSide, double secondSide, double thirdSide) {
        return Math.toDegrees(Triangle.frontAngle(currentSide, secondSide, thirdSide));
    }

    /**
     * Return a list of all triangle properties
     * @return List<GeometricShapeProperty>
     */
    @Override
    public List<GeometricShapeProperty> parameters() {
        List<GeometricShapeProperty> list = new LinkedList<>();

        list.add(new GeometricShapeProperty("firstSize", firstSize));
        list.add(new GeometricShapeProperty("firstSizeFrontAngle", frontAngleDegrees(firstSize, secondSize, thirdSize)));
        list.add(new GeometricShapeProperty("secondSize", secondSize));
        list.add(new GeometricShapeProperty("secondSizeFrontAngle", frontAngleDegrees(secondSize, firstSize, thirdSize)));
        list.add(new GeometricShapeProperty("thirdSize", thirdSize));
        list.add(new GeometricShapeProperty("thirdSizeFrontAngle", frontAngleDegrees(thirdSize, firstSize, secondSize)));

        return list;
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
