package encryptdecrypt;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CryptographyFactory {
    private CryptographyFactory() {
    }

    public static Cryptography createCryptography(String algorithm, int key) {
        switch (algorithm) {
            case "shift":
                return new AlphabetShiftCryptography(key);
            case "unicode":
                return new UnicodeShiftCryptography(key);
            default:
                return null;
        }
    }

    public static void runCryptographyFromArguments(String[] args) throws IllegalArgumentException, IOException {
        String algorithmArg = OptionalArgumentParser.parseArgument(args, "-alg");
        String dataArg = OptionalArgumentParser.parseArgument(args, "-data");
        String inputArg = OptionalArgumentParser.parseArgument(args, "-in");
        String keyArg = OptionalArgumentParser.parseArgument(args, "-key");
        String modeArg = OptionalArgumentParser.parseArgument(args, "-mode");
        String outputArg = OptionalArgumentParser.parseArgument(args, "-out");

        /* Missing values check */
        if (algorithmArg != null && algorithmArg.isEmpty()) {
            throw new IllegalArgumentException("Missing value for parameter -alg");
        }
        if (dataArg != null && dataArg.isEmpty()) {
            throw new IllegalArgumentException("Missing value for parameter -data");
        }
        if (inputArg != null && inputArg.isEmpty()) {
            throw new IllegalArgumentException("Missing value for parameter -in");
        }
        if (keyArg != null && keyArg.isEmpty()) {
            throw new IllegalArgumentException("Missing value for parameter -key");
        }
        if (modeArg != null && modeArg.isEmpty()) {
            throw new IllegalArgumentException("Missing value for parameter -mode");
        }
        if (outputArg != null && outputArg.isEmpty()) {
            throw new IllegalArgumentException("Missing value for parameter -out");
        }

        /* Invalid values check */
        if (keyArg != null && !keyArg.matches("[0-9]+")) {
            throw new IllegalArgumentException("Value for -key must be a number");
        }
        if (modeArg != null && !modeArg.equals("enc") && !modeArg.equals("dec")) {
            throw new IllegalArgumentException("Value for -mode must be: enc | dec");
        }
        if (algorithmArg != null && !algorithmArg.equals("shift") && !algorithmArg.equals("unicode")) {
            throw new IllegalArgumentException("Value for -mode must be: shift | unicode");
        }

        /* Sets received values and default values */
        int key = keyArg != null ? Integer.parseInt(keyArg) : 0;
        PrintStream output = outputArg != null ? new PrintStream(outputArg) : System.out;
        String algorithm = algorithmArg != null ? algorithmArg : "shift";
        String data = dataArg != null ? dataArg : inputArg != null ? new String(Files.readAllBytes(Paths.get(inputArg))) : "";
        String mode = modeArg != null ? modeArg : "enc";

        /* Runs the cryptography */
        Cryptography cryptography = createCryptography(algorithm, key);
        assert cryptography != null;

        switch (mode) {
            case "enc":
                output.println(cryptography.encrypt(data));
                break;
            case "dec":
                output.println(cryptography.decrypt(data));
                break;
        }

        if (!output.equals(System.out)) {
            output.close();
        }
    }
}
