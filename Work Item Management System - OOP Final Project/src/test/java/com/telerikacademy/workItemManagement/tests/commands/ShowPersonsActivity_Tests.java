package com.telerikacademy.workItemManagement.tests.commands;


import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.commands.showCommands.ShowPersonsActivity;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.MemberImpl;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.contracts.WorkItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ShowPersonsActivity_Tests {
    private Command testCommand;
    private Member testMember;
    private ModelsRepository testRepository;

    @Before
    public void before() {
        testRepository = new ModelsRepositoryImpl();
        testCommand = new ShowPersonsActivity(testRepository);
        testMember = new MemberImpl("PersonName");
        WorkItem testActivity = Mockito.mock(WorkItem.class);
        testRepository.addToPeople("PersonName",testMember);
        testMember.addWorkItem(testActivity);
        testMember.addToActivityHistory("HistoryMessage");
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
        testParameters [0] = "PersonName";
        testParameters [1] = "PersonName";

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passed_invalid_person() {
        // Arrange
        String [] testParameters  = new String[1];
        testParameters [0] = "Person Name";

        // Act&&Assert
        testCommand.execute(testParameters);
    }

    @Test
    public void execute_should_showPersonsActivity_when_inputIsValid() {
        // Arrange
        String [] testParameters  = new String[1];
        testParameters [0] = "PersonName";

        // Act
        testCommand.execute(testParameters);

        // Assert
        Assert.assertEquals(1, testMember.getActivityHistory().size());
    }
}
