package com.telerikacademy.workItemManagement.commands.changingCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.*;
import com.telerikacademy.workItemManagement.models.enums.PriorityType;

import java.util.Arrays;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class ChangeStoryPriority implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;

  private ModelsRepository modelsRepository;

  public ChangeStoryPriority(ModelsRepository modelsRepository) {
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

    long storyId = Long.parseLong(parameters[0]);
    String priority = parameters[1].toUpperCase();

    return changeStoryPriority(storyId, priority);
  }

  private String changeStoryPriority(long storyId, String priority) {
    if (!modelsRepository.getAllStories().containsKey(storyId)) {
      throw new IllegalArgumentException(String.format(STORY_NOT_EXISTS_MESSAGE, storyId));
    }
    if (!isPriorityValidPriorityType(priority)) {
      throw new IllegalArgumentException(String.format(INVALID_PRIORITY_TYPE_MESSAGE, priority));
    }

    Prioritable story = modelsRepository.getAllStories().get(storyId);

    story.changePriority(PriorityType.valueOf(priority));
    story.addToActivityHistory(String.format(STORY_CHANGE_PRIORITY_SUCCESS_MESSAGE, storyId, priority));

    return String.format(STORY_CHANGE_PRIORITY_SUCCESS_MESSAGE, storyId, priority);
  }

  private boolean isPriorityValidPriorityType(String priority) {
    return Arrays.stream(PriorityType.values())
            .anyMatch(value -> value.toString().equalsIgnoreCase(priority));
  }
}
