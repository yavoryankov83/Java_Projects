package com.telerikacademy.workItemManagement.tests.commands;

import com.telerikacademy.workItemManagement.commands.contracts.Command;

import com.telerikacademy.workItemManagement.commands.showCommands.ListAllWorkItemsFilteredByStatus;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.BugImpl;
import com.telerikacademy.workItemManagement.models.MemberImpl;
import com.telerikacademy.workItemManagement.models.contracts.Bug;
import com.telerikacademy.workItemManagement.models.contracts.Feedback;
import com.telerikacademy.workItemManagement.models.enums.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ListWorkItemFilteredByStatus_Tests {
  private Command testCommand;
  private ModelsRepository testRepository;

  @Before
  public void before() {
    testRepository = new ModelsRepositoryImpl();
    testCommand = new ListAllWorkItemsFilteredByStatus(testRepository);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throwException_when_passedLessArguments() {
    // Arrange
    String[] testParameters = new String[0];

    // Act & Assert
    testCommand.execute(testParameters);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throwException_when_passedMoreArguments() {
    // Arrange
    String[] testParameters = new String[2];
    testParameters[0] = "String";
    testParameters[1] = "String";

    // Act & Assert
    testCommand.execute(testParameters);
  }

  @Test(expected = IllegalArgumentException.class)
  public void listAllWorkItemsFilteredByStatus_should_throw_exception_when_status_is_invalid() {
    // Arrange
    Feedback feedback = mock(Feedback.class);
    testRepository.addToWorkItems(feedback.getId(), feedback);
    String[] testParameters = new String[1];
    testParameters[0] = "String";

    // Act & Assert
    testCommand.execute(testParameters[0]);
  }

  @Test
  public void listAllWorkItemsFilteredByStatus_should_return_message_when_no_items_exist() {
    // Arrange
    String[] testParameters = new String[1];
    testParameters[0] = "Done";
    String result = "List of work items is empty.";

    // Act
    String execute = testCommand.execute(testParameters[0]);

    // Assert
    Assert.assertEquals(result, execute);
  }

  @Test
  public void listAllWorkItemsFilteredByStatus_should_return_correct_message_when_item_is_bug() {
    // Arrange
    Bug bug = new BugImpl(
            "1234567890",
            "1234567890",
            PriorityType.HIGH,
            SeverityType.CRITICAL,
            BugStatusType.ACTIVE,
            new MemberImpl("Ivana"));

    testRepository.addToWorkItems(bug.getId(), bug);
    testRepository.addToBugs(bug.getId(), bug);

    String[] testParameters = new String[1];
    testParameters[0] = "ACTIVE";

    // Act
    String execute = testCommand.execute(testParameters[0]);

    // Assert
    String builder = String.format("Work items with status %s:%n",
            bug.getStatus().toUpperCase()) +
            String.format("%s with ID %d and title %s.%n",
                    bug.getType(),
                    bug.getId(),
                    bug.getTitle());
    Assert.assertEquals(builder, execute);
  }
}
