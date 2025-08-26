package com.nova.shared.security.exception;

public class UnauthorizedException extends SecurityException {
    public UnauthorizedException(String msg) { super(msg); }
}
