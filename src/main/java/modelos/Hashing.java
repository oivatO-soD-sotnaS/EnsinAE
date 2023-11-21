package modelos;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
    public static String hash256(String text) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] shaByteArr = mDigest.digest(text.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexStrBuilder = new StringBuilder();
        for (byte b : shaByteArr) {
            hexStrBuilder.append(String.format("%02x", b));
        }
        return hexStrBuilder.toString();
    }
}
