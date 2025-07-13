package com.mi_app.api.exception;

import lombok.Getter;

@Getter
public class InvalidTokenException extends RuntimeException {
  private final String type;

  public InvalidTokenException(String type, String message) {
    super("Token de autenticación inválido." + message);
    this.type = type;
  }

}
