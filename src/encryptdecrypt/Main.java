package encryptdecrypt;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            CryptographyFactory.runCryptographyFromArguments(args);
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + ".");
        }
    }
}

