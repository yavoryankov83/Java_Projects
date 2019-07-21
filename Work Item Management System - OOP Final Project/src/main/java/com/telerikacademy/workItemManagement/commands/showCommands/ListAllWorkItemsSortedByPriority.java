package com.telerikacademy.workItemManagement.commands.showCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class ListAllWorkItemsSortedByPriority implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;

  private ModelsRepository modelsRepository;

  public ListAllWorkItemsSortedByPriority(ModelsRepository modelsRepository) {
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

    return listAllWorkItemsSortedByPriority();
  }

  private String listAllWorkItemsSortedByPriority() {
    if (modelsRepository.getAllBugs().isEmpty() && modelsRepository.getAllStories().isEmpty()) {
      return NO_WORK_ITEMS_OF_TYPE_BUG_AND_STORY_MESSAGE;
    }

    Collection<Prioritable> workItems = new ArrayList<>();

    Collection<Bug> bugs = modelsRepository.getAllBugs().values();
    Collection<Story> stories = modelsRepository.getAllStories().values();

    workItems.addAll(bugs);
    workItems.addAll(stories);

    List<Prioritable> workItemsSortedByPriority = workItems
            .stream()
            .sorted(Comparator.comparing(Prioritable::getPriority))
            .collect(Collectors.toList());

    StringBuilder builder = new StringBuilder();
    workItemsSortedByPriority.forEach(workItem -> builder.append(
            String.format("Work item %s with title %s - Priority: %s%n",
                    workItem.getType(),
                    workItem.getTitle(),
                    workItem.getPriority())));

    return builder.toString();
  }
}
