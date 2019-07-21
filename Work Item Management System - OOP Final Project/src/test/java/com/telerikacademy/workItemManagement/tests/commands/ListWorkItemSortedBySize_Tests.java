package com.telerikacademy.workItemManagement.tests.commands;

import com.telerikacademy.workItemManagement.commands.contracts.Command;

import com.telerikacademy.workItemManagement.commands.showCommands.ListAllWorkItemsSortedBySize;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
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

public class ListWorkItemSortedBySize_Tests {
    private Command testCommand;
    private ModelsRepository testRepository;
    private Story testStory;

    @Before
    public void before() {
        testRepository = new ModelsRepositoryImpl();
        testCommand = new ListAllWorkItemsSortedBySize(testRepository);
        Member testAssignee = Mockito.mock(Member.class);
        testStory = new StoryImpl("NewStoryName","Description",
                PriorityType.MEDIUM, SizeType.SMALL, StoryStatusType.INPROGRESS,testAssignee);

    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedMoreArguments() {
        // Arrange
        testRepository.addToWorkItems(testStory.getId(), testStory);
        testRepository.addToStories(testStory.getId(), testStory);

        String [] testParameters  = new String[1];
        testParameters [0] = "StoryType";

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test
    public void execute_should_list_workItem_when_inputIsValid() {
        // Arrange
        testRepository.addToWorkItems(testStory.getId(), testStory);
        testRepository.addToStories(testStory.getId(), testStory);

        String [] testParameters  = new String[0];

        // Act
        String execute = testCommand.execute(testParameters);
        StringBuilder result = new StringBuilder();
        result
                .append(String.format("Work item %s with title %s - Size: %s%n",
                        testStory.getType(),
                        testStory.getTitle(),
                        testStory.getSize()));

        // Assert
        Assert.assertEquals(result.toString(), execute);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_return_message_when_no_workitems() {
        // Arrange
        String [] testParameters  = new String[0];

        // Act
        String execute = testCommand.execute(testParameters);
        String result = "Work items of type Story not exist.";
        // Assert

        Assert.assertEquals(result, execute);
    }
}
