package org.example.gui;

import javax.swing.JPopupMenu;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Creates and shows popup with "{@link CopyAction Copy}" and "{@link SaveAction Save}" actions.
 */
class PopupMenuHandler extends MouseAdapter {
    /** Generated identicon. */
    private final Image image;
    /** Popup menu gui component. */
    private JPopupMenu menu;

    public PopupMenuHandler(Image image) {
        this.image = image;
    }

    /** {@inheritDoc} */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            getPopup().show(e.getComponent(), e.getX(), e.getY());
        }
    }

    /** {@inheritDoc} */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            getPopup().show(e.getComponent(), e.getX(), e.getY());
        }
    }

    private JPopupMenu getPopup() {
        if (menu == null) {
            menu = new JPopupMenu();
            menu.add(new CopyAction(image));
            menu.add(new SaveAction(image));
        }
        return menu;
    }

}
