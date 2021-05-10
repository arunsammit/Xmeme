package com.crio.starter.exceptions;

public class MemeNotFoundException extends Exception {

  public MemeNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public MemeNotFoundException(String message) {
    super(message);
  }
  
}
