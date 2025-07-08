package com.mi_app.api.exception;

public class InvalidTokenException extends RuntimeException {

  public InvalidTokenException(String message) {
    super("Token de autenticación inválido." + message);
  }

}
