package com.nova.shared.validation.spring;

import com.nova.shared.validation.ValidationException;
import com.nova.shared.validation.ValidationError;
import com.nova.shared.validation.Violation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ControllerAdvice
public class GlobalValidationExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalValidationExceptionHandler.class);

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ProblemDetails> handleDomainValidation(ValidationException ex) {
        log.debug("Domain validation failed: {}", ex.getMessage());
        ProblemDetails body = ProblemDetails.of(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                ex.getMessage(),
                ex.getViolations(),
                null
        );
        return ResponseEntity.unprocessableEntity()
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetails> handleBeanValidation(MethodArgumentNotValidException ex) {
        List<Violation> violations = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> Violation.of(fe.getField(), fe.getCode(), fe.getDefaultMessage(), fe.getRejectedValue()))
                .toList();
        ProblemDetails body = ProblemDetails.of(
                HttpStatus.BAD_REQUEST.value(),
                "Request validation failed",
                violations,
                ex.getParameter() != null ? ex.getParameter().getParameterName() : null
        );
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(body);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemDetails> handleConstraintViolation(ConstraintViolationException ex) {
        List<Violation> violations = ex.getConstraintViolations().stream()
                .map(cv -> Violation.of(
                        cv.getPropertyPath() != null ? cv.getPropertyPath().toString() : null,
                        cv.getConstraintDescriptor() != null ? cv.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName() : "ConstraintViolation",
                        cv.getMessage(),
                        cv.getInvalidValue()))
                .toList();
        ProblemDetails body = ProblemDetails.of(
                HttpStatus.BAD_REQUEST.value(),
                "Constraint validation failed",
                violations,
                null
        );
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(body);
    }
}
