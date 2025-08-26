package com.nova.shared.security.jwt;

import com.nova.shared.security.core.AuthenticatedUser;
import com.nova.shared.security.exception.InvalidTokenException;
import com.nova.shared.security.exception.TokenExpiredException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.SignatureException;

import java.security.Key;
import java.time.Clock;
import java.time.Instant;
import java.util.*;

public final class JwtService {
    private final JwtConfig config;
    private final KeyProvider keys;
    private final Clock clock;

    public JwtService(JwtConfig config, KeyProvider keys, Clock clock) {
        this.config = Objects.requireNonNull(config);
        this.keys = Objects.requireNonNull(keys);
        this.clock = Objects.requireNonNull(clock);
    }

    /** Crea un JWT con claims estándar + custom claims del usuario. */
    public String createToken(AuthenticatedUser user, Map<String, Object> extraClaims) {
        Instant now = clock.instant();
        Instant exp = now.plus(config.defaultTtl());
        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", user.getUserId());
        claims.put("usr", user.getUsername());
        if (user.getTenantId() != null) claims.put("ten", user.getTenantId());
        if (!user.getRoles().isEmpty()) claims.put("roles", user.getRoles());
        if (!user.getPermissions().isEmpty()) claims.put("perms", user.getPermissions());
        if (!user.getAttributes().isEmpty()) claims.put("attrs", user.getAttributes());
        if (extraClaims != null) claims.putAll(extraClaims);

        return Jwts.builder()
                .header().add("typ", "JWT").and()
                .issuer(config.issuer())
                .audience().add(config.audience()).and()
                .subject(user.getUserId())
                .id(UUID.randomUUID().toString())
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .claims(claims)
                .signWith(keys.signingKey(), Jwts.SIG.get(keys.jwsAlgorithm()))
                .compact();
    }

    /** Valida firma, issuer, aud, exp y retorna claims útiles. */
    public ParsedToken parseAndValidate(String token) {
        try {
            JwtParser parser = Jwts.parser()
                    .requireIssuer(config.issuer())
                    .requireAudience(config.audience())
                    .clock(() -> Date.from(clock.instant()))
                    .clockSkewSeconds(config.allowedClockSkew().toSeconds())
                    .verifyWith((Key) keys.verificationKey())
                    .build();
            Jws<Claims> jws = parser.parseSignedClaims(token);
            Claims c = jws.getPayload();

            // Expiración verificada por parser; verificamos explícitamente para mapear excepción
            if (c.getExpiration() != null && c.getExpiration().toInstant().isBefore(clock.instant())) {
                throw new TokenExpiredException("Token expired");
            }

            AuthenticatedUser user = AuthenticatedUser.builder()
                    .userId(c.get("uid", String.class) != null ? c.get("uid", String.class) : c.getSubject())
                    .username(c.get("usr", String.class))
                    .tenantId(c.get("ten", String.class))
                    .roles(asSet(c.get("roles")))
                    .permissions(asSet(c.get("perms")))
                    .attributes(asMap(c.get("attrs")))
                    .build();

            return new ParsedToken(user,
                    Optional.ofNullable(c.getId()).orElse(null),
                    Optional.ofNullable(c.getIssuedAt()).map(Date::toInstant).orElse(null),
                    Optional.ofNullable(c.getExpiration()).map(Date::toInstant).orElse(null));
        } catch (ExpiredJwtException eje) {
            throw new TokenExpiredException("Token expired");
        } catch (JwtException | IllegalArgumentException e) {
            if (e instanceof SignatureException) {
                throw new InvalidTokenException("Invalid signature", e);
            }
            throw new InvalidTokenException("Invalid token", e);
        }
    }

    @SuppressWarnings("unchecked")
    private static Set<String> asSet(Object v) {
        if (v == null) return Collections.emptySet();
        if (v instanceof Collection<?> col) {
            Set<String> s = new HashSet<>();
            for (Object o : col) if (o != null) s.add(String.valueOf(o));
            return s;
        }
        return Set.of(String.valueOf(v));
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> asMap(Object v) {
        if (v == null) return Collections.emptyMap();
        if (v instanceof Map<?, ?> m) {
            Map<String, Object> out = new HashMap<>();
            for (Map.Entry<?, ?> e : m.entrySet())
                if (e.getKey()!=null) out.put(String.valueOf(e.getKey()), e.getValue());
            return out;
        }
        return Map.of();
    }

    public record ParsedToken(
            AuthenticatedUser user,
            String jti,
            Instant issuedAt,
            Instant expiresAt
    ) {}
}
