package com.nova.shared.core.domain.base;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public abstract class Identifier implements Serializable {
  private final String value;

  protected Identifier(String value) {
    this.value = Objects.requireNonNull(value, "id value cannot be null");
  }

  public String value() { return value; }

  @Override public String toString() { return value; }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Identifier that)) return false;
    return value.equals(that.value);
  }

  @Override public int hashCode() { return value.hashCode(); }

  public static String newUUID() { return UUID.randomUUID().toString(); }
}
