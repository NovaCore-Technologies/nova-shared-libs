package com.nova.shared.core.domain.repository;

import com.nova.shared.core.domain.base.Identifier;
import java.util.Optional;

public interface ReadOnlyRepository<T, ID extends Identifier> {
  Optional<T> findById(ID id);
  boolean existsById(ID id);
}
