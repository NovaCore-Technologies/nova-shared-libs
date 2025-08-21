package com.nova.shared.core.examples.user.infrastructure;

import com.nova.shared.core.examples.user.domain.*;
import java.util.*;

public class InMemoryUserRepository implements UserRepository {
  private final Map<UserId, User> storage = new HashMap<>();

  @Override
  public Optional<User> findById(UserId id) { return Optional.ofNullable(storage.get(id)); }

  @Override
  public boolean existsById(UserId id) { return storage.containsKey(id); }

  @Override
  public User save(User aggregate) {
    storage.put(aggregate.id(), aggregate);
    return aggregate;
  }

  @Override
  public List<User> saveAll(List<User> aggregates) {
    aggregates.forEach(u -> storage.put(u.id(), u));
    return aggregates;
  }

  @Override
  public void deleteById(UserId id) { storage.remove(id); }
}
