package com.nova.shared.logging.context;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.UUID;

public class CorrelationIdFilter implements Filter {

    private static final String HEADER_NAME = "X-Correlation-Id";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            String correlationId = ((HttpServletRequest) request).getHeader(HEADER_NAME);
            if (correlationId == null || correlationId.isEmpty()) {
                correlationId = UUID.randomUUID().toString();
            }
            CorrelationIdHolder.set(correlationId);
            MDC.put(HEADER_NAME, correlationId);

            chain.doFilter(request, response);
        } finally {
            CorrelationIdHolder.clear();
            MDC.remove(HEADER_NAME);
        }
    }
}
