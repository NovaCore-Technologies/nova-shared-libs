package com.nova.core.domain.events;

import com.nova.core.domain.base.DomainEvent;
import java.util.List;

public interface EventBus {
  void publish(DomainEvent event);
  default void publishAll(List<DomainEvent> events) {
    if (events == null || events.isEmpty()) return;
    events.forEach(this::publish);
  }
}
