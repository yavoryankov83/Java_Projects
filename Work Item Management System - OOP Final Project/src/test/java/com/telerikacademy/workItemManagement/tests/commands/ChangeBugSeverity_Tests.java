package com.telerikacademy.workItemManagement.tests.commands;

import com.telerikacademy.workItemManagement.commands.changingCommands.ChangeBugSeverity;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.BugImpl;
import com.telerikacademy.workItemManagement.models.contracts.Bug;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.enums.BugStatusType;
import com.telerikacademy.workItemManagement.models.enums.PriorityType;
import com.telerikacademy.workItemManagement.models.enums.SeverityType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ChangeBugSeverity_Tests {
    private Command testCommand;
    private Bug testBug;
    private ModelsRepository testRepository;

    @Before
    public void before() {
        testRepository = new ModelsRepositoryImpl();
        testCommand = new ChangeBugSeverity(testRepository);
        Member testAssignee = Mockito.mock(Member.class);
        testBug = new BugImpl("NewBugName","Description",
                PriorityType.HIGH, SeverityType.CRITICAL, BugStatusType.ACTIVE, testAssignee);
        testRepository.addToWorkItems(1L, testBug);
        testRepository.addToBugs(1L, testBug);
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
        testParameters [1] = "minor";
        testParameters [2] = "AssigneeName";

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passed_invalid_workItem() {
        // Arrange
        String [] testParameters  = new String[2];
        testParameters [0] = "5";
        testParameters [1] = "minor";

        // Act&&Assert
        testCommand.execute(testParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedInvalid_id_workItem() {
        // Arrange
        String [] testParameters  = new String[2];
        testParameters [0] = "medium";
        testParameters [1] = "minor";

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passed_invalid_severity() {
        // Arrange
        String [] testParameters  = new String[2];
        testParameters [0] = "1";
        testParameters [1] = "PersonName";

        // Act&&Assert
        testCommand.execute(testParameters);
    }

    @Test
    public void execute_should_change_bugSeverity_when_inputIsValid() {
        // Arrange
        String [] testParameters  = new String[2];
        testParameters [0] = "1";
        testParameters [1] = "Minor";

        // Act
        testCommand.execute(testParameters);
        SeverityType severity = testBug.getSeverity();

        // Assert
        Assert.assertEquals(testParameters[1], severity.toString());
    }

    @Test
    public void execute_should_collect_workItem_activityHistory_when_inputIsValid() {
        // Arrange
        String [] testParameters  = new String[2];
        testParameters [0] = "1";
        testParameters [1] = "minor";

        // Act
        testCommand.execute(testParameters);

        // Assert
        Assert.assertEquals("Severity of bug with id 1 was changed to MINOR.",
                testBug.getActivityHistory().get(0));
    }
}
