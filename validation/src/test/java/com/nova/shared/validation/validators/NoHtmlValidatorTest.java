package com.nova.shared.validation.validators;

import com.nova.shared.validation.annotations.NoHtml;
import jakarta.validation.*;
import org.junit.jupiter.api.*;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NoHtmlValidatorTest {

    private static Validator validator;

    static class TestBean {
        @NoHtml
        String content;

        TestBean(String content) { this.content = content; }
    }

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldFailOnHtmlContent() {
        Set<ConstraintViolation<TestBean>> violations = validator.validate(new TestBean("<b>bad</b>"));
        assertThat(violations).isNotEmpty();
    }

    @Test
    void shouldPassOnPlainText() {
        Set<ConstraintViolation<TestBean>> violations = validator.validate(new TestBean("just text"));
        assertThat(violations).isEmpty();
    }
}
