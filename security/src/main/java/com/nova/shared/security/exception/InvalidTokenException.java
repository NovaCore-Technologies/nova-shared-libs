package com.nova.shared.security.exception;

public class InvalidTokenException extends SecurityException {
    public InvalidTokenException(String msg) { super(msg); }
    public InvalidTokenException(String msg, Throwable cause) { super(msg, cause); }
}
