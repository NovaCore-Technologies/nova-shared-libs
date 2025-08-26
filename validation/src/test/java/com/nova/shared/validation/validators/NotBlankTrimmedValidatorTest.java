package com.nova.shared.validation.validators;

import com.nova.shared.validation.annotations.NotBlankTrimmed;
import jakarta.validation.*;
import org.junit.jupiter.api.*;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NotBlankTrimmedValidatorTest {

    private static Validator validator;

    static class TestBean {
        @NotBlankTrimmed
        String value;

        TestBean(String value) { this.value = value; }
    }

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldFailOnBlankValue() {
        Set<ConstraintViolation<TestBean>> violations = validator.validate(new TestBean("   "));
        assertThat(violations).isNotEmpty();
    }

    @Test
    void shouldPassOnNonBlankTrimmed() {
        Set<ConstraintViolation<TestBean>> violations = validator.validate(new TestBean("hello"));
        assertThat(violations).isEmpty();
    }
}
