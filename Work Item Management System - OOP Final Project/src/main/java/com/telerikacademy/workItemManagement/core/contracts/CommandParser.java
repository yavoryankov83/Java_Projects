package com.telerikacademy.workItemManagement.core.contracts;

public interface CommandParser {
  String parseCommand(String fullCommand);

  String[] parseParameters(String fullCommand);
}
