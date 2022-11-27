package ensf614.project.team6.cinema.application.service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class Encryptor {
    private static final String A_SALT = "!$team$6$!";

    public static String encryptPassword(String password){
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");

            byte[] key = A_SALT.getBytes(StandardCharsets.UTF_8);
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);

            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            return Base64.getEncoder().encodeToString(cipher.doFinal(password.getBytes(StandardCharsets.UTF_8)));

        } catch (Exception e) {
            //Should not happen
            e.printStackTrace();
            return password;
        }
    }
}
