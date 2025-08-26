package com.nova.shared.validation.spring;

import java.time.OffsetDateTime;
import java.util.List;

import com.nova.shared.validation.Violation;

public record ProblemDetails(
        String type,
        String title,
        int status,
        String detail,
        String instance,
        OffsetDateTime timestamp,
        List<Violation> violations
) {
    public static ProblemDetails of(int status, String detail, List<Violation> violations, String instance) {
        return new ProblemDetails(
                "about:blank",
                "Validation Error",
                status,
                detail,
                instance,
                OffsetDateTime.now(),
                violations
        );
    }
}
