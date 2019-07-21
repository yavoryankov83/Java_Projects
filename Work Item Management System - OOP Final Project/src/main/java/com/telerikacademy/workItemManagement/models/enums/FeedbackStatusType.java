package com.telerikacademy.workItemManagement.models.enums;

public enum FeedbackStatusType {
  NEW,
  UNSCHEDULED,
  SCHEDULED,
  DONE;

  @Override
  public String toString() {
    return name().charAt(0) + name().substring(1).toLowerCase();
  }
}
