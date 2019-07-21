package com.telerikacademy.workItemManagement.core.providers;

import com.telerikacademy.workItemManagement.core.contracts.CommandParser;

public class CommandParserImpl implements CommandParser {
  @Override
  public String parseCommand(String fullCommand) {
    return fullCommand.split("\\s+")[0];
  }

  @Override
  public String[] parseParameters(String fullCommand) {
    String[] commandParts = fullCommand.split("\\s+");

    String[] parameters = new String[commandParts.length - 1];

    System.arraycopy(commandParts, 1, parameters, 0, commandParts.length - 1);

    return parameters;
  }
}
