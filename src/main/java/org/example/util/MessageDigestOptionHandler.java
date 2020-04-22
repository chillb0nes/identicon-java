package org.example.util;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionDef;
import org.kohsuke.args4j.spi.Getter;
import org.kohsuke.args4j.spi.OneArgumentOptionHandler;
import org.kohsuke.args4j.spi.OptionHandler;
import org.kohsuke.args4j.spi.Setter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.List;
import java.util.Set;

/**
 * Custom implementation of {@link OptionHandler} to handle {@link MessageDigest} options.
 */
public class MessageDigestOptionHandler extends OneArgumentOptionHandler<MessageDigest> {

    public MessageDigestOptionHandler(CmdLineParser parser,
                                      OptionDef option,
                                      Setter<? super MessageDigest> setter) {
        super(parser, option, setter);
    }

    /** {@inheritDoc} */
    @Override
    protected MessageDigest parse(String algorithm) throws CmdLineException {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            String msg = L10N.getString(L10N.Error.MESSAGE_DIGEST_UNAVAILABLE);
            Set<String> algorithms = Security.getAlgorithms("MessageDigest");
            throw new CmdLineException(owner, String.format(msg, algorithm, algorithms), e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public String printDefaultValue() {
        if (this.setter instanceof Getter) {
            Getter<MessageDigest> getter = (Getter) setter;
            List<MessageDigest> defaultValues = getter.getValueList();
            if (defaultValues != null && !defaultValues.isEmpty()) {
                return defaultValues.get(0).getAlgorithm();
            }
        }
        return null;
    }
}
