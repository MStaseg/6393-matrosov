package ru.cft.focusstart.matrosov;

import ru.cft.focusstart.matrosov.validation.*;

/**
 * Class-singleton for holding application parameters and default static constants
 */
public class ApplicationParameters {
    public final static int MINIMUM_TABLE_SIZE = 1;
    public final static int MAXIMUM_TABLE_SIZE = 32;

    private int tableSize = 0;

    private static ApplicationParameters instance;

    static synchronized ApplicationParameters getInstance() {
        if (instance == null) {
            instance = new ApplicationParameters();
        }
        return instance;
    }

    void setTableSize(int tableSize) {
        if (Validator.validateTableSize(tableSize))
            this.tableSize = tableSize;
        else
            throw new IllegalArgumentException();
    }

    int getTableSize() {
        return tableSize;
    }
}
