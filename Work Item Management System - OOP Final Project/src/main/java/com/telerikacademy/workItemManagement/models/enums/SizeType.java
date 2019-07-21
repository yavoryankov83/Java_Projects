package com.telerikacademy.workItemManagement.models.enums;

public enum SizeType {
  LARGE,
  MEDIUM,
  SMALL;

  @Override
  public String toString() {
    return name().charAt(0) + name().substring(1).toLowerCase();
  }
}
