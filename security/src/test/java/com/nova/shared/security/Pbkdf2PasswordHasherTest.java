package com.nova.shared.security;

import com.nova.shared.security.crypto.PasswordHasher;
import com.nova.shared.security.crypto.Pbkdf2PasswordHasher;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Pbkdf2PasswordHasherTest {

    @Test
    void hash_and_verify() {
        PasswordHasher hasher = new Pbkdf2PasswordHasher();
        String enc = hasher.hash("S3guro!".toCharArray());
        assertThat(enc).startsWith("pbkdf2$");
        assertThat(hasher.verify("S3guro!".toCharArray(), enc)).isTrue();
        assertThat(hasher.verify("wrong".toCharArray(), enc)).isFalse();
    }
}
