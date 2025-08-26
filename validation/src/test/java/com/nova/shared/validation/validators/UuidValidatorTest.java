package com.nova.shared.validation.validators;

import com.nova.shared.validation.annotations.Uuid;
import jakarta.validation.*;
import org.junit.jupiter.api.*;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class UuidValidatorTest {

    private static Validator validator;

    static class TestBean {
        @Uuid
        String id;

        TestBean(String id) { this.id = id; }
    }

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldFailOnInvalidUuid() {
        Set<ConstraintViolation<TestBean>> violations = validator.validate(new TestBean("not-a-uuid"));
        assertThat(violations).isNotEmpty();
    }

    @Test
    void shouldPassOnValidUuid() {
        Set<ConstraintViolation<TestBean>> violations = validator.validate(new TestBean("123e4567-e89b-12d3-a456-426614174000"));
        assertThat(violations).isEmpty();
    }
}
