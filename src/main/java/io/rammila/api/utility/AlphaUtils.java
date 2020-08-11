package io.rammila.api.utility;

public class AlphaUtils {
    public static double toDigit(double amount) {
        return Math.round(amount * 100) / 100.0;
    }
}
