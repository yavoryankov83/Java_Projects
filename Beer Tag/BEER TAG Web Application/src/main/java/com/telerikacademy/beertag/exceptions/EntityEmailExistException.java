package com.telerikacademy.beertag.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.MULTIPLE_CHOICES)
public class EntityEmailExistException extends RuntimeException {
  public EntityEmailExistException(String message) {
    super(message);
  }
}
