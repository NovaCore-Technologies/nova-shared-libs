package com.nova.core.domain.common;

import java.util.Objects;
import java.util.function.Function;

public sealed interface Result<T, E> permits Result.Ok, Result.Err {
  record Ok<T, E>(T value) implements Result<T, E> {}
  record Err<T, E>(E error) implements Result<T, E> {}

  static <T, E> Result<T, E> ok(T value) { return new Ok<>(value); }
  static <T, E> Result<T, E> err(E error) { return new Err<>(Objects.requireNonNull(error)); }

  default boolean isOk() { return this instanceof Ok<T,E>; }
  default boolean isErr() { return this instanceof Err<T,E>; }

  default T getOrThrow(Function<E, RuntimeException> onError) {
    return switch (this) {
      case Ok<T,E> ok -> ok.value();
      case Err<T,E> err -> throw onError.apply(err.error());
    };
  }
}
