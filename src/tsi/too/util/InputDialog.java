package tsi.too.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tsi.too.ext.StringExt;

import java.math.BigDecimal;
import java.time.LocalDate;

import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;

public abstract class InputDialog {

    /**
     * Shows a dialog requesting input from the user parented to
     * with the dialog having the title and message.
     *
     * @param title     the <code>Object</code> to display in the dialog title bar.
     * @param message   the <code>Object</code> to display.
     * @param validator the <code>Object</code> with the validation rule.
     * @return user's input parsed or null if aborted.
     */
    @Nullable
    public static Integer showIntegerInputDialog(String title, String message, @NotNull InputValidator<Integer> validator) {
        int input;
        String s;

        do {
            s = showStringInputDialog(title, message);
            if (s == null)
                return null;

            input = StringExt.toInt(s);
        } while (!validator.isValid(input));

        return input;
    }

    /**
     * Shows a dialog requesting input from the user parented to
     * with the dialog having the title and message.
     *
     * @param title     the <code>Object</code> to display in the dialog title bar.
     * @param message   the <code>Object</code> to display.
     * @param validator the <code>Object</code> with the validation rule.
     * @return user's input parsed or null if aborted.
     */
    @Nullable
    public static Long showLongInputDialog(String title, String message, @NotNull InputValidator<Long> validator) {
        long input;
        String s;

        do {
            s = showStringInputDialog(title, message);
            if (s == null)
                return null;

            input = StringExt.toLong(s);
        } while (!validator.isValid(input));

        return input;
    }


    /**
     * Shows a dialog requesting input from the user parented to
     * with the dialog having the title and message.
     *
     * @param title     the <code>Object</code> to display in the dialog title bar.
     * @param message   the <code>Object</code> to display.
     * @param validator the <code>Object</code> with the validation rule.
     * @return user's input parsed or null if aborted.
     */
    @Nullable
    public static Double showDoubleInputDialog(String title, String message, @NotNull InputValidator<Double> validator) {
        double input;
        String s;

        do {
            s = showStringInputDialog(title, message);
            if (s == null)
                return null;

            input = StringExt.toDouble(s);
        } while (!validator.isValid(input));

        return input;
    }

    /**
     * Shows a dialog requesting input from the user parented to
     * with the dialog having the title and message.
     *
     * @param title     the <code>Object</code> to display in the dialog title bar.
     * @param message   the <code>Object</code> to display.
     * @param validator the <code>Object</code> with the validation rule.
     * @return user's input parsed or null if aborted.
     */
    @Nullable
    public static BigDecimal showBigDecimalInputDialog(String title, String message, @NotNull InputValidator<BigDecimal> validator) {
        BigDecimal input;
        String s;

        do {
            s = showStringInputDialog(title, message);
            if (s == null)
                return null;

            input = StringExt.toBigDecimal(s);
        } while (!validator.isValid(input));

        return input;
    }

    /**
     * Shows a dialog requesting input from the user.
     *
     * @param title   the <code>Object</code> to display in the dialog title bar.
     * @param message the <code>Object</code> to display.
     * @return user's input.
     */
    @Nullable
    public static String showStringInputDialog(String title, String message) {
        return showInputDialog(null, message, title, PLAIN_MESSAGE);
    }

    /**
     * Shows a dialog requesting input from the user.
     *
     * @param title   the <code>Object</code> to display in the dialog title bar.
     * @param message the <code>Object</code> to display.
     * @return user's input.
     */
    @Nullable
    public static String showStringInputDialog(String title, String message, InputValidator<String> validator) {
        String s;

        do {
            s = showStringInputDialog(title, message);
            if (s == null)
                return null;
        } while (!validator.isValid(s));

        return s;
    }

    @Nullable
    public static String showMaskedInputDialog(String title, String message, String mask, InputValidator<String> validator) {
        String input;
        do {
            input = InputDialogMasked.showInputDialog(title, message, mask);
            if (input == null)
                return null;

        } while (!validator.isValid(input));

        return input;
    }

    @Nullable
    public static LocalDate showBrazilianDateInputDialog(String title, String message, InputValidator<LocalDate> validator) {
        String input;
        String mask = "##/##/####";
        LocalDate result;

        do {
            input = InputDialogMasked.showInputDialog(title, message, mask);
            if (input == null)
                return null;

            try {
                String[] spl = input.split("/");
                result = LocalDate.of(StringExt.toInt(spl[2]), StringExt.toInt(spl[1]), StringExt.toInt(spl[0]));
            } catch (Exception ex) {
                result = LocalDate.MIN;
            }
        } while (!validator.isValid(result));

        return result;
    }

    /**
     * A processor that checks an input.
     */
    public interface InputValidator<E> {

        /**
         * Checks if whether the input is valid or not.
         *
         * @param input the input to be checked
         * @return true if valid, false otherwise.
         */
        boolean isValid(E input);
    }
}
