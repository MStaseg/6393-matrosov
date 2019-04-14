package ru.cft.focusstart.matrosov.math;


import java.util.Arrays;

public class MultiplyTable {

    private int size;
    private int[][] table;

    /**
     * Builds multiplication table of size pased
     *
     * @param size size of generating table
     */
    public MultiplyTable(int size) {
        this.size = size;
        this.table = new int[size][];
        for (int i = 0; i < size; i ++) {
            for (int j = 0; j < i + 1; j ++) {
                if (this.table[i] == null) this.table[i] = new int[i + 1];
                this.table[i][j] = (i + 1) * (j + 1);
            }
        }
    }

    public int getSize() {
        return size;
    }

    /**
     * Returs multiplication table
     * Not returning the inner table link to not break encapsulation
     *
     * @return resulting multiplication table with indexes starting from 0
     */
    public int[][] getTable() {
        int [][] result = new int[size][size];
        for (int i = 0; i < size; i ++) {
            for (int j = 0; j < size; j ++) {
                result[i][j] = j <= i ? table[i][j] : table[j][i];
            }
        }
        return result;
    }

    /**
     * Returns multiply result for two integer numbers
     *
     * @param first first integer number
     * @param second second integer number
     * @return resulting integer number
     */
    public int getMultiplicationResult(int first, int second) {
        if (first > size || second > size) throw new IllegalArgumentException();
        return first >= second ? table[first][second] : table[second][first];
    }

    @Override
    public String toString() {
        return Arrays.deepToString(this.getTable());
    }
}
