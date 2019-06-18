package ru.cft.focusstart.matrosov.task4_1;

class MathUtils {
    /**
     * Checks if current integer value is prime number or not
     *
     * @param number to be checked
     * @return true if prime else false
     */
    public static boolean isPrimeNumber(int number) {
        if (number % 2 == 0) {
            return false;
        }

        for (int i = 3; i < number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
