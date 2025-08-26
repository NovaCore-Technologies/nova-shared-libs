package com.nova.shared.validation.annotations;

import com.nova.shared.validation.validators.NotBlankTrimmedValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotBlankTrimmedValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlankTrimmed {
    String message() default "{nova.validation.notBlankTrimmed}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
