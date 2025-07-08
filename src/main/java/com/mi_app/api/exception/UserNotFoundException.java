package com.mi_app.api.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String type, String value) {
    super("El usuario con " + type + " = " + value + " no existe.");
  }

}
