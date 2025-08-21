package com.nova.shared.logging.examples.service;

import com.nova.shared.logging.logger.LoggerFactory;
import com.nova.shared.logging.logger.LoggerFactory.StructuredLogger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final StructuredLogger log = LoggerFactory.getLogger(UserService.class);

    public Map<String, Object> findUser(String id) {
        log.debug("Looking up user in DB (simulated) id={}", id);

        Map<String, Object> user = new HashMap<>();
        user.put("id", id);
        user.put("name", "Test User");

        log.info("User found successfully");
        return user;
    }

    public Map<String, Object> createUser(Map<String, Object> payload) {
        log.info("Persisting new user {}", payload);
        payload.put("id", "generated-12345");

        log.info("User created with id={}", payload.get("id"));
        return payload;
    }
}
