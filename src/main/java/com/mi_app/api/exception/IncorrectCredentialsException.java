package com.mi_app.api.exception;

public class IncorrectCredentialsException extends RuntimeException {

  public IncorrectCredentialsException() {
    super("Credenciales incorrectas.");
  }

}
