package com.nova.shared.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.List;

public final class ValidatorUtils {

    private static final Validator VALIDATOR =
            Validation.buildDefaultValidatorFactory().getValidator();

    private ValidatorUtils() {}

    /** Valida y lanza ValidationException si hay violaciones. */
    public static <T> T validateOrThrow(T target) {
        var violations = VALIDATOR.validate(target);
        if (!violations.isEmpty()) {
            List<Violation> mapped = violations.stream()
                    .map(ValidatorUtils::map)
                    .toList();
            throw new ValidationException("Validation failed", mapped);
        }
        return target;
    }

    private static Violation map(ConstraintViolation<?> cv) {
        String field = cv.getPropertyPath() != null ? cv.getPropertyPath().toString() : null;
        String code = ErrorCode.CONSTRAINT_VIOLATION.name();
        String message = cv.getMessage();
        Object rejected = cv.getInvalidValue();
        return Violation.of(field, code, message, rejected);
    }
}
