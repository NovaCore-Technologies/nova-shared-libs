// SecurityContextHolder.java
package com.nova.shared.security.core;

public final class SecurityContextHolder {

    private static final ThreadLocal<SecurityContext> CONTEXT = 
        ThreadLocal.withInitial(SecurityContextImpl::new);

    private SecurityContextHolder() {}

    public static SecurityContext getContext() {
        return CONTEXT.get();
    }

    public static void setUser(AuthenticatedUser user) {
        getContext().setUser(user);
    }

    public static AuthenticatedUser getUser() {
        return getContext().getUser();
    }

    public static void clearContext() {
        getContext().clear();
        CONTEXT.remove();
    }
}
