package com.nova.shared.core.examples.user.application;

import com.nova.shared.core.application.bus.Command;

public record RegisterUserCommand(String email, String name) implements Command {}
