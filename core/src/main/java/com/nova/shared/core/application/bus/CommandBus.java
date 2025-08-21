package com.nova.shared.core.application.bus;

public interface CommandBus {
    void dispatch(Command command);
  }