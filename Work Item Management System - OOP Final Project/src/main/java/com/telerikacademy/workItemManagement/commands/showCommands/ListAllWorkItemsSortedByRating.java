package com.telerikacademy.workItemManagement.commands.showCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.Feedback;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class ListAllWorkItemsSortedByRating implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;

  private ModelsRepository modelsRepository;

  public ListAllWorkItemsSortedByRating(ModelsRepository modelsRepository) {
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

    return listAllWorkItemsSortedByRating();
  }

  private String listAllWorkItemsSortedByRating() {
    if (modelsRepository.getAllFeedbacks().isEmpty()) {
      return NO_WORK_ITEMS_OF_TYPE_FEEDBACK_MESSAGE;
    }

    Collection<Feedback> feedbacks = modelsRepository.getAllFeedbacks().values();
    List<Feedback> feedbacksSortedByRating = feedbacks
            .stream()
            .sorted(Comparator.comparing(Feedback::getRating).reversed())
            .collect(Collectors.toList());

    StringBuilder builder = new StringBuilder();
    feedbacksSortedByRating.forEach(feedback -> builder.append(
            String.format("Work item %s with title %s - Rating: %d%n",
                    feedback.getType(),
                    feedback.getTitle(),
                    feedback.getRating())));

    return builder.toString();
  }
}
