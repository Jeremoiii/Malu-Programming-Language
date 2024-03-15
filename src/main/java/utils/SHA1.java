package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
* Erstellt einen MD5-Hash aus einem String.
*/
public class SHA1 {
    public static String sha1Hash(String input) {
        try {
            // Erstellen einer Instanz von MessageDigest mit dem SHA-1-Algorithmus
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // Die Eingabe in Bytes konvertieren und hashen
            byte[] sha1Bytes = md.digest(input.getBytes());

            // Konvertieren der Byte-Array in eine hexadezimale Zeichenfolge
            StringBuilder sb = new StringBuilder();
            for (byte b : sha1Bytes) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-1 Algorithmus nicht verfügbar.");
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String input = "Hello World";
        String sha1Hash = sha1Hash(input);
        System.out.println("SHA-1 hash von '" + input + "': " + sha1Hash);
    }
}