package ru.cft.focusstart.matrosov;

import ru.cft.focusstart.matrosov.io.OutputPrinter;
import ru.cft.focusstart.matrosov.math.MultiplyTable;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Application.run();
    }

    private static void run() {
        while (ApplicationParameters.getInstance().getTableSize() == 0) {
            try {
                OutputPrinter.showSizeAskingMessage(ApplicationParameters.MINIMUM_TABLE_SIZE, ApplicationParameters.MAXIMUM_TABLE_SIZE);
                int inputNumber = new Scanner(System.in).nextInt();
                ApplicationParameters.getInstance().setTableSize(inputNumber);
            } catch (RuntimeException e) {
                OutputPrinter.showParamErrorMessage();
            }
        }

        Application.buildTable();
        OutputPrinter.showGoodbyeMessage();
    }

    private static void buildTable() {
        try {
            MultiplyTable table = new MultiplyTable(ApplicationParameters.getInstance().getTableSize());
            OutputPrinter.showMultiplyTable(table);
        } catch (Exception e) {
            OutputPrinter.showErrorMessage();
        }
    }
}
