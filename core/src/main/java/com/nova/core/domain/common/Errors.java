package com.nova.core.domain.common;

public sealed interface Errors
  permits Errors.Validation, Errors.NotFound, Errors.Conflict, Errors.Unexpected {

  String message();

  record Validation(String message) implements Errors {}
  record NotFound(String message) implements Errors {}
  record Conflict(String message) implements Errors {}
  record Unexpected(String message) implements Errors {}
}
