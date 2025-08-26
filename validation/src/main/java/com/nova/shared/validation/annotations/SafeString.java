package com.nova.shared.validation.annotations;

import com.nova.shared.validation.validators.SafeStringValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SafeStringValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface SafeString {
    String message() default "{nova.validation.safeString}";
    int max() default 512;
    boolean allowNewlines() default false;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
