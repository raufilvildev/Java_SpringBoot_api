package com.mi_app.api.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleUserNotFound(UserNotFoundException exception) {
    Map<String, String> response = new HashMap<>();
    response.put("message", exception.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(DuplicateUserException.class)
  public ResponseEntity<Map<String, String>> handleDuplicateUser(DuplicateUserException exception) {
    Map<String, String> response = new HashMap<>();
    response.put("message", exception.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
  }

  @ExceptionHandler(IncorrectCredentialsException.class)
  public ResponseEntity<Map<String, String>> handleIncorrectCredentials(IncorrectCredentialsException exception) {
    Map<String, String> response = new HashMap<>();
    response.put("message", exception.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException exception) {
    Map<String, String> errors = new HashMap<>();

    for (FieldError error : exception.getBindingResult().getFieldErrors()) {
      errors.put(error.getField(), error.getDefaultMessage());
    }

    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(InvalidTokenException.class)
  public ResponseEntity<Map<String, String>> handleInvalidToken(InvalidTokenException exception) {
    Map<String, String> response = new HashMap<>();
    response.put("message", exception.getMessage());
    response.put("type", exception.getType());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
  }
}
