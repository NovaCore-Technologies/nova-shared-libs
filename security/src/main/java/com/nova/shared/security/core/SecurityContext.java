package com.nova.shared.security.core;

import java.util.Optional;

public interface SecurityContext {
    Optional<AuthenticatedUser> currentUser();
    void set(AuthenticatedUser user);
    void clear();
}
