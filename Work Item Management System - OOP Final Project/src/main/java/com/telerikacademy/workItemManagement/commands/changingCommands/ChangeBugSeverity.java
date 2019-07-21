package com.telerikacademy.workItemManagement.commands.changingCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.Bug;
import com.telerikacademy.workItemManagement.models.enums.SeverityType;

import java.util.Arrays;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class ChangeBugSeverity implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;

  private ModelsRepository modelsRepository;

  public ChangeBugSeverity(ModelsRepository modelsRepository) {
    this.modelsRepository = modelsRepository;
  }

  @Override
  public String execute(String... parameters) {
    Validator.validateCommandArgumentsCount(
            parameters,
            CORRECT_NUMBER_OF_ARGUMENTS,
            String.format(
                    INVALID_NUMBER_OF_ARGUMENTS_MESSAGE,
                    CORRECT_NUMBER_OF_ARGUMENTS,
                    parameters.length));

    long bugId = Long.parseLong(parameters[0]);
    String severity = parameters[1].toUpperCase();

    return changeBugSeverity(bugId, severity);
  }

  private String changeBugSeverity(long bugId, String severity) {
    if (!modelsRepository.getAllBugs().containsKey(bugId)) {
      throw new IllegalArgumentException(String.format(BUG_NOT_EXIST_MESSAGE, bugId));
    }

    if (!isSeverityValidSeverityType(severity)) {
      throw new IllegalArgumentException(String.format(INVALID_SEVERITY_TYPE_MESSAGE, severity));
    }

    Bug bug = modelsRepository.getAllBugs().get(bugId);

    bug.changeSeverity(SeverityType.valueOf(severity));
    bug.addToActivityHistory(String.format(BUG_CHANGE_SEVERITY_SUCCESS_MESSAGE, bugId, severity));

    return String.format(BUG_CHANGE_SEVERITY_SUCCESS_MESSAGE, bugId, severity);
  }

  private boolean isSeverityValidSeverityType(String severity) {
    return Arrays.stream(SeverityType.values())
            .anyMatch(value -> value.toString().equalsIgnoreCase(severity));
  }
}
