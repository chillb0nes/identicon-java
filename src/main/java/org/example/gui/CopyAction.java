package org.example.gui;

import org.example.util.Utils;

import javax.swing.AbstractAction;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;

/**
 * Copy menu action.
 */
class CopyAction extends AbstractAction {
    /** Generated identicon. */
    private final Image image;

    public CopyAction(Image image) {
        super("Copy");
        this.image = image;
    }

    /**
     * Copy image to clipboard.
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(Utils.pngImageSelection(image), null);
    }
}
