package ru.cft.focusstart.matrosov.math;

import org.junit.Test;

import static org.junit.Assert.*;

public class MultiplyTableTest {

    @Test
    public void getSize() {
        MultiplyTable table = new MultiplyTable(5);
        assertEquals(5, table.getSize());
    }

    @Test
    public void getTable() {
        int[][] expected = new int[3][];

        expected[0] = new int[]{1, 2, 3};
        expected[1] = new int[]{2, 4, 6};
        expected[2] = new int[]{3, 6, 9};

        MultiplyTable table = new MultiplyTable(3);
        assertArrayEquals(expected, table.getTable());
    }

    @Test
    public void getMultiplicationResult() {
        MultiplyTable table = new MultiplyTable(4);
        assertEquals(4, table.getMultiplicationResult(2, 2));
    }
}