package com.nova.core.domain.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AggregateRoot<ID extends Identifier> extends Entity<ID> {
  private final List<DomainEvent> domainEvents = new ArrayList<>();

  protected AggregateRoot(ID id) { super(id); }

  protected void record(DomainEvent event) { domainEvents.add(event); }

  public List<DomainEvent> pullDomainEvents() {
    var copy = List.copyOf(domainEvents);
    domainEvents.clear();
    return copy;
  }

  public List<DomainEvent> peekDomainEvents() {
    return Collections.unmodifiableList(domainEvents);
  }
}
