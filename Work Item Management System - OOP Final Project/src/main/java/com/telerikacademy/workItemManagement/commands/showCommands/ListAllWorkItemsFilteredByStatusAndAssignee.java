package com.telerikacademy.workItemManagement.commands.showCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.*;
import com.telerikacademy.workItemManagement.models.enums.BugStatusType;
import com.telerikacademy.workItemManagement.models.enums.FeedbackStatusType;
import com.telerikacademy.workItemManagement.models.enums.StoryStatusType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class ListAllWorkItemsFilteredByStatusAndAssignee implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;

  private ModelsRepository modelsRepository;
  private ListAllWorkItemsFilteredByStatus filteredByStatus;

  public ListAllWorkItemsFilteredByStatusAndAssignee(ModelsRepository modelsRepository) {
    this.modelsRepository = modelsRepository;
    filteredByStatus = new ListAllWorkItemsFilteredByStatus(modelsRepository);
  }

  public String execute(String... parameters) {
    Validator.validateCommandArgumentsCount(
            parameters,
            CORRECT_NUMBER_OF_ARGUMENTS,
            String.format(
                    INVALID_NUMBER_OF_ARGUMENTS_MESSAGE,
                    CORRECT_NUMBER_OF_ARGUMENTS,
                    parameters.length));

    String status = parameters[0];
    String assigneeName = parameters[1];

    return listAllWorkItemsFilteredByStatusAndAssignee(status, assigneeName);
  }

  private String listAllWorkItemsFilteredByStatusAndAssignee(String status, String assigneeName) {
    if (!filteredByStatus.isValidStatusType(status)) {
      throw new IllegalArgumentException(String.format(NOT_VALID_STATUS_TYPE_MESSAGE, status));
    }
    if (modelsRepository.getAllWorkItems().isEmpty()) {
      return LIST_WORK_ITEM_EMPTY_MESSAGE;
    }

    Collection<Statusable> statusables = new ArrayList<>();

    Collection<Bug> bugs = modelsRepository.getAllBugs().values();
    List<Bug> bugsWithAssigneeName = bugs
            .stream()
            .filter(bug -> bug.getAssignee().getName().equals(assigneeName))
            .collect(Collectors.toList());

    Collection<Story> stories = modelsRepository.getAllStories().values();
    List<Story> storiesWithAssigneeName = stories
            .stream()
            .filter(story -> story.getAssignee().getName().equals(assigneeName))
            .collect(Collectors.toList());

    statusables.addAll(bugsWithAssigneeName);
    statusables.addAll(storiesWithAssigneeName);

    if (statusables.isEmpty()) {
      return String.format(WORK_ITEM_WITH_ASSIGNEE_NAME_NOT_EXISTS_MESSAGE, assigneeName);
    }

    List<Statusable> workItemsFilteredByAssigneeAndStatus = statusables
            .stream()
            .filter(statusable -> statusable.getStatus().equalsIgnoreCase(status))
            .collect(Collectors.toList());

    if (workItemsFilteredByAssigneeAndStatus.isEmpty()) {
      return String.format(WORK_ITEM_WITH_STATUS_NOT_EXISTS_MESSAGE, status);
    }

    StringBuilder builder = new StringBuilder();
    builder.append(String.format("Work items with status %s and assignee %s:%n",
            status,
            assigneeName));

    workItemsFilteredByAssigneeAndStatus.forEach(workItem -> builder.append(
            String.format(
                    "%s with ID %d and title %s.%n",
                    workItem.getType(),
                    workItem.getId(),
                    workItem.getTitle())));

    return builder.toString();
  }
}
