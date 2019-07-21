package com.telerikacademy.workItemManagement.commands.showCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.Bug;

import java.util.Collection;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class ListWorkItemsFilteredByBug implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;

  private ModelsRepository modelsRepository;

  public ListWorkItemsFilteredByBug(ModelsRepository modelsRepository) {
    this.modelsRepository = modelsRepository;
  }

  public String execute(String... parameters) {
    Validator.validateCommandArgumentsCount(
            parameters,
            CORRECT_NUMBER_OF_ARGUMENTS,
            String.format(
                    INVALID_NUMBER_OF_ARGUMENTS_MESSAGE,
                    CORRECT_NUMBER_OF_ARGUMENTS,
                    parameters.length));

    return listWorkItemsFilteredByBug();
  }

  private String listWorkItemsFilteredByBug() {
    if (modelsRepository.getAllBugs().isEmpty()) {
      return NO_WORK_ITEMS_OF_TYPE_BUG_MESSAGE;
    }

    Collection<Bug> bugs = modelsRepository.getAllBugs().values();

    StringBuilder builder = new StringBuilder();
    bugs.forEach(bug -> builder.append(String.format("%s%n", bug)));

    return builder.toString();
  }
}
