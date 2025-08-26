package com.nova.shared.security.jwt;

import java.time.Duration;
import java.util.Objects;

public record JwtConfig(
        String issuer,
        String audience,
        Duration defaultTtl,
        Duration allowedClockSkew
) {
    public JwtConfig {
        Objects.requireNonNull(issuer, "issuer");
        Objects.requireNonNull(audience, "audience");
        Objects.requireNonNull(defaultTtl, "defaultTtl");
        Objects.requireNonNull(allowedClockSkew, "allowedClockSkew");
    }
}
