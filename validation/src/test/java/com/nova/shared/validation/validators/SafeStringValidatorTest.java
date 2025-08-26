package com.nova.shared.validation.validators;

import com.nova.shared.validation.annotations.SafeString;
import jakarta.validation.*;
import org.junit.jupiter.api.*;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SafeStringValidatorTest {

    private static Validator validator;

    static class TestBean {
        @SafeString(max = 10)
        String input;

        TestBean(String input) { this.input = input; }
    }

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldFailOnTooLong() {
        Set<ConstraintViolation<TestBean>> violations = validator.validate(new TestBean("01234567890"));
        assertThat(violations).isNotEmpty();
    }

    @Test
    void shouldFailOnHtmlInjection() {
        Set<ConstraintViolation<TestBean>> violations = validator.validate(new TestBean("<script>"));
        assertThat(violations).isNotEmpty();
    }

    @Test
    void shouldPassOnSafeInput() {
        Set<ConstraintViolation<TestBean>> violations = validator.validate(new TestBean("Valid_10"));
        assertThat(violations).isEmpty();
    }
}
