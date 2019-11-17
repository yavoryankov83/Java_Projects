package com.telerikacademy.beertag.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {IllegalArgumentException.class})
  protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
    if (ex instanceof EntityNotFoundException) {
      return handleEntityNotFoundException((EntityNotFoundException) ex, request);
    }
    if (ex instanceof EntityConflictException) {
      return handleEntityConflictException((EntityConflictException) ex, request);
    }
    if (ex instanceof EntityBadRequestException) {
      return handleEntityBadRequestException((EntityBadRequestException) ex, request);
    }
    return handleEntityException((EntityException) ex, request);
  }

  private ResponseEntity<Object> handleEntityNotFoundException(
          EntityNotFoundException ex,
          WebRequest request) {
    String bodyOfResponse = ex.getMessage();
    return handleExceptionInternal(ex, bodyOfResponse,
            new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  private ResponseEntity<Object> handleEntityConflictException(
          EntityConflictException ex,
          WebRequest request) {
    String bodyOfResponse = ex.getMessage();
    return handleExceptionInternal(ex, bodyOfResponse,
            new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  private ResponseEntity<Object> handleEntityBadRequestException(
          EntityBadRequestException ex,
          WebRequest request) {
    String bodyOfResponse = ex.getMessage();
    return handleExceptionInternal(ex, bodyOfResponse,
            new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  private ResponseEntity<Object> handleEntityException(
          EntityException ex,
          WebRequest request) {
    String bodyOfResponse = ex.getMessage();
    return handleExceptionInternal(ex, bodyOfResponse,
            new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT, request);
  }

}
