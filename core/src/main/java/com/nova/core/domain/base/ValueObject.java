package com.nova.core.domain.base;

import java.util.Objects;

public abstract class ValueObject {
  protected static <T> T notNull(T value, String message) {
    return Objects.requireNonNull(value, message);
  }
}
