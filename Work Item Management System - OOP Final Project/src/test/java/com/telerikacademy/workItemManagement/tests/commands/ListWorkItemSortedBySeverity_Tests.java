package com.telerikacademy.workItemManagement.tests.commands;

import com.telerikacademy.workItemManagement.commands.contracts.Command;

import com.telerikacademy.workItemManagement.commands.showCommands.ListAllWorkItemsSortedBySeverity;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.Bug;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ListWorkItemSortedBySeverity_Tests {
  private Command testCommand;
  private ModelsRepository testRepository;

  @Before
  public void before() {
    testRepository = new ModelsRepositoryImpl();
    testCommand = new ListAllWorkItemsSortedBySeverity(testRepository);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throwException_when_passedMoreArguments() {
    // Arrange
    String[] testParameters = new String[2];
    testParameters[0] = "SeverityType";
    testParameters[1] = "SeverityType";

    // Act & Assert
    testCommand.execute(testParameters);
  }

  @Test
  public void listAllWorkItemsSortedBySeverity_should_return_correct_message_when_bugs_no_exist() {
    // Arrange
    String result = "Work items of type Bug not exist.";

    // Act
    String execute = testCommand.execute();

    //Assert
    Assert.assertEquals(result, execute);
  }

  @Test
  public void listAllWorkItemsSortedBySeverity_should_retutn_correct_message_when_bugs_exist() {
    // Arrange
    Bug bug = mock(Bug.class);
    testRepository.addToBugs(bug.getId(), bug);
    String result = String.format(
            "Work item %s with title %s - Severity: %s%n",
            bug.getType(),
            bug.getTitle(),
            bug.getSeverity());

    // Act
    String execute = testCommand.execute();

    //Assert
    Assert.assertEquals(result, execute);
  }
}
