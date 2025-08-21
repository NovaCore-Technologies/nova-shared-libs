package com.nova.shared.logging.examples.controller;

import com.nova.shared.logging.examples.service.UserService;
import com.nova.shared.logging.logger.LoggerFactory;
import com.nova.shared.logging.logger.LoggerFactory.StructuredLogger;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final StructuredLogger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getUser(@PathVariable String id) {
        log.info("Fetching user with id={}", id);
        return userService.findUser(id);
    }

    @PostMapping
    public Map<String, Object> createUser(@RequestBody Map<String, Object> payload) {
        log.structured("Creating user", payload);
        return userService.createUser(payload);
    }
}
