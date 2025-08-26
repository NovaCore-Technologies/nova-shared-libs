package com.nova.shared.security.jwt;

import java.util.Optional;

public final class JwtAuthenticationExtractor {
    private JwtAuthenticationExtractor(){}

    public static Optional<String> fromAuthorizationHeader(String header) {
        if (header == null) return Optional.empty();
        String h = header.trim();
        if (h.length() < 8) return Optional.empty();
        int space = h.indexOf(' ');
        if (space > 0 && h.substring(0, space).equalsIgnoreCase("Bearer")) {
            return Optional.of(h.substring(space + 1).trim()).filter(s -> !s.isEmpty());
        }
        return Optional.empty();
    }
}
