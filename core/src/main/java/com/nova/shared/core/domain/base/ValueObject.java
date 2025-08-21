package com.nova.shared.core.domain.base;

import java.util.Objects;

public abstract class ValueObject {
  protected static <T> T notNull(T value, String message) {
    return Objects.requireNonNull(value, message);
  }
}
