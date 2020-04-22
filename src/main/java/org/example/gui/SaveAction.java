package org.example.gui;

import org.example.util.Utils;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Save menu action.
 */
class SaveAction extends AbstractAction {
    /** Generated identicon. */
    private final Image image;
    /** File chooser gui component. */
    private JFileChooser fileChooser;

    public SaveAction(Image image) {
        super("Save");
        this.image = image;
    }

    /**
     * Save image to file.
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int result = getFileChooser().showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File output = getFileChooser().getSelectedFile();
            Utils.writeImageToFile(image, output);
        }
    }

    private JFileChooser getFileChooser() {
        if (fileChooser == null) {
            File defaultDirectory = FileSystemView.getFileSystemView().getDefaultDirectory();
            fileChooser = new JFileChooser(defaultDirectory);
            /*if (System.getProperty("os.name").startsWith("Windows")) {
                // hack to deal with JFileChooser slowness on windows
                // java 8 only :(
                com.sun.java.swing.plaf.windows.WindowsFileChooserUI.createUI(fileChooser).installUI(fileChooser);
            }*/
        }
        return fileChooser;
    }
}
