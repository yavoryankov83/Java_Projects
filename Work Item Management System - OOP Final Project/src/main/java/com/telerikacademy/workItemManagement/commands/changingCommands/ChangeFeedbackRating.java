package com.telerikacademy.workItemManagement.commands.changingCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.Feedback;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class ChangeFeedbackRating implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;

  private ModelsRepository modelsRepository;

  public ChangeFeedbackRating(ModelsRepository modelsRepository) {
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
    int rating = Integer.parseInt(parameters[1]);

    return changeFeedbackRating(feedbackId, rating);
  }

  private String changeFeedbackRating(long feedbackId, int rating) {
    if (!modelsRepository.getAllFeedbacks().containsKey(feedbackId)) {
      throw new IllegalArgumentException(String.format(FEEDBACK_NOT_EXISTS_MESSAGE, feedbackId));
    }

    Feedback feedback = modelsRepository.getAllFeedbacks().get(feedbackId);

    feedback.changeRating(rating);
    feedback.addToActivityHistory(
            String.format(
                    FEEDBACK_CHANGE_RATING_SUCCESS_MESSAGE,
                    feedbackId,
                    rating));

    return String.format(FEEDBACK_CHANGE_RATING_SUCCESS_MESSAGE, feedbackId, rating);
  }
}