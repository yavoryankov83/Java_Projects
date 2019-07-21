package com.telerikacademy.workItemManagement.commands.common;

public final class Validator {
  private Validator() {
  }

  public static void validateCommandArgumentsCount(
          String[] commandArguments,
          int correctNumberOfArguments,
          String incorrectMessage) {
    if (commandArguments.length != correctNumberOfArguments) {
      throw new IllegalArgumentException(incorrectMessage);
    }
  }
}
