package com.telerikacademy.workItemManagement.tests.commands;

import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.commands.createCommands.CreateNewPerson;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.core.contracts.ModelsFactory;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.core.factories.ModelsFactoryImpl;
import com.telerikacademy.workItemManagement.models.MemberImpl;
import com.telerikacademy.workItemManagement.models.TeamImpl;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.contracts.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CreateNewPerson_Test {
    private Command testCommand;
    private ModelsRepository testRepository;
    private ModelsFactory testFactory;

    @Before
    public void before() {
        testFactory = new ModelsFactoryImpl();
        testRepository = new ModelsRepositoryImpl();
        testCommand = new CreateNewPerson(testRepository, testFactory);
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

    @Test
    public void execute_should_createNewPerson_when_inputIsValid() {
        // Arrange
        String [] testParameters  = new String[1];
        testParameters [0] = "PersonName";

        // Act
        testCommand.execute(testParameters);

        // Assert
        Assert.assertEquals(1, testRepository.getAllPeople().values().size());

    }

    @Test
    public void execute_should_collect_activityHistory_when_inputIsValid() {
        // Arrange
        String [] testParameters  = new String[1];
        testParameters [0] = "PersonName";

        // Act
        testCommand.execute(testParameters);

        // Assert
        Assert.assertEquals("Person PersonName was created.",
                testRepository.getAllPeople().get("PersonName").getActivityHistory().get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_person_exists() {
        // Arrange
        Member member = new MemberImpl("Ivana");
        testRepository.addToPeople(member.getName(), member);
        String personName = "Ivana";

        // Act & Assert
        testCommand.execute(personName);
    }
}