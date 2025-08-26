package com.nova.shared.validation;

public record Violation(String field, String code, String message, Object rejectedValue) {
    public static Violation of(String field, String code, String message, Object rejectedValue) {
        return new Violation(field, code, message, rejectedValue);
    }
}
