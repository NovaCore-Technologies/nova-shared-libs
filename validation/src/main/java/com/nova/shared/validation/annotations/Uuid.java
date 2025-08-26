package com.nova.shared.validation.annotations;

import com.nova.shared.validation.validators.UuidValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UuidValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Uuid {
    boolean allowNull() default false;
    String message() default "{nova.validation.uuid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
