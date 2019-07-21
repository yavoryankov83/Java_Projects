package com.telerikacademy.workItemManagement.models.contracts;

public interface Comment {
  Member getAuthor();

  String getMessage();
}
