package com.nova.shared.security.crypto;

import java.security.SecureRandom;

public final class CryptoUtils {
    private static final SecureRandom SR = new SecureRandom();
    private CryptoUtils(){}

    public static byte[] secureRandomBytes(int len) {
        byte[] b = new byte[len];
        SR.nextBytes(b);
        return b;
    }

    /** Constant-time equals to mitigate timing attacks. */
    public static boolean constantTimeEquals(byte[] a, byte[] b) {
        if (a == null || b == null) return false;
        if (a.length != b.length) return false;
        int result = 0;
        for (int i = 0; i < a.length; i++) result |= (a[i] ^ b[i]);
        return result == 0;
    }

    public static boolean constantTimeEquals(String a, String b) {
        if (a == null || b == null) return false;
        return constantTimeEquals(a.getBytes(java.nio.charset.StandardCharsets.UTF_8),
                                  b.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }
}
