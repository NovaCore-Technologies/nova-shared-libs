package com.nova.core.domain.base;

import java.util.Objects;

public abstract class Entity<ID extends Identifier> {
  protected final ID id;

  protected Entity(ID id) {
    this.id = Objects.requireNonNull(id, "id cannot be null");
  }

  public ID id() { return id; }
}
