package com.telerikacademy.workItemManagement.tests.commands;

import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.commands.showCommands.ListWorkItemsFilteredByBug;
import com.telerikacademy.workItemManagement.commands.showCommands.ListWorkItemsFilteredByFeedback;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.models.BugImpl;
import com.telerikacademy.workItemManagement.models.FeedbackImpl;
import com.telerikacademy.workItemManagement.models.contracts.Feedback;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.contracts.Story;
import com.telerikacademy.workItemManagement.models.enums.BugStatusType;
import com.telerikacademy.workItemManagement.models.enums.FeedbackStatusType;
import com.telerikacademy.workItemManagement.models.enums.PriorityType;
import com.telerikacademy.workItemManagement.models.enums.SeverityType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ListWorkItemFilteredByFeedback_Tests {
    private static final String NO_ACTIVITY_HISTORY_MESSAGE =
            "Activity history: There is no activity history for %s with title %s.%n";
    private static final String ACTIVITY_HISTORY_MESSAGE =
            "Activity history for %s with title %s: %n";
    private Command testCommand;
    private ModelsRepository testRepository;
    private Feedback testFeedback;

    @Before
    public void before() {
        testRepository = new ModelsRepositoryImpl();
        testCommand = new ListWorkItemsFilteredByFeedback(testRepository);
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
        result
                .append(getBaseWorkItemInfo())
                .append(String.format("%s with ID %d:%n",
                        testFeedback.getType(),
                        testFeedback.getId()))
                .append(String.format("Rating: %d%n", testFeedback.getRating()))
                .append(String.format("Status: %s%n", testFeedback.getStatus()))
                .append(getWorkItemCommentsCountInfo())
                .append(getActivityHistoryCountInfo())
                .append(System.lineSeparator());



        // Assert
        Assert.assertEquals( result.toString(), execute);
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

    private String getBaseWorkItemInfo() {
        return String.format("WorkItem with ID %d, title %s and description %s, has %d comments and %d history messages.%n",
                testFeedback.getId(),
                testFeedback.getTitle(),
                testFeedback.getDescription(),
                testFeedback.getComments().size(),
                testFeedback.getActivityHistory().size());
    }
    private String getWorkItemCommentsCountInfo() {
        return testFeedback.getComments().isEmpty() ? String.format("Comments: There are no comments for this work item.%n") :
                (testFeedback.getComments().size() == 1 ? String.format("Comment: %n") : String.format("Comments: %n"));
    }

    private String getActivityHistoryCountInfo() {
        return testFeedback.getActivityHistory().isEmpty() ?
                String.format(NO_ACTIVITY_HISTORY_MESSAGE, testFeedback.getType(), testFeedback.getTitle()) :
                String.format(ACTIVITY_HISTORY_MESSAGE, testFeedback.getType(), testFeedback.getTitle());
    }
}
