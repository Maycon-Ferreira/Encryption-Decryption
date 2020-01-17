package encryptdecrypt;

public class AlphabetShiftCryptography extends Cryptography {

    private static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public AlphabetShiftCryptography(int key) {
        super(key);
    }

    @Override
    public String encrypt(String data) {
        char[] encrypted = data.toCharArray();

        for (int i = 0; i < encrypted.length; i++) {
            if (Character.isLowerCase(encrypted[i])) {
                encrypted[i] = alphabet[(encrypted[i] - 'a' + key) % alphabet.length];
            } else if (Character.isUpperCase(encrypted[i])) {
                encrypted[i] = Character.toUpperCase(alphabet[(encrypted[i] - 'A' + key) % alphabet.length]);
            }
        }

        return String.valueOf(encrypted);
    }

    @Override
    public String decrypt(String data) {
        char[] encrypted = data.toCharArray();

        for (int i = 0; i < encrypted.length; i++) {
            if (Character.isLowerCase(encrypted[i])) {
                encrypted[i] = alphabet[(encrypted[i] - 'a' + alphabet.length - key) % alphabet.length];
            } else if (Character.isUpperCase(encrypted[i])) {
                encrypted[i] = Character.toUpperCase(alphabet[(encrypted[i] - 'A' + alphabet.length - key) % alphabet.length]);
            }
        }

        return String.valueOf(encrypted);
    }
}
