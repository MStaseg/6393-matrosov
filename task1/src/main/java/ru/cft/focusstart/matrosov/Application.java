package ru.cft.focusstart.matrosov;

import ru.cft.focusstart.matrosov.math.*;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        MultiplyTable table = new MultiplyTable(10);
        System.out.println("5 * 9 = " + table.getMultiplicationResult(9, 5));
        System.out.println(Arrays.deepToString(table.getTable()));
    }
}
