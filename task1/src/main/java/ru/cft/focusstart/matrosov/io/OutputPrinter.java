package ru.cft.focusstart.matrosov.io;

import ru.cft.focusstart.matrosov.math.MultiplyTable;

import java.util.Arrays;

public class OutputPrinter {

    public static void showSizeAskingMessage(int from, int to) {
        System.out.println("Введите размер таблицы умножения в диапазоне от " + from + " до " + to);
    }

    public static void showErrorMessage() {
        System.out.println("Извините, произошла неизвестная ошибка");
    }

    public static void showParamErrorMessage() {
        System.out.println("Введенные данные некорректны");
    }

    /**
     * Shows table formatted to user
     *
     * @param table an instance of MultiplyTable
     */
    public  static void showMultiplyTable(MultiplyTable table) {
        if (table == null) {
            return;
        } else if (table.getSize() == 1) {
            System.out.println(table.getMultiplicationResult(1, 1));
            return;
        }

        int sizeOfTable = table.getSize();
        int formatLength =  String.valueOf(sizeOfTable * sizeOfTable).length();

        char[] lineArr = new char[formatLength];
        Arrays.fill(lineArr, '-');
        String dividingLineElement = String.valueOf(lineArr) + "+";

        for (int i = 0; i < sizeOfTable; i++) {
            for (int j = 0; j < sizeOfTable; j++) {
                int number = table.getMultiplicationResult(i + 1, j + 1);
                System.out.printf("%" + formatLength + "d|", number);
            }
            System.out.println();
            OutputPrinter.printDividerLine(dividingLineElement, sizeOfTable);
            System.out.println();
        }
    }

    public static void showGoodbyeMessage() {
        System.out.println("Работа приложения завершена");
    }

    private static void printDividerLine(String devider, int repeat) {
        for (int j = 0; j < repeat; j++) {
            System.out.print(devider);
        }
    }
}
