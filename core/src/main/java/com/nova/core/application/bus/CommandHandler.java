package com.nova.core.application.bus;

public interface CommandHandler<C extends Command> {
  void handle(C command);
}