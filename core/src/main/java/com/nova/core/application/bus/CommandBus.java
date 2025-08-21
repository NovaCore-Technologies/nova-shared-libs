package com.nova.core.application.bus;

public interface CommandBus {
    void dispatch(Command command);
  }