package com.telerikacademy.workItemManagement.tests.commands;

import com.telerikacademy.workItemManagement.commands.contracts.Command;

import com.telerikacademy.workItemManagement.commands.showCommands.ListAllWorkItemsSortedByRating;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.FeedbackImpl;
import com.telerikacademy.workItemManagement.models.contracts.Feedback;
import com.telerikacademy.workItemManagement.models.enums.FeedbackStatusType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListWorkItemSortedByRating_Tests {
    private Command testCommand;
    private ModelsRepository testRepository;
    private Feedback testFeedback;

    @Before
    public void before() {
        testRepository = new ModelsRepositoryImpl();
        testCommand = new ListAllWorkItemsSortedByRating(testRepository);
        testFeedback = new FeedbackImpl("NewFeedbackName","Description",
                6, FeedbackStatusType.NEW);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedMoreArguments() {
        // Arrange
        testRepository.addToWorkItems(testFeedback.getId(), testFeedback);
        testRepository.addToFeedbacks(testFeedback.getId(), testFeedback);

        String [] testParameters  = new String[1];
        testParameters [0] = "FeedbackType";

        // Act & Assert
        testCommand.execute(testParameters);
    }
    @Test
    public void execute_should_list_workItem_when_inputIsValid() {
        // Arrange
        testRepository.addToWorkItems(testFeedback.getId(), testFeedback);
        testRepository.addToFeedbacks(testFeedback.getId(), testFeedback);

        String [] testParameters  = new String[0];

        // Act
        String execute = testCommand.execute(testParameters);
        StringBuilder result = new StringBuilder();
        result .append(String.format("Work item %s with title %s - Rating: %d%n",
                testFeedback.getType(),
                testFeedback.getTitle(),
                testFeedback.getRating()));

        // Assert
        Assert.assertEquals(result.toString(), execute);
    }

    @Test
    public void execute_should_return_message_when_no_workitems() {
        // Arrange
        String [] testParameters  = new String[0];

        // Act
        String execute = testCommand.execute(testParameters);
        String result = "Work items of type Feedback not exist.";
        // Assert

        Assert.assertEquals(result, execute);
    }
}
