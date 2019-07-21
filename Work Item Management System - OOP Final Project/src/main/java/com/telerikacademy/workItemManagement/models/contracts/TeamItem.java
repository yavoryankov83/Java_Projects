package com.telerikacademy.workItemManagement.models.contracts;

import java.util.List;

public interface TeamItem {
  String getName();

  List<WorkItem> getWorkItems();

  List<String> getActivityHistory();

  void addWorkItem(WorkItem workItem);

  void removeWorkItem(WorkItem workItem);

  void addToActivityHistory(String message);

  String showActivityHistory();

  String getType();
}
