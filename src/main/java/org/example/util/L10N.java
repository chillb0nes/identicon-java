package org.example.util;

import java.util.ResourceBundle;

/**
 * Localization constants holder.
 */
public final class L10N {
    private L10N() {
    }

    public static ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("l10n");
    }

    public static String getString(String key) {
        return getResourceBundle().getString(key);
    }

    public static final class Usage {
        public static final String DESCRIPTION = "usage.description";
        public static final String OPTIONS = "usage.options";
        public static final String ALGORITHM = "usage.algorithm";
        public static final String OUTPUT = "usage.output";
        public static final String SIZE = "usage.size";
        public static final String IMAGE_SIZE = "usage.image_size";
        public static final String ITERATIONS = "usage.iterations";
        public static final String BORDER = "usage.border";
        public static final String HEADLESS = "usage.headless";

        private Usage() {
        }
    }

    public static final class MetaVar {
        public static final String ALGORITHM = "metaVar.algorithm";
        public static final String FILE = "metaVar.file";
        public static final String N = "metaVar.n";

        private MetaVar() {
        }
    }

    public static final class Error {
        public static final String MIN_IDENTICON_SIZE = "error.min_identicon_size";
        public static final String MAX_IDENTICON_SIZE = "error.max_identicon_size";
        public static final String MESSAGE_DIGEST_UNAVAILABLE = "error.message_digest_unavailable";
        public static final String NULL_INPUT = "error.null_input";

        private Error() {
        }
    }
}
