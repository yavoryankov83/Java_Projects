package com.telerikacademy.workItemManagement.tests.commands;

import com.telerikacademy.workItemManagement.commands.contracts.Command;

import com.telerikacademy.workItemManagement.commands.showCommands.ListAllWorkItemsSortedByTitle;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.FeedbackImpl;
import com.telerikacademy.workItemManagement.models.contracts.Feedback;
import com.telerikacademy.workItemManagement.models.enums.FeedbackStatusType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListWorkItemSortedByTitle_Tests {
    private Command testCommand;
    private ModelsRepository testRepository;

    @Before
    public void before() {
        testRepository = new ModelsRepositoryImpl();
        testCommand = new ListAllWorkItemsSortedByTitle(testRepository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedMoreArguments() {
        // Arrange
        String [] testParameters  = new String[2];
        testParameters [0] = "Title";
        testParameters [1] = "Title";

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test
    public void listAllWorkItemsSortedByTitle_should_return_correct_message_when_no_workitems_exist() {
        // Arrange
        String result = "No work items exist.";

        // Act
        String execute = testCommand.execute();

        //Assert
        Assert.assertEquals(result, execute);
    }

    @Test
    public void listAllWorkItemsSortedByTitle_should_return_correct_message_when_workitems_exist() {
        // Arrange
        Feedback feedback = new FeedbackImpl(
                "1234567890",
                "1234567890",
                10,
                FeedbackStatusType.DONE);
        testRepository.addToWorkItems(feedback.getId(), feedback);
        String result = String.format("%s", feedback.getTitle());

        // Act
        String execute = testCommand.execute();

        //Assert
        Assert.assertEquals(result, execute);
    }
}
