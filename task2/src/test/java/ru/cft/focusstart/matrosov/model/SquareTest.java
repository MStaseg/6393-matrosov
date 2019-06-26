package ru.cft.focusstart.matrosov.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;

public class SquareTest {

    @Test
    public void getArea() {
        Square square = new Square(5.0);
        assertEquals(25.0, square.getArea(), 0.0);
    }

    @Test
    public void getPerimeter() {
        Square square = new Square(5.0);
        assertEquals(20.0, square.getPerimeter(), 0.0);
    }

    @Test
    public void getType() {
        Square square = new Square(5.0);
        assertEquals("SQUARE", square.getType().name());
    }

    @Test
    public void getParameters() {
        List<GeometricShapeProperty> expected = new ArrayList<>();
        expected.add(new GeometricShapeProperty(GeometricShapeParameter.SIZE, 5.0));

        Square square = new Square(5.0);
        assertArrayEquals(expected.toArray(), square.getParameters().toArray());
    }

    @Test
    public void equalsReflection() {
        Square square = new Square(5.0);
        assertEquals(square, square);
    }

    @Test
    public void equalsSymmetry() {
        Square firstSquare = new Square(5.0);
        Square secondSquare = new Square(5.0);

        boolean equalsFirst = firstSquare.equals(secondSquare);
        boolean equalsSecond = secondSquare.equals(firstSquare);

        assertEquals(equalsFirst, equalsSecond);
    }

    @Test
    public void equalsTransitivity() {
        Square firstSquare = new Square(5.0);
        Square secondSquare = new Square(5.0);
        Square thirdSquare = new Square(5.0);

        boolean equalsFirst = firstSquare.equals(secondSquare);
        boolean equalsSecond = secondSquare.equals(thirdSquare);
        boolean equalsThird = firstSquare.equals(thirdSquare);

        assertEquals(equalsFirst && equalsSecond, equalsThird);
    }
}