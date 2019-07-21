package com.telerikacademy.workItemManagement.commands.showCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.Story;

import java.util.Collection;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class ListWorkItemsFilteredByStory implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;

  private ModelsRepository modelsRepository;

  public ListWorkItemsFilteredByStory(ModelsRepository modelsRepository) {
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

    return listWorkItemsFilteredByStory();
  }

  private String listWorkItemsFilteredByStory() {
    if (modelsRepository.getAllStories().isEmpty()) {
      return NO_WORK_ITEMS_OF_TYPE_STORY_MESSAGE;
    }

    Collection<Story> stories = modelsRepository.getAllStories().values();

    StringBuilder builder = new StringBuilder();
    stories.forEach(story -> builder.append(
            String.format("%s%n", story)));

    return builder.toString();
  }
}
