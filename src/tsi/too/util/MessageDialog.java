package tsi.too.util;

import static javax.swing.JOptionPane.*;

public abstract class MessageDialog {
    /**
     * Brings up an information-message dialog.
     *
     * @param title   the <code>Object</code> to display in the dialog title bar.
     * @param message the <code>Object</code> to display.
     */
    public static void showInformationDialog(String title, String message) {
        showMessageDialog(null, message, title, INFORMATION_MESSAGE);
    }

    /**
     * Brings up an alert-message dialog.
     *
     * @param title   the <code>Object</code> to display in the dialog title bar.
     * @param message the <code>Object</code> to display.
     */
    public static void showAlertDialog(String title, String message) {
        showMessageDialog(null, message, title, WARNING_MESSAGE);
    }
}