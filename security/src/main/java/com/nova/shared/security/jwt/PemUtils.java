package com.nova.shared.security.jwt;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.*;
import java.util.Base64;

final class PemUtils {
    private PemUtils(){}
    static PublicKey readRsaPublicKey(String pem) {
        try {
            String content = pem.replaceAll("-----BEGIN (.*)-----", "")
                                .replaceAll("-----END (.*)-----", "")
                                .replaceAll("\\s", "");
            byte[] der = Base64.getDecoder().decode(content);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(der);
            return KeyFactory.getInstance("RSA").generatePublic(spec);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid RSA public key PEM", e);
        }
    }
    static PrivateKey readRsaPrivateKey(String pem) {
        try {
            String content = pem.replaceAll("-----BEGIN (.*)-----", "")
                                .replaceAll("-----END (.*)-----", "")
                                .replaceAll("\\s", "");
            byte[] der = Base64.getDecoder().decode(content);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(der);
            return KeyFactory.getInstance("RSA").generatePrivate(spec);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid RSA private key PEM", e);
        }
    }
}
