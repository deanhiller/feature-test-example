package org.webpieces.app.example1.errors;

public class JsonError {
  private String error;
  private int errorCode;

  public JsonError(String error, int errorCode) {
    this.error = error;
    this.errorCode = errorCode;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }
}
