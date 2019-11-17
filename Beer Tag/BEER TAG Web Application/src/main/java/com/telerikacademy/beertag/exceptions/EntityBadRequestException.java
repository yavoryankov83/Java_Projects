package com.telerikacademy.beertag.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityBadRequestException extends RuntimeException {

  public EntityBadRequestException(String message) {
    super(message);
  }
}
