package solarproject;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Error {

    private final JFrame FRAME; //A constant that holds a JFrame object that is used to display the error message

    private final Runnable ERRORSOUND = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation"); //A Runnable object that will play a windows error sound when the program has to display the error window

    Error(String error) { //Creates a JOptionPane that can display to the user that there has been an error. The parameter holds a string with a message to display on the error window.
        ERRORSOUND.run();
        FRAME = new JFrame();
        JOptionPane.showMessageDialog(FRAME, error, "Program Error", JOptionPane.WARNING_MESSAGE);
    }
}
