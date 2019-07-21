package com.telerikacademy.workItemManagement.tests.commands;

import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.commands.showCommands.ListWorkItemsFilteredByBug;
import com.telerikacademy.workItemManagement.commands.showCommands.ListWorkItemsFilteredByStory;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.models.MemberImpl;
import com.telerikacademy.workItemManagement.models.StoryImpl;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.contracts.Story;
import com.telerikacademy.workItemManagement.models.enums.PriorityType;
import com.telerikacademy.workItemManagement.models.enums.SizeType;
import com.telerikacademy.workItemManagement.models.enums.StoryStatusType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ListWorkItemFilteredByStory_Tests {
  private static final String NO_ACTIVITY_HISTORY_MESSAGE =
          "Activity history: There is no activity history for %s with title %s.%n";
  private static final String ACTIVITY_HISTORY_MESSAGE =
          "Activity history for %s with title %s: %n";
  private Command testCommand;
  private ModelsRepository testRepository;
  private Story testStory;

  @Before
  public void before() {
    testRepository = new ModelsRepositoryImpl();
    testCommand = new ListWorkItemsFilteredByStory(testRepository);
    Member member = new MemberImpl("Ivana");
    testStory = new StoryImpl("NewStoryName", "Description",
            PriorityType.MEDIUM, SizeType.SMALL, StoryStatusType.INPROGRESS, member);

  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throwException_when_passedMoreArguments() {
    // Arrange

    testRepository.addToStories(testStory.getId(), testStory);

    String[] testParameters = new String[1];
    testParameters[0] = "StoryType";

    // Act & Assert
    testCommand.execute(testParameters);
  }

  @Test
  public void execute_should_list_workItem_when_inputIsValid() {
    // Arrange
    testRepository.addToStories(testStory.getId(), testStory);

    String[] testParameters = new String[0];

    // Act
    String execute = testCommand.execute(testParameters);
    StringBuilder result = new StringBuilder();
    result
            .append(getBaseWorkItemInfo())
            .append(String.format("%s with ID %d:%n",
                    testStory.getType(),
                    testStory.getId()))
            .append(String.format("Priority: %s%n", testStory.getPriority()))
            .append(String.format("Size: %s%n", testStory.getSize()))
            .append(String.format("Status: %s%n", testStory.getStatus()))
            .append(String.format("Assignee: %s%n", testStory.getAssignee().getName()))
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

    // Act
    String execute = testCommand.execute(testParameters);
    String result = "Work items of type Story not exist.";
    // Assert

    Assert.assertEquals(result, execute);
  }

  private String getBaseWorkItemInfo() {
    return String.format("WorkItem with ID %d, title %s and description %s, has %d comments and %d history messages.%n",
            testStory.getId(),
            testStory.getTitle(),
            testStory.getDescription(),
            testStory.getComments().size(),
            testStory.getActivityHistory().size());
  }

  private String getWorkItemCommentsCountInfo() {
    return testStory.getComments().isEmpty() ? String.format("Comments: There are no comments for this work item.%n") :
            (testStory.getComments().size() == 1 ? String.format("Comment: %n") : String.format("Comments: %n"));
  }

  private String getActivityHistoryCountInfo() {
    return testStory.getActivityHistory().isEmpty() ?
            String.format(NO_ACTIVITY_HISTORY_MESSAGE, testStory.getType(), testStory.getTitle()) :
            String.format(ACTIVITY_HISTORY_MESSAGE, testStory.getType(), testStory.getTitle());
  }
}
