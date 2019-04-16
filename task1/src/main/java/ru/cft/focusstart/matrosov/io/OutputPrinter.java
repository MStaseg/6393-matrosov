package ru.cft.focusstart.matrosov.io;

import com.sun.javafx.binding.StringFormatter;
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
        if (table == null)
            return;

        int sizeOfTable = table.getSize();
        if (sizeOfTable == 1) {
            System.out.println(1);
            return;
        }

        int formatLength =  String.valueOf(sizeOfTable * sizeOfTable).length();

        char[] lineArr = new char[formatLength];
        Arrays.fill(lineArr, '-');
        String dividingLineElement = String.valueOf(lineArr);
        String[] dividers = new String[sizeOfTable];
        Arrays.fill(dividers, dividingLineElement);

        String[] tablePrintLine = new String[sizeOfTable];
        for (int i = 0; i < sizeOfTable; i++) {
            for (int j = 0; j < sizeOfTable; j++) {
                int number = table.getMultiplicationResult(i + 1, j + 1);
                tablePrintLine[j] = String.format("%" + formatLength + "d", number);
            }
            OutputPrinter.printOutputDataLine(tablePrintLine, "|");
            OutputPrinter.printOutputDataLine(dividers, "+");
        }
    }

    public static void showGoodbyeMessage() {
        System.out.println("Работа приложения завершена");
    }

    private static void printOutputDataLine(String[] elements, String divider) {
        System.out.println(String.join(divider, elements));
    }
}
