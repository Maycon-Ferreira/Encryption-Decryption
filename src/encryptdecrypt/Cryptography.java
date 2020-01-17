package encryptdecrypt;

public abstract class Cryptography {
    protected int key;

    protected Cryptography(int key) {
        this.key = key;
    }

    protected abstract String encrypt(String data);

    protected abstract String decrypt(String data);
}
