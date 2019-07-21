package com.telerikacademy.workItemManagement.tests.commands;

import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.commands.showCommands.ListAllWorkItemsFilteredByAssignee;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.BugImpl;
import com.telerikacademy.workItemManagement.models.MemberImpl;
import com.telerikacademy.workItemManagement.models.StoryImpl;
import com.telerikacademy.workItemManagement.models.contracts.Bug;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.contracts.Story;
import com.telerikacademy.workItemManagement.models.enums.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListWorkItemFilteredByAssignee_Tests {
    private Command testCommand;
    private ModelsRepository testRepository;

    @Before
    public void before() {
        testRepository = new ModelsRepositoryImpl();
        testCommand = new ListAllWorkItemsFilteredByAssignee(testRepository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedLessArguments() {
        // Arrange
        String [] testParameters  = new String[0];

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedMoreArguments() {
        // Arrange
        String [] testParameters  = new String[2];
        testParameters [0] = "AssigneeName";
        testParameters [1] = "AssigneeName";

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test
    public void execute_should_return_correct_message_when_passed_invalid_member() {
        // Arrange
        String [] testParameters  = new String[1];
        testParameters [0] = "Ivana";

        Member member = new MemberImpl("Ivana");
        Bug bug = new BugImpl(
                "1234567890",
                "1234567890",
                PriorityType.HIGH,
                SeverityType.CRITICAL,
                BugStatusType.ACTIVE,
                member);

        testRepository.getAllMembers().clear();
        testRepository.addToBugs(bug.getId(), bug);

        // Act
        testCommand.execute(testParameters);
        //Assert
        Assert.assertEquals(String.format("Work item with assignee name %s not exists.", testParameters[0]),
                testCommand.execute(testParameters));
    }

    @Test
    public void listAllWorkItemsFilteredByAssignee_should_return_message_when_no_workItems_of_type_bug_and_story(){
        //Arrange
        Member member = new MemberImpl("AssigneeName");
        Bug bug = new BugImpl(
                "NewBugName",
                "Description",
                PriorityType.HIGH,
                SeverityType.CRITICAL,
                BugStatusType.ACTIVE,
                member);
        Story story = new StoryImpl(
                "NewStoryName",
                "Description",
                PriorityType.HIGH,
                SizeType.LARGE,
                StoryStatusType.INPROGRESS,
                member);

        String result = "Work items of type Bug and Story not exist.";

        //Act
        String execute = testCommand.execute(member.getName());

        //Assert
        Assert.assertEquals(result, execute);
    }

    @Test
    public void listAllWorkItemsFilteredByAssignee_should_return_message_when_story_or_bug_exist(){
        //Arrange
        Member member = new MemberImpl("AssigneeName");
        Bug bug = new BugImpl(
                "NewBugName",
                "Description",
                PriorityType.HIGH,
                SeverityType.CRITICAL,
                BugStatusType.ACTIVE,
                member);
        Story story = new StoryImpl(
                "NewStoryName",
                "Description",
                PriorityType.HIGH,
                SizeType.LARGE,
                StoryStatusType.INPROGRESS,
                member);

        testRepository.addToMembers(member.getName(), member);
        testRepository.addToBugs(bug.getId(), bug);
        StringBuilder result = new StringBuilder();
        result
                .append(String.format("Work items with assignee name %s:%n",
                        member.getName()))
                .append(String.format("%s with ID %d and title %s.%n",
                        bug.getType(),
                        bug.getId(),
                        bug.getTitle()));

        //Act
        String execute = testCommand.execute(member.getName());

        //Assert
        Assert.assertEquals(result.toString(), execute);
    }
}
