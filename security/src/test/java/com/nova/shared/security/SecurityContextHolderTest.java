package com.nova.shared.security;

import com.nova.shared.security.core.AuthenticatedUser;
import com.nova.shared.security.core.SecurityContextHolder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SecurityContextHolderTest {

    @Test
    void set_and_clear() {
        var user = AuthenticatedUser.builder().userId("u1").username("a").build();
        SecurityContextHolder.set(user);
        assertThat(SecurityContextHolder.current()).isPresent();
        SecurityContextHolder.clear();
        assertThat(SecurityContextHolder.current()).isEmpty();
    }
}
