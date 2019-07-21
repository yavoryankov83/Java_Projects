package com.telerikacademy.workItemManagement.tests.commands;

import com.telerikacademy.workItemManagement.commands.changingCommands.ChangeBugPriority;
import com.telerikacademy.workItemManagement.commands.contracts.Command;

import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.models.BugImpl;
import com.telerikacademy.workItemManagement.models.MemberImpl;
import com.telerikacademy.workItemManagement.models.contracts.Bug;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.enums.BugStatusType;
import com.telerikacademy.workItemManagement.models.enums.PriorityType;
import com.telerikacademy.workItemManagement.models.enums.SeverityType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ChangeBugPriority_Tests {
    private Command testCommand;
    private Bug testBug;
    private ModelsRepository testRepository;

    @Before
    public void before() {
        testRepository = new ModelsRepositoryImpl();
        testCommand = new ChangeBugPriority(testRepository);
        Member testAssignee = Mockito.mock(Member.class);
        testBug = new BugImpl(
                "NewBugName",
                "Description",
                PriorityType.HIGH,
                SeverityType.CRITICAL,
                BugStatusType.ACTIVE,
                testAssignee);
        testRepository.addToWorkItems(1L, testBug);
        testRepository.addToBugs(1L, testBug);
    }

    @Test
    public void execute_should_change_bugPriority_when_inputIsValid() {
        // Arrange
        String [] testParameters  = new String[2];
        testParameters [0] = "1";
        testParameters [1] = "High";

        // Act
        testCommand.execute(testParameters);

        // Assert
        Assert.assertEquals( testParameters[1], testBug.getPriority().toString());
    }

    @Test
    public void execute_should_collect_workItem_activityHistory_when_inputIsValid() {
        // Arrange
        String [] testParameters  = new String[2];
        testParameters [0] = "1";
        testParameters [1] = "LOW";

        // Act
        testCommand.execute(testParameters);

        // Assert
        Assert.assertEquals("Priority of bug with id 1 was changed to LOW.",
                testBug.getActivityHistory().get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedLessArguments() {
        // Arrange
        String [] testParameters  = new String[1];
        testParameters [0] = "1";

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedMoreArguments() {
        // Arrange
        String [] testParameters  = new String[3];
        testParameters [0] = "1";
        testParameters [1] = "low";
        testParameters [2] = "AssigneeName";

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passed_invalid_workItem() {
        // Arrange
        String [] testParameters  = new String[2];
        testParameters [0] = "5";
        testParameters [1] = "low";

        // Act&&Assert
        testCommand.execute(testParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedInvalid_id_workItem() {
        // Arrange
        String [] testParameters  = new String[2];
        testParameters [0] = "medium";
        testParameters [1] = "low";

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passed_invalid_priority() {
        // Arrange
        Member member = new MemberImpl("Ivana");
        Bug bug = new BugImpl(
                "NewBugName",
                "Description",
                PriorityType.HIGH,
                SeverityType.CRITICAL,
                BugStatusType.ACTIVE,
                member);
        testRepository.addToBugs(bug.getId(), bug);


        String [] testParameters  = new String[2];
        testParameters [0] = String.valueOf(bug.getId());
        testParameters [1] = "Lower";

        // Act&&Assert
        testCommand.execute(testParameters);
    }
}
