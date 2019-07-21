package com.telerikacademy.workItemManagement.tests.commands;

import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.commands.showCommands.ListAllWorkItems;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.BugImpl;
import com.telerikacademy.workItemManagement.models.contracts.Bug;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.enums.BugStatusType;
import com.telerikacademy.workItemManagement.models.enums.PriorityType;
import com.telerikacademy.workItemManagement.models.enums.SeverityType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ListAllWorkItem_Tests {
  private Command testCommand;
  private ModelsRepository testRepository;
  private Bug testBug;

  @Before
  public void before() {
    testRepository = new ModelsRepositoryImpl();
    testCommand = new ListAllWorkItems(testRepository);
    Member testAssignee = Mockito.mock(Member.class);
    testBug = new BugImpl("NewBugName", "Description",
            PriorityType.HIGH, SeverityType.CRITICAL, BugStatusType.ACTIVE, testAssignee);
    testRepository.addToWorkItems(1L, testBug);
    testRepository.addToBugs(1L, testBug);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throwException_when_passedMoreArguments() {
    // Arrange
    String[] testParameters = new String[2];
    testParameters[0] = "WorkItemName";
    testParameters[1] = "WorkItemName";

    // Act & Assert
    testCommand.execute(testParameters);
  }

  @Test
  public void execute_should_return_message_when_no_workitems() {
    // Arrange
    String[] testParameters = new String[0];
    testRepository.removeFromWorkItems(1L, testBug);

    // Act
    String execute = testCommand.execute(testParameters);
    String result = "List of work items is empty.";
    // Assert

    Assert.assertEquals(result, execute);
  }

  @Test
  public void execute_should_list_workItem_when_inputIsValid() {
    // Arrange
    String[] testParameters = new String[0];

    // Act
    String execute = testCommand.execute(testParameters);
    StringBuilder result = new StringBuilder();

    result
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
            .append(System.lineSeparator());

    // Assert
    Assert.assertEquals(result.toString(), execute);
  }
}
