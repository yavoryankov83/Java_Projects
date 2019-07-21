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

public class ListAllWorkItemsSortedBySize implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;

  private ModelsRepository modelsRepository;

  public ListAllWorkItemsSortedBySize(ModelsRepository modelsRepository) {
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

    return listAllWorkItemsSortedBySize();
  }

  private String listAllWorkItemsSortedBySize() {
    if (modelsRepository.getAllStories().isEmpty()) {
      throw new IllegalArgumentException(NO_WORK_ITEMS_OF_TYPE_STORY_MESSAGE);
    }

    Collection<Story> stories = modelsRepository.getAllStories().values();
    List<Story> storiesSortedBySize = stories
            .stream()
            .sorted(Comparator.comparing(Story::getSize))
            .collect(Collectors.toList());

    StringBuilder builder = new StringBuilder();
    storiesSortedBySize.forEach(story -> builder.append(
            String.format("Work item %s with title %s - Size: %s%n",
                    story.getType(),
                    story.getTitle(),
                    story.getSize())));

    return builder.toString();
  }
}
