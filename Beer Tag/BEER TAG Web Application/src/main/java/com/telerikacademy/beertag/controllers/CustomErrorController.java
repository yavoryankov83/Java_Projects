package com.telerikacademy.beertag.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

  @RequestMapping(path = "error")
  public String handleError(HttpServletRequest request) {
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

    if (status != null) {
      Integer statusCode = Integer.valueOf(status.toString());

      if (statusCode == HttpStatus.NOT_FOUND.value()) {
        return "error-404";
      }
      if (statusCode == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
        return "error-beername";
      }
      if (statusCode == HttpStatus.BAD_REQUEST.value()) {
        return "error-400";
      }
      if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
        return "error-401";
      }
      if (statusCode == HttpStatus.CONFLICT.value()) {
        return "error-409";
      }
      if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
        return "error-500";
      }
      if (statusCode == HttpStatus.PARTIAL_CONTENT.value()) {
        return "error-password";
      }
      if (statusCode == HttpStatus.ALREADY_REPORTED.value()) {
        return "error-username";
      }
      if (statusCode == HttpStatus.MULTIPLE_CHOICES.value()) {
        return "error-email";
      }
    }
    return "error";
  }

  @Override
  public String getErrorPath() {
    return "error";
  }
}
