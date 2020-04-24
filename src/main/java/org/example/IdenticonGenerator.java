package org.example;

import org.example.util.L10N;
import org.example.util.Utils;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.security.MessageDigest;

/**
 * Converts a string input to a 32-bit digest and generates an identicon for this digest.
 */
public class IdenticonGenerator {
    /** Identicon size. */
    private final int size;
    /** Border size (1 or 0). */
    private final int border;
    /** End position of color data in byte array. */
    private final int colorOffset;

    /**
     * Default constructor with border = true.
     *
     * @see IdenticonGenerator#IdenticonGenerator(int, boolean)
     */
    public IdenticonGenerator(int size) {
        this(size, true);
    }

    /**
     * Default constructor.
     *
     * @param size Identicon size (Max size = 8).
     */
    public IdenticonGenerator(int size, boolean border) {
        if (size < 1) {
            String msg = L10N.getString(L10N.Error.MIN_IDENTICON_SIZE);
            throw new IllegalArgumentException(msg + " = 1");
        }
        if (size > 8) {
            String msg = L10N.getString(L10N.Error.MAX_IDENTICON_SIZE);
            throw new IllegalArgumentException(msg + " = 8");
        }
        this.size = size;
        this.border = border ? 1 : 0;
        this.colorOffset = 32 - size * 4;
    }

    /**
     * Generate identicon from user data.
     * <p>
     * 1. Hash user data with SHA-256 digest algorithm.
     * 2. Get color from first {@code 32 - size * 4} bytes.
     * 3. Generate symmetrical image with given size from rest of user data.
     * <p>
     * Whether the pixel will be colored is determined if the byte value is greater than zero.
     *
     * @param input User data (String).
     * @return Identicon image.
     */
    public Image getIdenticon(byte[] input) {
        byte[] digest = getDigest(input);

        Color color = getColor(digest);

        int imageSize = size + border * 2;
        BufferedImage image = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_ARGB);

        int width = (size + 1) / 2;
        for (int i = border; i < width; i++) {
            for (int j = border; j < imageSize - border; j++) {
                int x = i - border;
                int y = j - border;
                int pos = colorOffset + x * width + y;
                if (isColor(digest[pos])) {
                    image.setRGB(i, j, color.getRGB());
                    image.setRGB(imageSize - i - 1, j, color.getRGB());
                }
            }
        }
        return image;
    }

    private byte[] getDigest(byte[] input) {
        MessageDigest sha256 = Utils.sha256();
        sha256.update(input);
        return sha256.digest();
    }

    private Color getColor(byte[] data) {
        int r = 0;
        int g = 0;
        int b = 0;

        if (colorOffset > 0) {
            int blockSize = colorOffset / 3;
            for (int i = 0; i < blockSize; i++) {
                r += data[i] + 128;
                g += data[i + blockSize] + 128;
                b += data[i + blockSize * 2] + 128;
            }

            //todo something with extra 1-2 bytes

            r /= blockSize;
            g /= blockSize;
            b /= blockSize;
        }

        return new Color(r, g, b);
    }

    private boolean isColor(byte value) {
        return value > 0;
    }

}
