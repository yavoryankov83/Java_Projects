package com.telerikacademy.workItemManagement.commands.changingCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.*;
import com.telerikacademy.workItemManagement.models.enums.PriorityType;

import java.util.Arrays;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class ChangeBugPriority implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;

  private ModelsRepository modelsRepository;

  public ChangeBugPriority(ModelsRepository modelsRepository) {
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
    String priority = parameters[1].toUpperCase();

    return changeBugPriority(bugId, priority);
  }

  private String changeBugPriority(long bugId, String priority) {
    if (!modelsRepository.getAllBugs().containsKey(bugId)) {
      throw new IllegalArgumentException(String.format(BUG_NOT_EXIST_MESSAGE, bugId));
    }
    if (!isPriorityValidPriorityType(priority)) {
      throw new IllegalArgumentException(String.format(INVALID_PRIORITY_TYPE_MESSAGE, priority));
    }

    Prioritable bug = modelsRepository.getAllBugs().get(bugId);

    bug.changePriority(PriorityType.valueOf(priority));
    bug.addToActivityHistory(String.format(BUG_CHANGE_PRIORITY_SUCCESS_MESSAGE, bugId, priority));

    return String.format(BUG_CHANGE_PRIORITY_SUCCESS_MESSAGE, bugId, priority);
  }

  private boolean isPriorityValidPriorityType(String priority) {
    return Arrays.stream(PriorityType.values())
            .anyMatch(value -> value.toString().equalsIgnoreCase(priority));
  }
}
