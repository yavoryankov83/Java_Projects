package com.telerikacademy.workItemManagement.commands.changingCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.Story;
import com.telerikacademy.workItemManagement.models.enums.SizeType;

import java.util.Arrays;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class ChangeStorySize implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;

  private ModelsRepository modelsRepository;

  public ChangeStorySize(ModelsRepository modelsRepository) {
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
    String size = parameters[1].toUpperCase();

    return changeStorySize(storyId, size);
  }

  private String changeStorySize(long storyId, String size) {
    if (!modelsRepository.getAllStories().containsKey(storyId)) {
      throw new IllegalArgumentException(String.format(STORY_NOT_EXISTS_MESSAGE, storyId));
    }
    if (!isSizeValidSizeType(size)) {
      throw new IllegalArgumentException(String.format(INVALID_SIZE_TYPE_MESSAGE, size));
    }

    Story story = modelsRepository.getAllStories().get(storyId);
    story.changeSize(SizeType.valueOf(size));
    story.addToActivityHistory(String.format(STORY_CHANGE_SIZE_SUCCESS_MESSAGE, storyId, size));

    return String.format(STORY_CHANGE_SIZE_SUCCESS_MESSAGE, storyId, size);
  }

  private boolean isSizeValidSizeType(String size) {
    return Arrays.stream(SizeType.values())
            .anyMatch(value -> value.toString().equalsIgnoreCase(size));
  }
}