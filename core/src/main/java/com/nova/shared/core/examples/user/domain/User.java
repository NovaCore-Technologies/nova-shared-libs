package com.nova.shared.core.examples.user.domain;

import com.nova.shared.core.domain.base.AggregateRoot;
import com.nova.shared.core.domain.common.Preconditions;

public final class User extends AggregateRoot<UserId> {
  private final String email;
  private String name;

  private User(UserId id, String email, String name) {
    super(id);
    Preconditions.check(email != null && !email.isBlank(), "email is required");
    Preconditions.check(name != null && !name.isBlank(), "name is required");

    this.email = email;
    this.name = name;
    this.record(new UserRegistered(id.value(), email));
  }

  public static User register(String email, String name) {
    return new User(UserId.newId(), email, name);
  }

  public String email() { return email; }
  public String name() { return name; }

  public void rename(String newName) {
    Preconditions.check(newName != null && !newName.isBlank(), "name cannot be empty");
    this.name = newName;
  }
}
