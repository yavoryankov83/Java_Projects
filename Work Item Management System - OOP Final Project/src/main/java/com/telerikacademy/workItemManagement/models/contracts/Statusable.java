package com.telerikacademy.workItemManagement.models.contracts;

public interface Statusable extends WorkItem {
  String getStatus();

  void changeStatus(String status);
}
