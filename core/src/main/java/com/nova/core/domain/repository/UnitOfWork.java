package com.nova.core.domain.repository;

public interface UnitOfWork {
  void begin();
  void commit();
  void rollback();
}
