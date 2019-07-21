package com.telerikacademy.workItemManagement.models.contracts;

public interface Feedback extends WorkItem, Statusable {
  int getRating();

  void changeRating(int rating);
}
