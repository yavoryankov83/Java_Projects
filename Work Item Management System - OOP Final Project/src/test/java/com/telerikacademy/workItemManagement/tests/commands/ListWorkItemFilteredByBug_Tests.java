package com.telerikacademy.workItemManagement.tests.commands;

import com.telerikacademy.workItemManagement.commands.contracts.Command;

import com.telerikacademy.workItemManagement.commands.showCommands.ListWorkItemsFilteredByBug;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.BugImpl;
import com.telerikacademy.workItemManagement.models.MemberImpl;
import com.telerikacademy.workItemManagement.models.contracts.Bug;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.enums.BugStatusType;
import com.telerikacademy.workItemManagement.models.enums.PriorityType;
import com.telerikacademy.workItemManagement.models.enums.SeverityType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListWorkItemFilteredByBug_Tests {
  private static final String NO_ACTIVITY_HISTORY_MESSAGE =
          "Activity history: There is no activity history for %s with title %s.%n";
  private static final String ACTIVITY_HISTORY_MESSAGE =
          "Activity history for %s with title %s: %n";

  private Command testCommand;
  private ModelsRepository testRepository;
  private Bug testBug;

  @Before
  public void before() {
    testRepository = new ModelsRepositoryImpl();
    testCommand = new ListWorkItemsFilteredByBug(testRepository);
    Member member = new MemberImpl("Ivana");
    testBug = new BugImpl("NewBugName", "Description",
            PriorityType.HIGH, SeverityType.CRITICAL, BugStatusType.ACTIVE, member);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throwException_when_passedMoreArguments() {
    // Arrange

    String[] testParameters = new String[1];
    testParameters[0] = "BugType";

    // Act & Assert
    testCommand.execute(testParameters);
  }

  @Test
  public void execute_should_list_workItem_when_inputIsValid() {
    // Arrange
    testRepository.addToBugs(1, testBug);
    String[] testParameters = new String[0];

    // Act
    String execute = testCommand.execute(testParameters);

    StringBuilder result = new StringBuilder();
    result
            .append(getBaseWorkItemInfo())
            .append(String.format("%s with ID %d:%n",
                    testBug.getType(),
                    testBug.getId()))
            .append(String.format("Bug has %d steps to reproduce.%n",
                    testBug.getStepsToReproduce().size()))
            .append(
                    String.format("Steps to reproduce: There are no steps to reproduce for this bug.%n"))
            .append(String.format("Priority: %s%n", testBug.getPriority()))
            .append(String.format("Severity: %s%n", testBug.getSeverity()))
            .append(String.format("Status: %s%n", testBug.getStatus()))
            .append(String.format("Assignee: %s%n", testBug.getAssignee().getName()))
            .append(getWorkItemCommentsCountInfo())
            .append(getActivityHistoryCountInfo())
            .append(System.lineSeparator());

    // Assert
    Assert.assertEquals(result.toString(), execute);
  }

  @Test
  public void execute_should_return_message_when_no_workitems() {
    // Arrange
    String[] testParameters = new String[0];
    testRepository.removeFromWorkItems(testBug.getId(), testBug);

    // Act
    String execute = testCommand.execute(testParameters);
    String result = "Work items of type Bug not exist.";
    // Assert

    Assert.assertEquals(result, execute);
  }

  private String getBaseWorkItemInfo() {
    return String.format("WorkItem with ID %d, title %s and description %s, has %d comments and %d history messages.%n",
            testBug.getId(),
            testBug.getTitle(),
            testBug.getDescription(),
            testBug.getComments().size(),
            testBug.getActivityHistory().size());
  }
  private String getWorkItemCommentsCountInfo() {
    return testBug.getComments().isEmpty() ? String.format("Comments: There are no comments for this work item.%n") :
            (testBug.getComments().size() == 1 ? String.format("Comment: %n") : String.format("Comments: %n"));
  }

  private String getActivityHistoryCountInfo() {
    return testBug.getActivityHistory().isEmpty() ?
            String.format(NO_ACTIVITY_HISTORY_MESSAGE, testBug.getType(), testBug.getTitle()) :
            String.format(ACTIVITY_HISTORY_MESSAGE, testBug.getType(), testBug.getTitle());
  }
}
