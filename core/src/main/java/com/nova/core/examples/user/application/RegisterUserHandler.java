package com.nova.core.examples.user.application;

import com.nova.core.application.UseCase;
import com.nova.core.application.bus.CommandHandler;
import com.nova.core.domain.events.EventBus;
import com.nova.core.examples.user.domain.User;
import com.nova.core.examples.user.domain.UserRepository;

@UseCase
public class RegisterUserHandler implements CommandHandler<RegisterUserCommand> {
  private final UserRepository repository;
  private final EventBus eventBus;

  public RegisterUserHandler(UserRepository repository, EventBus eventBus) {
    this.repository = repository;
    this.eventBus = eventBus;
  }

  @Override
  public void handle(RegisterUserCommand command) {
    var user = User.register(command.email(), command.name());
    repository.save(user);
    eventBus.publishAll(user.pullDomainEvents());
  }
}
