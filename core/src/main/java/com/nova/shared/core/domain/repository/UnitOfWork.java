package com.nova.shared.core.domain.repository;

public interface UnitOfWork {
  void begin();
  void commit();
  void rollback();
}
