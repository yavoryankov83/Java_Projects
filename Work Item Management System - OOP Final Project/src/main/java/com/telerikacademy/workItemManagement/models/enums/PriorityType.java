package com.telerikacademy.workItemManagement.models.enums;

public enum PriorityType {
  HIGH,
  MEDIUM,
  LOW;

  @Override
  public String toString() {
    return name().charAt(0) + name().substring(1).toLowerCase();
  }
}
