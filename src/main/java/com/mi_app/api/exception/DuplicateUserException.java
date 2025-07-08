package com.mi_app.api.exception;

public class DuplicateUserException extends RuntimeException {

  public DuplicateUserException(String type, String value) {
    super("El usuario con " + type + " = " + value + " ya existe.");
  }

}
