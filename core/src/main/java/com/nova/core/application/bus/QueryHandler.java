package com.nova.core.application.bus;

public interface QueryHandler<Q extends Query<R>, R> {
    R handle(Q query);
  }