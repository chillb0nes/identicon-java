package org.example;

import org.example.gui.GUIBootstrapper;
import org.example.util.L10N;
import org.example.util.MessageDigestOptionHandler;
import org.example.util.Utils;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.util.ResourceBundle;

/**
 * Generate identicon of specified size by hashing user input string with
 * specified message digest algorithm and show preview or save generated
 * image to file directly.
 *
 * @see IdenticonGenerator
 * @see GUIBootstrapper
 */
public class IdenticonGeneratorApp implements Runnable {

    //region program arguments
    @Argument(required = true, hidden = true)
    private String input;

    @Option(name = "-a",
            aliases = "--algorithm",
            metaVar = L10N.MetaVar.ALGORITHM,
            usage = L10N.Usage.ALGORITHM,
            handler = MessageDigestOptionHandler.class
    )
    private MessageDigest messageDigest = Utils.sha256();

    @Option(name = "-o",
            aliases = "--output",
            metaVar = L10N.MetaVar.FILE,
            usage = L10N.Usage.OUTPUT
    )
    private File output;

    @Option(name = "-s",
            aliases = "--size",
            metaVar = L10N.MetaVar.N,
            usage = L10N.Usage.SIZE
    )
    private int size = 5;

    @Option(name = "-i",
            aliases = "--image-size",
            metaVar = L10N.MetaVar.N,
            usage = L10N.Usage.IMAGE_SIZE
    )
    private int imageSize = 250;

    @Option(name = "-n",
            aliases = "--iterations",
            metaVar = L10N.MetaVar.N,
            usage = L10N.Usage.ITERATIONS
    )
    private int iterations = 1;

    @Option(name = "-b",
            aliases = "--border",
            usage = L10N.Usage.BORDER
    )
    private boolean border = true;

    @Option(name = "-g",
            aliases = "--no-gui",
            usage = L10N.Usage.HEADLESS
    )
    private boolean headless = GraphicsEnvironment.isHeadless();

    @Option(name = "-h",
            aliases = "--help",
            help = true,
            hidden = true
    )
    private boolean help;
    //endregion

    private final String usage;

    public static void main(String... args) {
        new IdenticonGeneratorApp(args).run();
    }

    public IdenticonGeneratorApp(String... args) {
        CmdLineParser parser = new CmdLineParser(this);
        usage = getUsage(parser);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            printUsageAndExit(2, e.getLocalizedMessage());
        }
        if (help) {
            printUsageAndExit(0, null);
        }
    }

    // Create usage before actual parsing to preserve default values.
    private static String getUsage(CmdLineParser parser) {
        StringWriter sw = new StringWriter();
        ResourceBundle rb = L10N.getResourceBundle();
        parser.printUsage(sw, rb);
        return "Usage: [java -jar] identicon[.jar] [OPTIONS] <input>\n\n" +
                L10N.Usage.DESCRIPTION + "\n\n" +
                L10N.Usage.OPTIONS + ":\n" +
                sw;
    }

    private void printUsageAndExit(int status, String message) {
        if (message != null) {
            System.err.println(message);
            System.err.println();
        }
        System.err.println(usage);
        System.exit(status);
    }

    @Override
    public void run() {
        if (input == null) {
            printUsageAndExit(2, L10N.Error.NULL_INPUT.localized());
        }
        IdenticonGenerator generator = new IdenticonGenerator(size, border);
        for (int i = 0; i < iterations; i++) {
            messageDigest.update(input.getBytes());
        }
        Image identicon = generator.getIdenticon(messageDigest.digest());
        Image scaledImage = identicon.getScaledInstance(imageSize, imageSize, Image.SCALE_FAST);
        if (!headless) {
            new GUIBootstrapper().start(scaledImage);
        }
        if (output != null) {
            Utils.writeImageToFile(scaledImage, output);
        }
    }
}
