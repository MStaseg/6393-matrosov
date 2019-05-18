package ru.cft.focusstart.matrosov.model;

import java.util.*;

/**
 * Public interface for geometric shapes
 *
 */
public interface Geometric2DShape {

    /**
     * The area figure occupies in geometric terms
     * @return area value
     */
    double getArea();

    /**
     * Sum of all figure sides
     * @return perimeter value
     */
    double getPerimeter();

    /**
     * Method return the list of current shape's parameters. You should implement this method with returning
     * full information of your geometric shape that can be useful to client's code.
     *
     * @return List of shape parameters wrapped in GeometricShapeProperty
     */
    List<GeometricShapeProperty> getParameters();

    /**
     * Method should be use when you want to get the type of geometric shape you're working with
     *
     * @return enum type of the shape's instance
     */
    GeometricShapeType getType();
}
