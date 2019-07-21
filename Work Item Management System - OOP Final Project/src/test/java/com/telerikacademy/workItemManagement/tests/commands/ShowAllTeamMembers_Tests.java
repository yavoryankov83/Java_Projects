package com.telerikacademy.workItemManagement.tests.commands;


import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.commands.showCommands.ShowAllTeamMembers;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.MemberImpl;
import com.telerikacademy.workItemManagement.models.TeamImpl;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.contracts.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShowAllTeamMembers_Tests {
    private Command testCommand;
    private ModelsRepository testRepository;

    @Before
    public void before() {
        testRepository = new ModelsRepositoryImpl();
        testCommand = new ShowAllTeamMembers(testRepository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedMoreArguments() {
        // Arrange
        String [] testParameters  = new String[2];
        testParameters [0] = "TeamName";
        testParameters [1] = "TeamName";

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test
    public void showAllTeamMembers_should_return_correct_message_when_no_team_members_exists() {
        // Arrange
        String message = "No members exist.";
        testRepository.getAllMembers().clear();
        // Act
        String execute = testCommand.execute();
        //Assert
        Assert.assertEquals(message, execute);
    }

    @Test
    public void showAllTeamMembers_should_return_correct_message_when_members_exist() {
        // Arrange
        String message = "Ivana";
        Team team = new TeamImpl("Barcelona");
        Member member = new MemberImpl("Ivana");
        team.addMember(member);
        testRepository.addToTeams(team.getName(), team);
        testRepository.addToMembers(member.getName(), member);
        // Act
        String execute = testCommand.execute();
        //Assert
        Assert.assertEquals(message, execute);
    }
}
