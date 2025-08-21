package com.nova.shared.core.application.bus;

public interface QueryBus {
    <R> R ask(Query<R> query);
}