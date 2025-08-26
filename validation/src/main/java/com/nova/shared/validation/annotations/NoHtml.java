package com.nova.shared.validation.annotations;

import com.nova.shared.validation.validators.NoHtmlValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NoHtmlValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface NoHtml {
    String message() default "{nova.validation.noHtml}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
