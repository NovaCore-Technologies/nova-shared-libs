package com.nova.core.examples.user.application;

import com.nova.core.application.bus.Command;

public record RegisterUserCommand(String email, String name) implements Command {}
