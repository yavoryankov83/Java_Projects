package com.telerikacademy.workItemManagement.models.common;

public final class Validator {

  private Validator() {
  }

  public static <T extends Comparable<T>> void validateFieldMinAndMaxBoundries(
          T value,
          T min,
          T max,
          String message) {
    if (value.compareTo(min) < 0 || value.compareTo(max) > 0) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void validateForNegativeValue(int value, String message) {
    if (value < 0) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void validateStringNotNullOrEmpty(String value, String message) {
    if (value == null || value.length() == 0) {
      throw new IllegalArgumentException(message);
    }
  }
}
