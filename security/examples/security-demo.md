# Demo de uso de Nova Shared Security

```java
var cfg = new JwtConfig("novaerp.ai", "nova", Duration.ofMinutes(10), Duration.ofSeconds(30));
var keys = new HmacKeyProvider(System.getenv("NOVA_JWT_SECRET"));
var jwt = new JwtService(cfg, keys, Clock.systemUTC());

var user = AuthenticatedUser.builder()
    .userId("uuid-123").username("bob").tenantId("tenant-7")
    .roles(Set.of("USER"))
    .permissions(Set.of("orders:read"))
    .build();

String token = jwt.createToken(user, Map.of());
var parsed = jwt.parseAndValidate(token);
SecurityContextHolder.set(parsed.user());
// ... ejecutar caso de uso ...
SecurityContextHolder.clear();
```
Para RS256 usa ```RsaKeyProvider(privatePem, publicPem)```.

---