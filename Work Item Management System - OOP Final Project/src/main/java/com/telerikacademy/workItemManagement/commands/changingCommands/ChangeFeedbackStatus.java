package com.telerikacademy.workItemManagement.commands.changingCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.*;
import com.telerikacademy.workItemManagement.models.enums.FeedbackStatusType;

import java.util.Arrays;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class ChangeFeedbackStatus implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;

  private ModelsRepository modelsRepository;

  public ChangeFeedbackStatus(ModelsRepository modelsRepository) {
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

    long feedbackId = Long.parseLong(parameters[0]);
    String status = parameters[1].toUpperCase();

    return changeFeedbackStatus(feedbackId, status);
  }

  private String changeFeedbackStatus(long feedbackId, String status) {
    if (!modelsRepository.getAllFeedbacks().containsKey(feedbackId)) {
      throw new IllegalArgumentException(String.format(FEEDBACK_NOT_EXISTS_MESSAGE, feedbackId));
    }
    if (!isStatusValidFeedbackStatusType(status)) {
      throw new IllegalArgumentException(String.format(INVALID_FEEDBACK_STATUS_TYPE_MESSAGE, status));
    }

    Statusable feedback = modelsRepository.getAllFeedbacks().get(feedbackId);

    feedback.changeStatus(status);
    feedback.addToActivityHistory(
            String.format(
                    FEEDBACK_CHANGE_STATUS_SUCCESS_MESSAGE,
                    feedbackId,
                    status));

    return String.format(FEEDBACK_CHANGE_STATUS_SUCCESS_MESSAGE, feedbackId, status);
  }

  private boolean isStatusValidFeedbackStatusType(String status) {
    return Arrays.stream(FeedbackStatusType.values())
            .anyMatch(value -> value.toString().equalsIgnoreCase(status));
  }
}