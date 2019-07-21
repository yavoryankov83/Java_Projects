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

public class ListAllWorkItemsFilteredByStatus implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;

  private ModelsRepository modelsRepository;

  public ListAllWorkItemsFilteredByStatus(ModelsRepository modelsRepository) {
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

    String status = parameters[0];

    return listAllWorkItemsFilteredByStatus(status);
  }

  private String listAllWorkItemsFilteredByStatus(String status) {
    if (!isValidStatusType(status)) {
      throw new IllegalArgumentException(String.format(NOT_VALID_STATUS_TYPE_MESSAGE, status));
    }
    if (modelsRepository.getAllWorkItems().isEmpty()) {
      return LIST_WORK_ITEM_EMPTY_MESSAGE;
    }

    Collection<Statusable> filteredStatusables = new ArrayList<>();

    Collection<Bug> bugs = modelsRepository.getAllBugs().values();
    List<Bug> filteredBugs = bugs.stream()
            .filter(bug -> bug.getStatus().equalsIgnoreCase(status))
            .collect(Collectors.toList());

    Collection<Story> stories = modelsRepository.getAllStories().values();
    List<Story> filteredStories = stories.stream()
            .filter(story -> story.getStatus().equalsIgnoreCase(status))
            .collect(Collectors.toList());

    Collection<Feedback> feedbacks = modelsRepository.getAllFeedbacks().values();
    List<Feedback> filteredFeedbacks = feedbacks.stream()
            .filter(feedback -> feedback.getStatus().equalsIgnoreCase(status))
            .collect(Collectors.toList());

    filteredStatusables.addAll(filteredBugs);
    filteredStatusables.addAll(filteredStories);
    filteredStatusables.addAll(filteredFeedbacks);


    StringBuilder builder = new StringBuilder();
    builder.append(String.format("Work items with status %s:%n", status));

    filteredStatusables.forEach(workItem -> builder.append(
            String.format("%s with ID %d and title %s.%n",
            workItem.getType(),
            workItem.getId(),
            workItem.getTitle())));

    return builder.toString();

  }

  public boolean isValidStatusType(String status) {
    boolean isBugStatusType = Arrays
            .stream(BugStatusType.values())
            .anyMatch(value -> value.toString().equalsIgnoreCase(status));

    boolean isStoryStatusType = Arrays
            .stream(StoryStatusType.values())
            .anyMatch(value -> value.toString().equalsIgnoreCase(status));

    boolean isFeedBackStatusType = Arrays
            .stream(FeedbackStatusType.values())
            .anyMatch(value -> value.toString().equalsIgnoreCase(status));

    return isBugStatusType || isStoryStatusType || isFeedBackStatusType;
  }
}
