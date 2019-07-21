package com.telerikacademy.workItemManagement.models.enums;

public enum SeverityType {
  CRITICAL,
  MAJOR,
  MINOR;

  @Override
  public String toString() {
    return name().charAt(0) + name().substring(1).toLowerCase();
  }
}
