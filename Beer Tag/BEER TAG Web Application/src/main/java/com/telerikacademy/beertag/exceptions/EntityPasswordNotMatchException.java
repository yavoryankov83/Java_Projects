package com.telerikacademy.beertag.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PARTIAL_CONTENT)
public class EntityPasswordNotMatchException extends RuntimeException {
  public EntityPasswordNotMatchException(String message) {
    super(message);
  }
}
