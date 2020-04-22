package org.example.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import java.awt.Image;

/**
 * Creates and shows GUI.
 */
public class GUIBootstrapper {

    /**
     * Creates and shows GUI for generated identicon.
     *
     * @see PopupMenuHandler
     */
    public void start(Image image) {
        JFrame frame = new JFrame("Preview");
        JLabel label = new JLabel(new ImageIcon(image));

        frame.add(label);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.addMouseListener(new PopupMenuHandler(image));

        setSystemLookAndFeel();
        frame.setVisible(true);
    }

    private static void setSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            // woah
        } catch (IllegalAccessException e) {
            // woah
        } catch (InstantiationException e) {
            // woah
        } catch (UnsupportedLookAndFeelException e) {
            // woah
        }
    }
}
