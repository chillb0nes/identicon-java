package org.example.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Localization constants holder.
 */
public interface L10N {

    static ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("l10n");
    }

    default String localized() {
        return getResourceBundle().getString(value());
    }

    default String value() {
        return (getClass().getSimpleName() + "." + name()).toLowerCase(Locale.ROOT);
    }

    String name();

    enum Usage implements L10N {
        DESCRIPTION,
        OPTIONS;

        public static final String ALGORITHM = "usage.algorithm";
        public static final String OUTPUT = "usage.output";
        public static final String SIZE = "usage.size";
        public static final String IMAGE_SIZE = "usage.image_size";
        public static final String ITERATIONS = "usage.iterations";
        public static final String BORDER = "usage.border";
        public static final String HEADLESS = "usage.headless";
    }

    class MetaVar {
        public static final String ALGORITHM = "metaVar.algorithm";
        public static final String FILE = "metaVar.file";
        public static final String N = "metaVar.n";

        private MetaVar() {
        }
    }

    enum Error implements L10N {
        MIN_IDENTICON_SIZE,
        MAX_IDENTICON_SIZE,
        MESSAGE_DIGEST_UNAVAILABLE,
        NULL_INPUT
    }
}
