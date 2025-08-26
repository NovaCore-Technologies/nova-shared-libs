# Nova Shared Security

Primitivas de seguridad para microservicios Nova (hexagonal-friendly, sin dependencia de frameworks):
- **JWT** (HS256/RS256) con `JwtService` y `KeyProvider`.
- **SecurityContext** thread-local y `AuthenticatedUser`.
- **Crypto**: PBKDF2 (HmacSHA256), `constantTimeEquals`, `secureRandom`.
- **Excepciones** semánticas (`Unauthorized`, `Forbidden`, `InvalidToken`, `TokenExpired`).

## Uso rápido

```java
var config = new JwtConfig("novaerp.ai", "nova", Duration.ofMinutes(15), Duration.ofSeconds(30));
var keys = new HmacKeyProvider("super-secret-32+chars-at-least");
var jwt = new JwtService(config, keys, Clock.systemUTC());

var user = AuthenticatedUser.builder()
    .userId("u-123")
    .username("alice")
    .tenantId("t-1")
    .roles(Set.of("ADMIN"))
    .permissions(Set.of("users:read", "users:write"))
    .attributes(Map.of("locale", "es-AR"))
    .build();

String token = jwt.createToken(user, Map.of("custom", "value"));
var parsed = jwt.parseAndValidate(token); // throws InvalidToken/TokenExpired si aplica
SecurityContextHolder.set(user);
// ...
SecurityContextHolder.clear();
```
Para ver un ejemplo, ver examples/security-demo.md.

---