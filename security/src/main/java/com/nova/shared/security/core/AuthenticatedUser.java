package com.nova.shared.security.core;

import java.io.Serializable;
import java.util.*;

public final class AuthenticatedUser implements Serializable {
    private final String userId;
    private final String username;
    private final String tenantId;
    private final Set<String> roles;
    private final Set<String> permissions;
    private final Map<String, Object> attributes;

    private AuthenticatedUser(Builder b) {
        this.userId = Objects.requireNonNull(b.userId, "userId");
        this.username = Objects.requireNonNull(b.username, "username");
        this.tenantId = b.tenantId;
        this.roles = Collections.unmodifiableSet(new HashSet<>(b.roles));
        this.permissions = Collections.unmodifiableSet(new HashSet<>(b.permissions));
        this.attributes = Collections.unmodifiableMap(new HashMap<>(b.attributes));
    }

    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getTenantId() { return tenantId; }
    public Set<String> getRoles() { return roles; }
    public Set<String> getPermissions() { return permissions; }
    public Map<String, Object> getAttributes() { return attributes; }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String userId;
        private String username;
        private String tenantId;
        private Set<String> roles = new HashSet<>();
        private Set<String> permissions = new HashSet<>();
        private Map<String, Object> attributes = new HashMap<>();

        public Builder userId(String v) { this.userId = v; return this; }
        public Builder username(String v) { this.username = v; return this; }
        public Builder tenantId(String v) { this.tenantId = v; return this; }
        public Builder roles(Set<String> v) { if (v!=null) this.roles = new HashSet<>(v); return this; }
        public Builder permissions(Set<String> v) { if (v!=null) this.permissions = new HashSet<>(v); return this; }
        public Builder attributes(Map<String, Object> v) { if (v!=null) this.attributes = new HashMap<>(v); return this; }
        public AuthenticatedUser build() { return new AuthenticatedUser(this); }
    }
}
