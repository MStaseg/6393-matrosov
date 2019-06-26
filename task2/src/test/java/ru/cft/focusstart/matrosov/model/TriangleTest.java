package ru.cft.focusstart.matrosov.model;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;

public class TriangleTest {

    @Test
    public void getArea() {
        Triangle triangle = new Triangle(3.0, 4.0, 5.0);
        assertEquals(6.0, triangle.getArea(), 0.0);
    }

    @Test
    public void getPerimeter() {
        Triangle triangle = new Triangle(3.0, 4.0, 5.0);
        assertEquals(12.0, triangle.getPerimeter(), 0.0);
    }

    @Test
    public void getParameters() {
        List<GeometricShapeProperty> expected = new ArrayList<>();

        expected.add(new GeometricShapeProperty(GeometricShapeParameter.FIRST_SIDE, 5.0));
        expected.add(new GeometricShapeProperty(GeometricShapeParameter.FIRST_SIDE_FRONT_ANGLE, 60.0));
        expected.add(new GeometricShapeProperty(GeometricShapeParameter.SECOND_SIDE, 5.0));
        expected.add(new GeometricShapeProperty(GeometricShapeParameter.SECOND_SIDE_FRONT_ANGLE, 60.0));
        expected.add(new GeometricShapeProperty(GeometricShapeParameter.THIRD_SIDE, 5.0));
        expected.add(new GeometricShapeProperty(GeometricShapeParameter.THIRD_SIDE_FRONT_ANGLE, 60.0));

        Triangle triangle = new Triangle(5.0, 5.0, 5.0);
        assertArrayEquals(expected.toArray(), triangle.getParameters().toArray());
    }

    @Test
    public void getType() {
        Triangle triangle = new Triangle(3.0, 4.0, 5.0);
        assertEquals("TRIANGLE", triangle.getType().name());
    }

    @Test
    public void equalsReflection() {
        Triangle triangle = new Triangle(3.0, 4.0, 5.0);
        assertEquals(triangle, triangle);
    }

    @Test
    public void equalsSymmetry() {
        Triangle firstTriangle = new Triangle(3.0, 4.0, 5.0);
        Triangle secondTriangle = new Triangle(3.0, 4.0, 5.0);

        boolean equalsFirst = firstTriangle.equals(secondTriangle);
        boolean equalsSecond = secondTriangle.equals(firstTriangle);

        assertEquals(equalsFirst, equalsSecond);
    }

    @Test
    public void equalsTransitivity() {
        Triangle firstTriangle = new Triangle(3.0, 4.0, 5.0);
        Triangle secondTriangle = new Triangle(3.0, 4.0, 5.0);
        Triangle thirdTriangle = new Triangle(3.0, 4.0, 5.0);

        boolean equalsFirst = firstTriangle.equals(secondTriangle);
        boolean equalsSecond = secondTriangle.equals(thirdTriangle);
        boolean equalsThird = firstTriangle.equals(thirdTriangle);

        assertEquals(equalsFirst && equalsSecond, equalsThird);
    }
}