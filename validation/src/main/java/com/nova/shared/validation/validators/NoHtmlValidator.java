package com.nova.shared.validation.validators;

import com.nova.shared.validation.ErrorCode;
import com.nova.shared.validation.annotations.NoHtml;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoHtmlValidator implements ConstraintValidator<NoHtml, String> {
    private static final String HTML_PATTERN = ".*<[^>]+>.*";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        boolean ok = !value.matches(HTML_PATTERN);
        if (!ok) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorCode.NO_HTML_ALLOWED.name())
                   .addConstraintViolation();
        }
        return ok;
    }
}
