package com.nova.core.examples.user.domain;

import com.nova.core.domain.base.DomainEvent;
import java.time.Instant;

public record UserRegistered(String aggregateId, String email, Instant occurredOn) implements DomainEvent {
  private static final String EVENT_NAME = "user.registered";

  public UserRegistered(String aggregateId, String email) {
    this(aggregateId, email, Instant.now());
  }

  @Override
  public String eventName() { return EVENT_NAME; }

  @Override
  public Instant occurredOn() { return occurredOn; }
}
