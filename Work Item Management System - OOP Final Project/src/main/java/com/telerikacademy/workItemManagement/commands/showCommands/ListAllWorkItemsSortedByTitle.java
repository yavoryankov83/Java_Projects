package com.telerikacademy.workItemManagement.commands.showCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.WorkItem;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.INVALID_NUMBER_OF_ARGUMENTS_MESSAGE;
import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.NO_WORK_ITEMS_EXISTS_MESSAGE;

public class ListAllWorkItemsSortedByTitle implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;

  private ModelsRepository modelsRepository;

  public ListAllWorkItemsSortedByTitle(ModelsRepository modelsRepository) {
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

    return listAllWorkItemsSortedByTitle();
  }

  private String listAllWorkItemsSortedByTitle() {
    if (modelsRepository.getAllWorkItems().isEmpty()) {
      return NO_WORK_ITEMS_EXISTS_MESSAGE;
    }

    Collection<WorkItem> workItems = modelsRepository.getAllWorkItems().values();
    List<WorkItem> workItemsSortedByTitle = workItems
            .stream()
            .sorted(Comparator.comparing(WorkItem::getTitle))
            .collect(Collectors.toList());

    StringBuilder builder = new StringBuilder();
    workItemsSortedByTitle.forEach(workItem -> builder.append(String.format("%s, ", workItem.getTitle())));

    return builder.toString().substring(0, builder.length() - 2);
  }
}
