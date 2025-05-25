package dev.hachemi.mystipass.security;

import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import static dev.hachemi.mystipass.util.Constant.CRYPTO_ALGORITHM;
import static dev.hachemi.mystipass.util.Constant.KEY_DERIVATION_ALGORITHM;

@Service
public class EncryptionEngine {

    public String encrypt(String plainText, String master) {
        var salt = PasswordHelper.readSalt();

        String encoded = "";
        SecretKey secretKey = new SecretKeySpec(deriveKey(master, salt), CRYPTO_ALGORITHM);
        try {
            Cipher cipher = Cipher.getInstance(CRYPTO_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            encoded = Base64.getEncoder().encodeToString(encrypted);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException e) {
            System.out.println(e.getMessage());
        }
        return encoded;
    }

    public String decrypt(String encrypted, String master) {
        var salt = PasswordHelper.readSalt();

        String plainText = "";
        SecretKey secretKey = new SecretKeySpec(deriveKey(master, salt), CRYPTO_ALGORITHM);
        try {
            Cipher cipher = Cipher.getInstance(CRYPTO_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decoded = Base64.getDecoder().decode(encrypted);
            byte[] decrypted = cipher.doFinal(decoded);
            plainText = new String(decrypted, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException e) {
            System.out.println(e.getMessage() + e);
        }
        return plainText;
    }

    public byte[] deriveKey(String password, String salt) {
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(KEY_DERIVATION_ALGORITHM);
            KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            return new byte[0];
        }
    }
}
