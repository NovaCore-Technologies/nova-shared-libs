package com.nova.shared.core.examples.user.infrastructure;

import com.nova.shared.core.domain.base.DomainEvent;
import com.nova.shared.core.domain.events.EventBus;
import java.util.ArrayList;
import java.util.List;

public class FakeEventBus implements EventBus {
  private final List<DomainEvent> published = new ArrayList<>();

  @Override
  public void publish(DomainEvent event) { published.add(event); }

  public List<DomainEvent> publishedEvents() { return List.copyOf(published); }
}
