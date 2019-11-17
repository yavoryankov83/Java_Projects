package com.telerikacademy.beertag.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class EntityUserExistException extends RuntimeException {
  public EntityUserExistException(String message) {
    super(message);
  }
}
