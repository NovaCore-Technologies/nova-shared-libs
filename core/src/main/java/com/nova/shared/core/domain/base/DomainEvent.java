package com.nova.shared.core.domain.base;

import java.time.Instant;

public interface DomainEvent {
  String eventName();
  Instant occurredOn();
  String aggregateId(); // para trazabilidad
}
