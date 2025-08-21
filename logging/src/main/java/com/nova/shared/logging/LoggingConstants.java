package com.nova.shared.logging;

public final class LoggingConstants {

    private LoggingConstants() {}

    public static final String CORRELATION_ID_HEADER = "X-Correlation-Id";
    public static final String DEFAULT_LOG_PATTERN =
            "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n";

    public static final String STRUCTURED_LOG_PATTERN =
            "{\"timestamp\":\"%d{ISO8601}\",\"level\":\"%p\",\"thread\":\"%t\",\"logger\":\"%c\",\"message\":%m,\"correlationId\":\"%X{X-Correlation-Id}\"}%n";
}
