package ru.cft.focusstart.matrosov.validation;

import ru.cft.focusstart.matrosov.ApplicationParameters;

public class Validator {
    /**
     * Checks if current multiplication table size is correct
     *
     * @param size size of the table
     * @return true or false if the size is incorrect
     */
    public static boolean validateTableSize(int size) {
        return size >= ApplicationParameters.MINIMUM_TABLE_SIZE && size <= ApplicationParameters.MAXIMUM_TABLE_SIZE;
    }
}
