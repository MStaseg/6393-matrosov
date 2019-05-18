package ru.cft.focusstart.matrosov.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class MathUtils {
    private MathUtils() {}

    public static double round(double value) {
        BigDecimal bd = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
