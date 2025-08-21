package com.nova.core.application.pagination;

public record PageRequest(int page, int size, Sort sort) {
  public PageRequest {
    if (page < 0) throw new IllegalArgumentException("page must be >= 0");
    if (size <= 0) throw new IllegalArgumentException("size must be > 0");
  }

  public static PageRequest of(int page, int size) { return new PageRequest(page, size, null); }
  public static PageRequest of(int page, int size, Sort sort) { return new PageRequest(page, size, sort); }
}
