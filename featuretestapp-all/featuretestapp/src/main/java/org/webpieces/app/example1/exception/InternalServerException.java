package org.webpieces.app.example1.exception;

public class InternalServerException extends RuntimeException {
  public InternalServerException(String message) {
    super(message);
  }

  public InternalServerException(Exception cause) {
    super(cause);
  }
}
