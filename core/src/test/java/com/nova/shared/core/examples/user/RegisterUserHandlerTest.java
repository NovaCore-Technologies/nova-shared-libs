package com.nova.shared.core.examples.user;

import org.junit.jupiter.api.Test;

import com.nova.shared.core.examples.user.application.*;
import com.nova.shared.core.examples.user.domain.User;
import com.nova.shared.core.examples.user.domain.UserRegistered;
import com.nova.shared.core.examples.user.infrastructure.*;

import static org.junit.jupiter.api.Assertions.*;

class RegisterUserHandlerTest {

  @Test
  void should_register_user_and_publish_event() {
    var repository = new InMemoryUserRepository();
    var eventBus = new FakeEventBus();
    var handler = new RegisterUserHandler(repository, eventBus);

    var command = new RegisterUserCommand("john.doe@example.com", "John Doe");
    handler.handle(command);

    // Verificar que el usuario se guardó
    var savedUser = repository.findById(repository
        .save(User.register("dummy@example.com","Dummy"))
        .id()); // check storage
    assertTrue(savedUser.isPresent());

    // Verificar que el evento fue publicado
    var events = eventBus.publishedEvents();
    assertEquals(1, events.size());
    assertTrue(events.get(0) instanceof UserRegistered);
    assertEquals("user.registered", events.get(0).eventName());
  }
}
