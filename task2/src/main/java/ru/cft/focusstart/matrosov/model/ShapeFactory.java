package ru.cft.focusstart.matrosov.model;

import ru.cft.focusstart.matrosov.exception.ShapeFormatException;

import java.util.List;

/**
 * Class represents a factory that builds geometric shapes based on different parameters
 */
public class ShapeFactory {
    /**
     * Builds shape from existing shape type and list of params that pretend to be the base of shape parameters
     * @param type of the shape, enum-value
     * @param params list of Double values that can be passed to shape's constructor if their format is correct
     * @return an instance of geometric shape if constructing not failed
     */
    public static Geometric2DShape buildShape(GeometricShapeType type, List<Double> params) throws ShapeFormatException {
        if (type == null || params.size() < type.getParamExpected())
            throw new ShapeFormatException("Параметры фигуры некорректны");

        switch (type) {
            case SQUARE: return new Square(params.get(0));
            case TRIANGLE: return new Triangle(params.get(0), params.get(1), params.get(2));
            case CIRCLE: return new Circle(params.get(0));
            case RECTANGLE: return new Rectangle(params.get(0), params.get(1));
            default: throw new ShapeFormatException("Фигура " + type + " не соответствует ни одному из имеющихся шаблонов");
        }
    }
}
