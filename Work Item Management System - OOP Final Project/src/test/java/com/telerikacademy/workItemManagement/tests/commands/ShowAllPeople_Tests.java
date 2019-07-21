package com.telerikacademy.workItemManagement.tests.commands;

import com.telerikacademy.workItemManagement.commands.contracts.Command;

import com.telerikacademy.workItemManagement.commands.showCommands.ShowAllPeople;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.MemberImpl;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShowAllPeople_Tests {
    private Command testCommand;
    private ModelsRepository testRepository;
    private Member testMember;

    @Before
    public void before() {
        testRepository = new ModelsRepositoryImpl();
        testCommand = new ShowAllPeople(testRepository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedMoreArguments() {
        // Arrange
        testMember = new MemberImpl("Pesho");
        testRepository.addToPeople("Pesho",testMember);
        String [] testParameters  = new String[2];
        testParameters [0] = "PersonName";
        testParameters [1] = "PersonName";

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test
    public void showAllPeople_should_return_correct_message_when_no_people_exist() {
        // Arrange
        testRepository.getAllPeople().clear();
        String message = "There are no created persons.";
        // Act
        String execute = testCommand.execute();
        // Assert
        Assert.assertEquals(message, execute);
    }

    @Test
    public void execute_should_createNewBoard_when_inputIsValid() {
        // Arrange
        testMember = new MemberImpl("Pesho");
        testRepository.addToPeople("Pesho",testMember);
        String [] testParameters  = new String[0];

        // Act
        testCommand.execute(testParameters);

        // Assert
        Assert.assertEquals(String.format("[Member Pesho has 0 work items and 0 history messages.%n"+
                        "Work items: There are no work items for this member.%n"+
                        "Activity history: There is no activity history for Member Pesho.%n%n]")
                ,testRepository.getAllPeople().values().toString());

    }
}