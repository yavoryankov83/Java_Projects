package com.telerikacademy.workItemManagement.commands.changingCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.*;
import com.telerikacademy.workItemManagement.models.enums.StoryStatusType;

import java.util.Arrays;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class ChangeStoryStatus implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;

  private ModelsRepository modelsRepository;

  public ChangeStoryStatus(ModelsRepository modelsRepository) {
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
    String status = parameters[1].toUpperCase();

    return changeStoryStatus(storyId, status);
  }

  private String changeStoryStatus(long storyId, String status) {
    if (!modelsRepository.getAllStories().containsKey(storyId)) {
      throw new IllegalArgumentException(String.format(STORY_NOT_EXISTS_MESSAGE, storyId));
    }
    if (!isStatusValidStoryStatusType(status)) {
      throw new IllegalArgumentException(String.format(INVALID_STORY_STATUS_TYPE_MESSAGE, status));
    }

    Statusable story = modelsRepository.getAllStories().get(storyId);

    story.changeStatus(status);
    story.addToActivityHistory(String.format(STORY_CHANGE_STATUS_SUCCESS_MESSAGE, storyId, status));

    return String.format(STORY_CHANGE_STATUS_SUCCESS_MESSAGE, storyId, status);
  }

  private boolean isStatusValidStoryStatusType(String status) {
    return Arrays.stream(StoryStatusType.values())
            .anyMatch(value -> value.toString().equalsIgnoreCase(status));
  }
}