package com.nova.shared.security.jwt;

import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public final class HmacKeyProvider implements KeyProvider {
    private final Key key;

    public HmacKeyProvider(String secret) {
        if (secret == null || secret.length() < 32) {
            throw new IllegalArgumentException("HMAC secret must be at least 32 characters");
        }
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override public Key signingKey() { return key; }
    @Override public Key verificationKey() { return key; }
    @Override public String jwsAlgorithm() { return "HS256"; }
}
