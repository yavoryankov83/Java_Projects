package com.telerikacademy.beertag.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class EntityBeerExistException extends RuntimeException {
  public EntityBeerExistException(String message) {
    super(message);
  }
}
