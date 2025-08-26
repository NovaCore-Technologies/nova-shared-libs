package com.nova.shared.validation.validators;

import com.nova.shared.validation.ErrorCode;
import com.nova.shared.validation.annotations.SafeString;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SafeStringValidator implements ConstraintValidator<SafeString, String> {

    private int max;
    private boolean allowNewlines;

    // Letras, números, espacios y puntuación básica. Si allowNewlines=false bloquea \r?\n
    private String basePattern;

    @Override
    public void initialize(SafeString annotation) {
        this.max = annotation.max();
        this.allowNewlines = annotation.allowNewlines();
        String newline = allowNewlines ? "\\r?\\n" : "";
        // evita controles, HTML, y caracteres poco seguros
        this.basePattern = "^[\\p{L}\\p{N} .,_\\-@#\\$%\\^&\\*\\(\\)!\\?\\/:;\\+\\|\\[\\]\"'`~" + newline + "]{0," + max + "}$";
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        boolean ok = value.length() <= max && value.matches(basePattern) && !value.matches(".*<[^>]+>.*");
        if (!ok) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorCode.UNSAFE_STRING.name())
                   .addConstraintViolation();
        }
        return ok;
    }
}
