package com.telerikacademy.workItemManagement.models;

import com.telerikacademy.workItemManagement.models.contracts.Comment;
import com.telerikacademy.workItemManagement.models.contracts.Member;

public class CommentImpl implements Comment {
  private Member author;
  private String message;

  public CommentImpl(Member author, String message) {
    this.author = author;
    this.message = message;
  }

  @Override
  public Member getAuthor() {
    return author;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return String.format("Author: %s, comment: %s%n", getAuthor().getName(), getMessage());
  }
}
