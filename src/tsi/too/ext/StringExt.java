package tsi.too.ext;

import java.math.BigDecimal;

public abstract class StringExt {

    /**
     * Parses the string as a [long] number and returns the result.
     *
     * @return the long value represented by the string or zero if the string cannot be parsed.
     */
    public static long toLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    /**
     * Parses the string as a [Int] number and returns the result.
     *
     * @return the int value represented by the string or zero if the string cannot be parsed.
     */
    public static int toInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    /**
     * Parses the string as a [Double] number and returns the result.
     *
     * @return the double value represented by the string or zero if the string cannot be parsed.
     */
    public static double toDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    /**
     * Parses the string as a [BigDecimal] number and returns the result.
     *
     * @return the double value represented by the string or zero if the string cannot be parsed.
     */
    public static BigDecimal toBigDecimal(String str) {
        try {
            return new BigDecimal(str);
        } catch (NumberFormatException ex) {
            return BigDecimal.ZERO;
        }
    }
}
