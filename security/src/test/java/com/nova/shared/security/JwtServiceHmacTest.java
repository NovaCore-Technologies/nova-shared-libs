package com.nova.shared.security;

import com.nova.shared.security.core.AuthenticatedUser;
import com.nova.shared.security.jwt.*;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JwtServiceHmacTest {

    @Test
    void roundtrip_hs256_ok() {
        var fixedNow = Instant.parse("2025-01-01T00:00:00Z");
        var clock = Clock.fixed(fixedNow, ZoneOffset.UTC);
        var cfg = new JwtConfig("novaerp.ai", "nova", Duration.ofMinutes(15), Duration.ofSeconds(30));
        var kp = new HmacKeyProvider("this-is-a-super-secret-key-at-least-32-chars");
        var svc = new JwtService(cfg, kp, clock);

        var user = AuthenticatedUser.builder()
                .userId("u1").username("alice").tenantId("t1")
                .roles(Set.of("ADMIN")).permissions(Set.of("users:read"))
                .attributes(Map.of("locale", "es-AR"))
                .build();

        String token = svc.createToken(user, Map.of("custom", "v"));
        var parsed = svc.parseAndValidate(token);

        assertThat(parsed.user().getUsername()).isEqualTo("alice");
        assertThat(parsed.user().getPermissions()).contains("users:read");
        assertThat(parsed.jti()).isNotBlank();
        assertThat(parsed.expiresAt()).isEqualTo(fixedNow.plus(Duration.ofMinutes(15)));
    }

    @Test
    void expired_token_throws() {
        var fixedNow = Instant.parse("2025-01-01T00:00:00Z");
        var clock = Clock.fixed(fixedNow, ZoneOffset.UTC);
        var cfg = new JwtConfig("novaerp.ai", "nova", Duration.ofSeconds(1), Duration.ofSeconds(0));
        var kp = new HmacKeyProvider("this-is-a-super-secret-key-at-least-32-chars");
        var svc = new JwtService(cfg, kp, clock);

        var user = AuthenticatedUser.builder().userId("u1").username("alice").build();
        String token = svc.createToken(user, null);

        // avanzar clock artificialmente 2s
        var clock2 = Clock.fixed(fixedNow.plusSeconds(2), ZoneOffset.UTC);
        var svc2 = new JwtService(cfg, kp, clock2);

        assertThrows(com.nova.shared.security.exception.TokenExpiredException.class,
                () -> svc2.parseAndValidate(token));
    }
}
