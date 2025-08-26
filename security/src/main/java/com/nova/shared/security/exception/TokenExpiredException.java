package com.nova.shared.security.exception;

public class TokenExpiredException extends SecurityException {
    public TokenExpiredException(String msg) { super(msg); }
}
