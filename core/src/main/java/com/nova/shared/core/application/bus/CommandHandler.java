package com.nova.shared.core.application.bus;

public interface CommandHandler<C extends Command> {
  void handle(C command);
}