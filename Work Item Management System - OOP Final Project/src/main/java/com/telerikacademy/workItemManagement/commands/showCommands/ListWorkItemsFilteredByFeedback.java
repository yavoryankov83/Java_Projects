package com.telerikacademy.workItemManagement.commands.showCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.Feedback;

import java.util.Collection;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class ListWorkItemsFilteredByFeedback implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;

  private ModelsRepository modelsRepository;

  public ListWorkItemsFilteredByFeedback(ModelsRepository modelsRepository) {
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

    return listWorkItemsFilteredByFeedback();
  }

  private String listWorkItemsFilteredByFeedback() {
    if (modelsRepository.getAllFeedbacks().isEmpty()) {
      return NO_WORK_ITEMS_OF_TYPE_FEEDBACK_MESSAGE;
    }

    Collection<Feedback> feedbacks = modelsRepository.getAllFeedbacks().values();

    StringBuilder builder = new StringBuilder();
    feedbacks.forEach(feedback -> builder.append(String.format("%s%n", feedback)));

    return builder.toString();
  }
}
