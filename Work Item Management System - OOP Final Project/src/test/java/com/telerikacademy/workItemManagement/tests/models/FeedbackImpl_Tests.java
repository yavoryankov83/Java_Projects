package com.telerikacademy.workItemManagement.tests.models;

import com.telerikacademy.workItemManagement.models.FeedbackImpl;
import com.telerikacademy.workItemManagement.models.contracts.Feedback;
import com.telerikacademy.workItemManagement.models.enums.*;
import org.junit.Assert;
import org.junit.Test;

public class FeedbackImpl_Tests {

  @Test(expected = IllegalArgumentException.class)
  public void constructor_should_thrownException_when_rating_is_negative() {
    // Arrange, Act, Assert
    Feedback feedback = new FeedbackImpl(
            "1234567890",
            "1234567890",
            -5,
            FeedbackStatusType.DONE);
  }

  @Test
  public void getStatusType_should_return_statusType() {
    // Arrange
    Feedback feedback = new FeedbackImpl(
            "1234567890",
            "1234567890",
            5,
            FeedbackStatusType.DONE);
    // Act
    String status = "Done";
    // Assert
    Assert.assertEquals(status, feedback.getStatus());
  }
}
