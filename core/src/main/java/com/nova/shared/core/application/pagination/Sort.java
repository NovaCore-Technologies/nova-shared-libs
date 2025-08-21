package com.nova.shared.core.application.pagination;

import java.util.List;

public record Sort(List<Order> orders) {
  public record Order(String property, Direction direction) {
    public enum Direction { ASC, DESC }
  }

  public static Sort by(Order... orders) { return new Sort(List.of(orders)); }
}
