package com.nova.shared.security.core;

import java.util.Optional;

public final class SecurityContextHolder implements SecurityContext {
    private static final ThreadLocal<AuthenticatedUser> HOLDER = new ThreadLocal<>();

    private SecurityContextHolder() {}

    private static final SecurityContext INSTANCE = new SecurityContextHolder();

    public static SecurityContext get() { return INSTANCE; }

    public static void set(AuthenticatedUser user) { HOLDER.set(user); }
    public static Optional<AuthenticatedUser> current() { return Optional.ofNullable(HOLDER.get()); }
    public static void clear() { HOLDER.remove(); }

    @Override public Optional<AuthenticatedUser> currentUser() { return current(); }
    @Override public void set(AuthenticatedUser user) { set(user); }
    @Override public void clear() { clear(); }
}
