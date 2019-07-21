package com.telerikacademy.workItemManagement.commands.showCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class ListAllWorkItemsFilteredByAssignee implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;

  private ModelsRepository modelsRepository;

  public ListAllWorkItemsFilteredByAssignee(ModelsRepository modelsRepository) {
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

    String assigneeName = parameters[0];

    return listAllWorkItemsFilteredByAssignee(assigneeName);
  }

  private String listAllWorkItemsFilteredByAssignee(String assigneeName) {
    if (modelsRepository.getAllBugs().isEmpty() && modelsRepository.getAllStories().isEmpty()) {
      return NO_WORK_ITEMS_OF_TYPE_BUG_AND_STORY_MESSAGE;
    }
    if (!modelsRepository.getAllMembers().containsKey(assigneeName)) {
      return String.format(WORK_ITEM_WITH_ASSIGNEE_NAME_NOT_EXISTS_MESSAGE, assigneeName);
    }

    Collection<Assignable> assignables = new ArrayList<>();

    Collection<Bug> bugs = modelsRepository.getAllBugs().values();
    List<Bug> bugsFilteredByAssigneeName = bugs.
            stream().
            filter(bug -> bug.getAssignee().getName().equals(assigneeName))
            .collect(Collectors.toList());

    Collection<Story> stories = modelsRepository.getAllStories().values();
    List<Story> storiesFilteredByAssigneeName = stories.
            stream().
            filter(story -> story.getAssignee().getName().equals(assigneeName))
            .collect(Collectors.toList());

    assignables.addAll(bugsFilteredByAssigneeName);
    assignables.addAll(storiesFilteredByAssigneeName);

    StringBuilder builder = new StringBuilder();
    builder.append(String.format("Work items with assignee name %s:%n", assigneeName));

    assignables.forEach(workItem -> builder.append(String.format("%s with ID %d and title %s.%n",
            workItem.getType(),
            workItem.getId(),
            workItem.getTitle())));

    return builder.toString();
  }
}
