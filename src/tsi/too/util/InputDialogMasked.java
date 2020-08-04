package tsi.too.util;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InputDialogMasked extends JDialog {
    private JButton buttonOK;
    private JButton buttonCancel;

    private JPanel contentPane;
    @SuppressWarnings("unused")
    private JPanel innerContentPane;
    private JPanel inputPane;

    private JLabel messageLabel;

    private JFormattedTextField inputTextField;

    private String userInput = null;

    private InputDialogMasked(String title, String message, String mask) {
        setContentPane(contentPane);
        setSize(new Dimension(300, 128));
        setModal(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);
        setIconImage(null);

        setTitle(title);
        messageLabel.setText(message);

        createInputField(mask);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public static String showInputDialog(String title, String message, String mask) {
        InputDialogMasked dialog = new InputDialogMasked(title, message, mask);
        dialog.setVisible(true);

        return dialog.userInput;
    }

    private void createInputField(String mask) {
        try {
            MaskFormatter maskFormatter = new MaskFormatter(mask);
            maskFormatter.setPlaceholderCharacter('_');
            inputTextField = new JFormattedTextField(maskFormatter);
        } catch (Exception ex) {
            inputTextField = new JFormattedTextField();
        }

        inputTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //ignored
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    onOK();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //ignored
            }
        });

        inputPane.add(inputTextField);
    }

    private void onOK() {
        userInput = inputTextField.getText();
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}