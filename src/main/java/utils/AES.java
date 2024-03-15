package utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AES {
    public static String encrypt(String plaintext, String key) throws Exception {
        if (key.length() < 16) {
            while (key.length() < 16) {
                key += " ";
            }
        } else if (key.length() > 16) {
            key = key.substring(0, 16);
        }

        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String ciphertext, String key) throws Exception {
        if (key.length() < 16) {
            while (key.length() < 16) {
                key += " ";
            }
        } else if (key.length() > 16) {
            key = key.substring(0, 16);
        }

        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] original = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(original);
    }

    public static void main(String[] args) {
        try {
            String plaintext = "Hello, world!";
            String key = "asd";

            // Verschlüsselung
            String ciphertext = encrypt(plaintext, key);
            System.out.println("Ciphertext: " + ciphertext);

            // Entschlüsselung
            String decryptedText = decrypt(ciphertext, key);
            System.out.println("Decrypted Text: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}