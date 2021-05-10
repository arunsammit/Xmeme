package com.crio.starter.exceptions;

public class MemeNotFoundException extends RuntimeException{

  public MemeNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
  
}
