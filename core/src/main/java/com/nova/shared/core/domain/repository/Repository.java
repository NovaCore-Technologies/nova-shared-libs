package com.nova.shared.core.domain.repository;

import com.nova.shared.core.domain.base.Identifier;
import java.util.List;

public interface Repository<T, ID extends Identifier> extends ReadOnlyRepository<T, ID> {
  T save(T aggregate);
  List<T> saveAll(List<T> aggregates);
  void deleteById(ID id);
}
