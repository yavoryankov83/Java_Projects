package com.telerikacademy.workItemManagement.tests.commands;

import com.telerikacademy.workItemManagement.commands.contracts.Command;

import com.telerikacademy.workItemManagement.commands.showCommands.ListAllWorkItemsFilteredByStatusAndAssignee;
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

public class ListWorkItemFilteredByStatusAndAssignee_Tests {
    private Command testCommand;
    private ModelsRepository testRepository;

    @Before
    public void before() {
        testRepository = new ModelsRepositoryImpl();
        testCommand = new ListAllWorkItemsFilteredByStatusAndAssignee(testRepository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedLessArguments() {
        // Arrange
        String [] testParameters  = new String[1];
        testParameters [0] = "String";

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedMoreArguments() {
        // Arrange
        String [] testParameters  = new String[3];
        testParameters [0] = "String";
        testParameters [1] = "AssigneeName";
        testParameters [2] = "AssigneeName";

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test
    public void execute_should_return_correct_message_when_passed_not_exist_member_in_members() {
        // Arrange
        String [] testParameters  = new String[2];
        Member member = new MemberImpl("Ivana");
        Bug bug = new BugImpl(
                "1234567890",
                "1234567890",
                PriorityType.HIGH,
                SeverityType.CRITICAL,
                BugStatusType.ACTIVE,
                member);
        testRepository.addToWorkItems(bug.getId(), bug);
        testRepository.getAllMembers().clear();
        testParameters [0] = "Active";
        testParameters [1] = "Assignee";

        // Act
        testCommand.execute(testParameters);
        //Assert
        Assert.assertEquals(String.format("Work item with assignee name %s not exists.",
                testParameters[1]), testCommand.execute(testParameters));
    }

    @Test
    public void listAllWorkItemsFilteredByStatusAndAssignee_should_return_message_when_no_workitems_exist(){
        //Arrange
        String [] testParameters  = new String[2];
        testParameters [0] = "Active";
        testParameters [1] = "Assignee";
        String result = "List of work items is empty.";
        //Act
        String execute = testCommand.execute(testParameters[0], testParameters[1]);

        //Assert
        Assert.assertEquals(result, execute);
    }

    @Test
    public void listAllWorkItemsFilteredByStatusAndAssignee_should_return_message_when_stories_exist_and_assignee_exists(){
        //Arrange
        Member member = new MemberImpl("Ivana");
        Story story = new StoryImpl(
                "StoryStory",
                "Description",
                PriorityType.HIGH,
                SizeType.LARGE,
                StoryStatusType.DONE,
                member);
        String [] testParameters  = new String[2];
        testParameters [0] = "DONE";
        testParameters [1] = "Ivana";
        testRepository.addToMembers(member.getName(), member);
        testRepository.addToWorkItems(story.getId(), story);
        testRepository.addToStories(story.getId(), story);
        StringBuilder result = new StringBuilder();
        result
                .append(String.format(
                        "Work items with status %s and assignee %s:%n",
                        testParameters[0],
                        testParameters[1]))
                .append(String.format(
                        "%s with ID %d and title %s.%n",
                        story.getType(),
                        story.getId(),
                        story.getTitle()));
        //Act
        String execute = testCommand.execute(testParameters[0], testParameters[1]);

        //Assert
        Assert.assertEquals(result.toString(), execute);
    }

    @Test
    public void listAllWorkItemsFilteredByStatusAndAssignee_should_return_message_when_bugs_exist_and_assignee_exists(){
        //Arrange
        Member member = new MemberImpl("Ivana");
        Bug bug = new BugImpl(
                "1234567890",
                "1234567890",
                PriorityType.HIGH,
                SeverityType.CRITICAL,
                BugStatusType.ACTIVE,
                member);
        String [] testParameters  = new String[2];
        testParameters [0] = "ACTIVE";
        testParameters [1] = "Ivana";
        testRepository.addToMembers(member.getName(), member);
        testRepository.addToWorkItems(bug.getId(), bug);
        testRepository.addToBugs(bug.getId(), bug);
        StringBuilder result = new StringBuilder();
        result
                .append(String.format(
                        "Work items with status %s and assignee %s:%n",
                        testParameters[0],
                        testParameters[1]))
                .append(String.format(
                        "%s with ID %d and title %s.%n",
                        bug.getType(),
                        bug.getId(),
                        bug.getTitle()));
        //Act
        String execute = testCommand.execute(testParameters[0], testParameters[1]);

        //Assert
        Assert.assertEquals(result.toString(), execute);
    }

    @Test(expected = IllegalArgumentException.class)
    public void listAllWorkItemsFilteredByStatusAndAssignee_should_throw_exception_when_status_invalid(){
        //Arrange
        String [] testParameters  = new String[2];
        testParameters [0] = "String";
        testParameters [1] = "Assignee";
        //Act
        String execute = testCommand.execute(testParameters[0], testParameters[1]);

    }

    @Test
    public void listAllWorkItemsFilteredByStatusAndAssignee_should_return_message_when_no_workitems_with_given_status_exist(){
        //Arrange
        Member member = new MemberImpl("Ivana");
        Story story = new StoryImpl(
                "StoryStory",
                "Description",
                PriorityType.HIGH,
                SizeType.LARGE,
                StoryStatusType.INPROGRESS,
                member);
        String [] testParameters  = new String[2];
        testParameters [0] = "DONE";
        testParameters [1] = "Ivana";
        testRepository.addToMembers(member.getName(), member);
        testRepository.addToWorkItems(story.getId(), story);
        testRepository.addToStories(story.getId(), story);
        String result = String.format("Work item with status %s not exists.",
                testParameters[0]);
        //Act
        String execute = testCommand.execute(testParameters[0], testParameters[1]);

        //Assert
        Assert.assertEquals(result, execute);
    }
}
