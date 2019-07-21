package com.telerikacademy.workItemManagement.core;

import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.commands.enums.CommandType;
import com.telerikacademy.workItemManagement.core.contracts.*;
import com.telerikacademy.workItemManagement.core.factories.ModelsFactoryImpl;
import com.telerikacademy.workItemManagement.core.factories.CommandFactoryImpl;
import com.telerikacademy.workItemManagement.core.providers.*;
import com.telerikacademy.workItemManagement.models.common.Validator;

import java.io.IOException;
import java.util.Arrays;

public class EngineImpl implements Engine {
  private static final String TERMINATION_COMMAND = "Exit";
  private static final String COMMAND_NULL_OR_EMPTY_EXCEPTION =
          "Command cannot be null or empty.";

  private CommandFactoryImpl commandFactory;
  private ModelsFactory modelsFactory;
  private CommandParser commandParser;
  private ModelsRepository modelsRepository;
  private Writer writer;
  private Reader reader;

  public EngineImpl() {
    commandFactory = new CommandFactoryImpl();
    modelsFactory = new ModelsFactoryImpl();
    commandParser = new CommandParserImpl();
    modelsRepository = new ModelsRepositoryImpl();
    writer = new ConsoleWriter();
    reader = new ConsoleReader();
  }

  @Override
  public void start() {
    Command showCommand = commandFactory
            .createCommand("showCommands", modelsRepository, modelsFactory);

    String command = showCommand.execute();
    writer.writeLine(command);

    while (true) {
      try {
        String commandAsString = reader.readLine();
        if (commandAsString.equalsIgnoreCase(TERMINATION_COMMAND)) {
          break;
        }
        processCommand(commandAsString);

      } catch (Exception ex) {
        writer.writeLine(ex.getMessage() != null
                && !(ex.getMessage().length() == 0) ? ex.getMessage() : ex.toString());
      }
    }
  }

  private void processCommand(String commandAsString) {
    Validator.validateStringNotNullOrEmpty(commandAsString, COMMAND_NULL_OR_EMPTY_EXCEPTION);

    String commandName = commandParser.parseCommand(commandAsString);
    Command command = commandFactory.createCommand(commandName, modelsRepository, modelsFactory);
    String[] parameters = commandParser.parseParameters(commandAsString);
    String executionResult = command.execute(parameters);
    writer.writeLine(executionResult);
  }
}
