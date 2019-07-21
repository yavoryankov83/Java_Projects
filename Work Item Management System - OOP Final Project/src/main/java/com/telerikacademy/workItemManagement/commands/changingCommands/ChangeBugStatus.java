package com.telerikacademy.workItemManagement.commands.changingCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.*;
import com.telerikacademy.workItemManagement.models.enums.BugStatusType;

import java.util.Arrays;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class ChangeBugStatus implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;

  private ModelsRepository modelsRepository;

  public ChangeBugStatus(ModelsRepository modelsRepository) {
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
    String status = parameters[1].toUpperCase();

    return changeBugStatus(bugId, status);
  }

  private String changeBugStatus(long bugId, String status) {
    if (!modelsRepository.getAllBugs().containsKey(bugId)) {
      throw new IllegalArgumentException(String.format(BUG_NOT_EXIST_MESSAGE, bugId));
    }
    if (!isStatusValidBugStatusType(status)) {
      throw new IllegalArgumentException(String.format(INVALID_BUG_STATUS_TYPE_MESSAGE, status));
    }

    Statusable bug = modelsRepository.getAllBugs().get(bugId);

    bug.changeStatus(status);
    bug.addToActivityHistory(String.format(BUG_CHANGE_STATUS_SUCCESS_MESSAGE, bugId, status));

    return String.format(BUG_CHANGE_STATUS_SUCCESS_MESSAGE, bugId, status);
  }

  private boolean isStatusValidBugStatusType(String status) {
    return Arrays.stream(BugStatusType.values())
            .anyMatch(value -> value.toString().equalsIgnoreCase(status));
  }
}
