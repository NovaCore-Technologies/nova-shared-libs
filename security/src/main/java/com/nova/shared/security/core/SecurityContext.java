// SecurityContext.java
package com.nova.shared.security.core;

public interface SecurityContext {
    AuthenticatedUser getUser();
    void setUser(AuthenticatedUser user);
    void clear();
}
