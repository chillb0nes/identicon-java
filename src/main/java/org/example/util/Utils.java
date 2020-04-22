package org.example.util;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Common operations utilities.
 */
public final class Utils {

    private Utils() {
    }

    /**
     * Provide SHA-256 {@link MessageDigest} instance.
     *
     * @return SHA-256 {@link MessageDigest} instance.
     */
    public static MessageDigest sha256() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            // SHA-256 is guaranteed to be supported, so exception will never be thrown
            throw new IllegalStateException(e);
        }
    }

    /**
     * Save image to file.
     *
     * @param image Image to save.
     * @param file Output file.
     */
    public static void writeImageToFile(Image image, File file) {
        try {
            BufferedImage bi = new BufferedImage(
                    image.getWidth(null),
                    image.getHeight(null),
                    BufferedImage.TYPE_INT_ARGB
            );
            Graphics2D g2d = bi.createGraphics();
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();
            ImageIO.write(bi, "png", file);
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage() + '\n' +
                               e.getCause().getLocalizedMessage());
        }
    }

    /**
     * Create new {@link PNGImageSelection} instance.
     *
     * @param image Constructor argument.
     * @return {@link PNGImageSelection} instance
     */
    public static PNGImageSelection pngImageSelection(Image image) {
        return new PNGImageSelection(image);
    }

    /**
     * Util class for image with an alpha channel handling.
     */
    static class PNGImageSelection implements Transferable {
        private static final DataFlavor imagePng = new DataFlavor("image/png", "PNG");

        private final Image image;

        public PNGImageSelection(Image image) {
            this.image = image;
        }

        /** {@inheritDoc} */
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[] {imagePng};
        }

        /** {@inheritDoc} */
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return imagePng.equals(flavor);
        }

        /** {@inheritDoc} */
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
            if (!imagePng.equals(flavor)) {
                throw new UnsupportedFlavorException(flavor);
            }
            return image;
        }
    }
}
