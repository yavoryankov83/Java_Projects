package com.telerikacademy.workItemManagement.commands.showCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.WorkItem;

import java.util.Collection;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.INVALID_NUMBER_OF_ARGUMENTS_MESSAGE;
import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.LIST_WORK_ITEM_EMPTY_MESSAGE;

public class ListAllWorkItems implements Command {

  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;

  private ModelsRepository modelsRepository;

  public ListAllWorkItems(ModelsRepository modelsRepository) {
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

    return listAllWorkItems();
  }

  private String listAllWorkItems() {
    if (modelsRepository.getAllWorkItems().isEmpty()) {
      return LIST_WORK_ITEM_EMPTY_MESSAGE;
    }

    Collection<WorkItem> workItems = modelsRepository.getAllWorkItems().values();

    StringBuilder builder = new StringBuilder();
    workItems.forEach(workItem -> builder.append(String.format("%s%n", workItem.getAdditionalInfo())));

    return builder.toString();
  }
}
