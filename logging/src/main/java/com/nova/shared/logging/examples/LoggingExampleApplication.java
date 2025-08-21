package com.nova.shared.logging.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.nova"})
public class LoggingExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoggingExampleApplication.class, args);
    }
}
