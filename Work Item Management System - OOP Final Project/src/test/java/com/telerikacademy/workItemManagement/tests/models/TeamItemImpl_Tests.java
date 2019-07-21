package com.telerikacademy.workItemManagement.tests.models;

import com.telerikacademy.workItemManagement.models.FeedbackImpl;
import com.telerikacademy.workItemManagement.models.TeamItemImpl;
import com.telerikacademy.workItemManagement.models.contracts.Feedback;
import com.telerikacademy.workItemManagement.models.contracts.TeamItem;
import com.telerikacademy.workItemManagement.models.enums.FeedbackStatusType;
import org.junit.Assert;
import org.junit.Test;

public class TeamItemImpl_Tests {
  @Test
  public void addWorkItem_should_increaseWorkItemCount() {
    // Arrange
    TeamItem teamItem = new TeamItemImpl("Barcelona");
    // Act
    teamItem.addWorkItem(new FeedbackImpl("TitleTitle", "Description", 5, FeedbackStatusType.NEW));

    // Assert
    Assert.assertEquals(1, teamItem.getWorkItems().size());
  }

  @Test
  public void removeWorkItem_should_decreaseWorkItemCount() {
    // Arrange
    TeamItem teamItem = new TeamItemImpl("Barcelona");
    // Act
    Feedback feedback = new FeedbackImpl("TitleTitle", "Description", 5, FeedbackStatusType.NEW);
    teamItem.addWorkItem(feedback);
    teamItem.removeWorkItem(feedback);

    // Assert
    Assert.assertEquals(0, teamItem.getWorkItems().size());
  }

  @Test
  public void addToActivityHistory_should_addHistory() {
    // Arrange
    TeamItem teamItem = new TeamItemImpl("Barcelona");
    // Act
    teamItem.addToActivityHistory("Activity History");

    // Assert
    Assert.assertEquals(1, teamItem.getActivityHistory().size());
  }

  @Test
  public void toString_should_returnCorrectSingleOutput() {
    // Arrange
    TeamItem teamItem = new TeamItemImpl("TeamName");
    StringBuilder builder = new StringBuilder();
    // Act
    builder.append(String.format("%s %s has %d work items and %d history messages.%n",
            teamItem.getType(),
            teamItem.getName(),
            teamItem.getWorkItems().size(),
            teamItem.getActivityHistory().size()))
            .append(String.format("Work items: There are no work items for this %s.%n",
                    teamItem.getType().toLowerCase()))
            .append(String.format("Activity history: There is no activity history for %s %s.%n",
                    teamItem.getType(), teamItem.getName()));
    builder.append(System.lineSeparator());

    // Assert
    Assert.assertEquals(builder.toString(), teamItem.toString());
  }

  @Test
  public void toString_should_returnCorrectMultipleOutput() {
    // Arrange
    TeamItem teamItem = new TeamItemImpl("TeamName");
    Feedback workItem = new FeedbackImpl("TitleTitle", "Description", 5, FeedbackStatusType.NEW);
    StringBuilder builder = new StringBuilder();
    // Act
    teamItem.addWorkItem(workItem);
    String activity = "Activity";
    teamItem.addToActivityHistory(activity);

    builder.append(String.format("%s %s has %d work items and %d history messages.%n",
            teamItem.getType(),
            teamItem.getName(),
            teamItem.getWorkItems().size(),
            teamItem.getActivityHistory().size()))
            .append(String.format("Work item: %n"))
            .append(String.format("%s%n", workItem))
            .append(String.format("Activity history for %s %s: %n", teamItem.getType(), teamItem.getName()))
            .append(String.format("%s%n", activity))
            .append(System.lineSeparator());

    // Assert
    Assert.assertEquals(builder.toString(), teamItem.toString());
  }
}
