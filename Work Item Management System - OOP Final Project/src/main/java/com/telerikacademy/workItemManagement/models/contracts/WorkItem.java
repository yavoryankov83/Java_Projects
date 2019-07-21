package com.telerikacademy.workItemManagement.models.contracts;

import java.util.List;

public interface WorkItem {
  long getId();

  String getTitle();

  String getDescription();

  List<Comment> getComments();

  void addComment(Comment comment);

  List<String> getActivityHistory();

  void addToActivityHistory(String change);

  String showActivityHistory();

  String getType();

  String getAdditionalInfo();
}
