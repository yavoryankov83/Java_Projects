package com.telerikacademy.workItemManagement.models;

import com.telerikacademy.workItemManagement.models.common.Validator;
import com.telerikacademy.workItemManagement.models.contracts.Feedback;
import com.telerikacademy.workItemManagement.models.contracts.Statusable;
import com.telerikacademy.workItemManagement.models.enums.FeedbackStatusType;

public class FeedbackImpl extends AbstractWorkItem implements Feedback, Statusable {
  private static final String RATING_NEGATIVE_EXCEPTION =
          "Rating should not be negative.";

  private int rating;
  private FeedbackStatusType status;

  public FeedbackImpl(
          String title,
          String description,
          int rating,
          FeedbackStatusType status) {
    super(title, description);
    setRating(rating);
    setStatus(status);
  }

  @Override
  public int getRating() {
    return rating;
  }

  @Override
  public void changeRating(int rating) {
    this.rating = rating;
  }

  @Override
  public String getStatus() {
    return status.toString();
  }

  @Override
  public void changeStatus(String status) {
    setStatus(FeedbackStatusType.valueOf(status));
  }

  @Override
  public String getAdditionalInfo() {
    StringBuilder builder = new StringBuilder();

    builder
            .append(String.format("%s with ID %d:%n",
                    getType(),
                    getId()))
            .append(String.format("Rating: %d%n", getRating()))
            .append(String.format("Status: %s%n", getStatus()));

    return builder.toString();
  }

  private void setRating(int rating) {
    Validator.validateForNegativeValue(rating, RATING_NEGATIVE_EXCEPTION);
    this.rating = rating;
  }

  private void setStatus(FeedbackStatusType status) {
    this.status = status;
  }
}
