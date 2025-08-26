package com.nova.shared.validation.validators;

import com.nova.shared.validation.ErrorCode;
import com.nova.shared.validation.annotations.NotBlankTrimmed;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotBlankTrimmedValidator implements ConstraintValidator<NotBlankTrimmed, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        boolean ok = !value.trim().isEmpty();
        if (!ok) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorCode.NOT_BLANK_TRIMMED.name())
                   .addConstraintViolation();
        }
        return ok;
    }
}
