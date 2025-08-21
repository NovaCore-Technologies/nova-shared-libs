package com.nova.core.domain.repository;

import com.nova.core.domain.base.Identifier;
import java.util.Optional;

public interface ReadOnlyRepository<T, ID extends Identifier> {
  Optional<T> findById(ID id);
  boolean existsById(ID id);
}
