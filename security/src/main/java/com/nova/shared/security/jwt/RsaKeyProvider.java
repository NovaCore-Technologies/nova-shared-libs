package com.nova.shared.security.jwt;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

public final class RsaKeyProvider implements KeyProvider {
    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public RsaKeyProvider(String privateKeyPem, String publicKeyPem) {
        this.privateKey = PemUtils.readRsaPrivateKey(privateKeyPem);
        this.publicKey = PemUtils.readRsaPublicKey(publicKeyPem);
    }

    @Override public Key signingKey() { return privateKey; }
    @Override public Key verificationKey() { return publicKey; }
    @Override public String jwsAlgorithm() { return "RS256"; }
}
