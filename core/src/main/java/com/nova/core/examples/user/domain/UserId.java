package com.nova.core.examples.user.domain;

import com.nova.core.domain.base.Identifier;

public final class UserId extends Identifier {
  public UserId(String value) { super(value); }
  public static UserId newId() { return new UserId(Identifier.newUUID()); }
}
