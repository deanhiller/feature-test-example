package org.webpieces.app.example1.exception;

public class AuthenticationException extends RuntimeException {
  public AuthenticationException(String message) {
    super(message);
  }
}
