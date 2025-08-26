package com.nova.shared.validation;

import java.util.List;

public record ValidationError(String message, List<Violation> violations) {
    public static ValidationError of(String message, List<Violation> violations) {
        return new ValidationError(message, violations);
    }
}
