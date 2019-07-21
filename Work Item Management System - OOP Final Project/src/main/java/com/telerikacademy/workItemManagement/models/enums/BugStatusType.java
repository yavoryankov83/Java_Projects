package com.telerikacademy.workItemManagement.models.enums;

public enum BugStatusType {
  ACTIVE,
  FIXED;

  @Override
  public String toString() {
    return name().charAt(0) + name().substring(1).toLowerCase();
  }
}
