package com.nova.core.domain.common;

public final class Preconditions {
  private Preconditions() {}
  public static void check(boolean condition, String message) {
    if (!condition) throw new IllegalArgumentException(message);
  }
}
