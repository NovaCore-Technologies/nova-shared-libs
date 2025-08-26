package com.nova.shared.security.crypto;

public interface PasswordHasher {
    String hash(char[] password);
    boolean verify(char[] password, String encoded);
}
