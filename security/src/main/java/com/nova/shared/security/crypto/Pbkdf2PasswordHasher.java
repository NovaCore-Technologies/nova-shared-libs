package com.nova.shared.security.crypto;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public final class Pbkdf2PasswordHasher implements PasswordHasher {
    private static final String ALGO = "PBKDF2WithHmacSHA256";
    private final int iterations;
    private final int saltLen;
    private final int keyLenBits;

    public Pbkdf2PasswordHasher() { this(180_000, 16, 256); } // valores seguros por defecto
    public Pbkdf2PasswordHasher(int iterations, int saltLen, int keyLenBits) {
        this.iterations = iterations;
        this.saltLen = saltLen;
        this.keyLenBits = keyLenBits;
    }

    @Override
    public String hash(char[] password) {
        byte[] salt = new byte[saltLen];
        new SecureRandom().nextBytes(salt);
        byte[] dk = derive(password, salt, iterations, keyLenBits);
        // Formato: pbkdf2$<iter>$<salt_b64>$<dk_b64>
        return "pbkdf2$" + iterations + "$" +
                Base64.getUrlEncoder().withoutPadding().encodeToString(salt) + "$" +
                Base64.getUrlEncoder().withoutPadding().encodeToString(dk);
    }

    @Override
    public boolean verify(char[] password, String encoded) {
        String[] parts = encoded.split("\\$");
        if (parts.length != 4 || !parts[0].equals("pbkdf2")) return false;
        int it = Integer.parseInt(parts[1]);
        byte[] salt = Base64.getUrlDecoder().decode(parts[2]);
        byte[] expected = Base64.getUrlDecoder().decode(parts[3]);
        byte[] actual = derive(password, salt, it, expected.length * 8);
        return CryptoUtils.constantTimeEquals(actual, expected);
    }

    private static byte[] derive(char[] password, byte[] salt, int iterations, int keyLenBits) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLenBits);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGO);
            return skf.generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new IllegalStateException("PBKDF2 derivation failed", e);
        }
    }
}
