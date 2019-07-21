package com.telerikacademy.workItemManagement.commands.showCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class ListAllWorkItemsSortedBySeverity implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;

  private ModelsRepository modelsRepository;

  public ListAllWorkItemsSortedBySeverity(ModelsRepository modelsRepository) {
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

    return listAllWorkItemsSortedBySeverity();
  }

  private String listAllWorkItemsSortedBySeverity() {
    if (modelsRepository.getAllBugs().isEmpty()) {
      return NO_WORK_ITEMS_OF_TYPE_BUG_MESSAGE;
    }

    Collection<Bug> bugs = modelsRepository.getAllBugs().values();
    List<Bug> bugsSortedBySeverity = bugs
            .stream()
            .sorted(Comparator.comparing(Bug::getSeverity))
            .collect(Collectors.toList());

    StringBuilder builder = new StringBuilder();
    bugsSortedBySeverity.forEach(bug -> builder.append(
            String.format("Work item %s with title %s - Severity: %s%n",
                    bug.getType(),
                    bug.getTitle(),
                    bug.getSeverity())));

    return builder.toString();
  }
}
