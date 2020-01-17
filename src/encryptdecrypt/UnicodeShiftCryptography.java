package encryptdecrypt;

public class UnicodeShiftCryptography extends Cryptography {

    public UnicodeShiftCryptography(int key) {
        super(key);
    }

    @Override
    public String encrypt(String data) {
        char[] encrypted = data.toCharArray();

        for (int i = 0; i < encrypted.length; i++) {
            encrypted[i] += key;
        }

        return String.valueOf(encrypted);
    }

    @Override
    public String decrypt(String data) {
        char[] decrypted = data.toCharArray();

        for (int i = 0; i < decrypted.length; i++) {
            decrypted[i] -= key;
        }

        return String.valueOf(decrypted);
    }
}
