// SecurityContextImpl.java
package com.nova.shared.security.core;

public class SecurityContextImpl implements SecurityContext {
    private AuthenticatedUser user;

    @Override
    public AuthenticatedUser getUser() {
        return user;
    }

    @Override
    public void setUser(AuthenticatedUser user) {
        this.user = user;
    }

    @Override
    public void clear() {
        this.user = null;
    }
}
