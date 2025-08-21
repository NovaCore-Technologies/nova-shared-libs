package com.nova.shared.logging.logger;

import com.nova.shared.logging.context.CorrelationIdHolder;
import org.slf4j.Logger;

import java.util.Map;

public final class LoggerFactory {

    private LoggerFactory() {}

    public static StructuredLogger getLogger(Class<?> clazz) {
        Logger slf4jLogger = org.slf4j.LoggerFactory.getLogger(clazz);
        return new StructuredLogger(slf4jLogger);
    }

    public static class StructuredLogger {
        private final Logger logger;

        StructuredLogger(Logger logger) {
            this.logger = logger;
        }

        public void info(String message, Object... args) {
            logger.info(appendCorrelationId(message), args);
        }

        public void warn(String message, Object... args) {
            logger.warn(appendCorrelationId(message), args);
        }

        public void error(String message, Object... args) {
            logger.error(appendCorrelationId(message), args);
        }

        public void debug(String message, Object... args) {
            logger.debug(appendCorrelationId(message), args);
        }

        public void trace(String message, Object... args) {
            logger.trace(appendCorrelationId(message), args);
        }

        public void structured(String message, Map<String, Object> fields) {
            logger.info(appendCorrelationId(message) + " | fields={}", fields);
        }

        private String appendCorrelationId(String message) {
            String correlationId = CorrelationIdHolder.get();
            return "[correlationId=" + (correlationId != null ? correlationId : "N/A") + "] " + message;
        }
    }
}
