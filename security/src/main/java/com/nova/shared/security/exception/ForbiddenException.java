package com.nova.shared.security.exception;

public class ForbiddenException extends SecurityException {
    public ForbiddenException(String msg) { super(msg); }
}
