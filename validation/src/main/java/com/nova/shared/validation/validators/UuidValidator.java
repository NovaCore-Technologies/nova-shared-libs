package com.nova.shared.validation.validators;

import com.nova.shared.validation.ErrorCode;
import com.nova.shared.validation.annotations.Uuid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.UUID;

public class UuidValidator implements ConstraintValidator<Uuid, String> {
    private boolean allowNull;

    @Override
    public void initialize(Uuid constraintAnnotation) {
        this.allowNull = constraintAnnotation.allowNull();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return allowNull;
        try {
            UUID.fromString(value);
            return true;
        } catch (IllegalArgumentException ex) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorCode.INVALID_UUID.name())
                   .addConstraintViolation();
            return false;
        }
    }
}
