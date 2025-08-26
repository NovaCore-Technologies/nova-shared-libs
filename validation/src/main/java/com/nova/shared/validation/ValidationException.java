package com.nova.shared.validation;

import java.util.List;

public class ValidationException extends RuntimeException {

    private final transient List<Violation> violations;

    public ValidationException(String message, List<Violation> violations) {
        super(message);
        this.violations = List.copyOf(violations);
    }

    public List<Violation> getViolations() {
        return violations;
    }
}
