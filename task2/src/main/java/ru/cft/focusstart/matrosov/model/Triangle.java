package ru.cft.focusstart.matrosov.model;

import ru.cft.focusstart.matrosov.exception.ShapeFormatException;
import ru.cft.focusstart.matrosov.util.MathUtils;

import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class-implementation of Geometric2DShape that describes an triangle
 */
public class Triangle implements Geometric2DShape {

    private double firstSize;
    private double secondSize;
    private double thirdSize;

    /**
     * Creates an implementation of triangle with three existing size of sides
     *
     * @param firstSide first side size value greater than 0
     * @param secondSide second side size value greater than 0
     * @param thirdSide third side size value greater than 0
     */
    public Triangle(double firstSide, double secondSide, double thirdSide) {
        if (firstSide <= 0.0 || secondSide <= 0.0 || thirdSide <= 0.0)
            throw new ShapeFormatException("Сторона треугольника не может быть отрицательной или нулевой длины");
        if (firstSide + secondSide < thirdSide || firstSide + thirdSide < secondSide || secondSide + thirdSide < firstSide)
            throw new ShapeFormatException("Из заданных сторон невозможно составить треугольник");

        this.firstSize = firstSide;
        this.secondSize = secondSide;
        this.thirdSize = thirdSide;
    }

    @Override
    public double getArea() {
        double semiPerimeter = getPerimeter() / 2;
        double sqrtValue
                = semiPerimeter * (semiPerimeter - firstSize) * (semiPerimeter - secondSize) * (semiPerimeter - thirdSize);
        return Math.sqrt(sqrtValue);
    }

    @Override
    public double getPerimeter() {
        return firstSize + secondSize + thirdSize;
    }

    @Override
    public List<GeometricShapeProperty> getParameters() {
        List<GeometricShapeProperty> list = new ArrayList<>();

        list.add(new GeometricShapeProperty(GeometricShapeParameter.FIRST_SIDE, firstSize));
        list.add(new GeometricShapeProperty(GeometricShapeParameter.FIRST_SIDE_FRONT_ANGLE,
                getFrontAngleDegrees(firstSize, secondSize, thirdSize)));
        list.add(new GeometricShapeProperty(GeometricShapeParameter.SECOND_SIDE, secondSize));
        list.add(new GeometricShapeProperty(GeometricShapeParameter.SECOND_SIDE_FRONT_ANGLE,
                getFrontAngleDegrees(secondSize, firstSize, thirdSize)));
        list.add(new GeometricShapeProperty(GeometricShapeParameter.THIRD_SIDE, thirdSize));
        list.add(new GeometricShapeProperty(GeometricShapeParameter.THIRD_SIDE_FRONT_ANGLE,
                getFrontAngleDegrees(thirdSize, firstSize, secondSize)));

        return list;
    }

    private static double getFrontAngle(double currentSide, double secondSide, double thirdSide) {
        if (currentSide <=0 || secondSide <= 0 || thirdSide <= 0) {
            throw new IllegalArgumentException("Стороны треугольника не могут быть отрицательной или нулевой длины");
        }

        double cos = (secondSide * secondSide + thirdSide * thirdSide - currentSide * currentSide) /
                (2 * secondSide * thirdSide);
        return Math.acos(cos);
    }

    private static double getFrontAngleDegrees(double currentSide, double secondSide, double thirdSide) {
        double degreesFullValue = Math.toDegrees(getFrontAngle(currentSide, secondSide, thirdSide));
        return MathUtils.round(degreesFullValue);
    }

    @Override
    public GeometricShapeType getType() {
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
