package com.telerikacademy.workItemManagement.commands.addingCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.contracts.Team;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class AddPersonToTeam implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;

  private ModelsRepository modelsRepository;

  public AddPersonToTeam(ModelsRepository modelsRepository) {
    this.modelsRepository = modelsRepository;
  }

  public String execute(String... parameters) {
    Validator.validateCommandArgumentsCount(
            parameters,
            CORRECT_NUMBER_OF_ARGUMENTS,
            String.format(
                    INVALID_NUMBER_OF_ARGUMENTS_MESSAGE,
                    CORRECT_NUMBER_OF_ARGUMENTS,
                    parameters.length));

    String personName = parameters[0];
    String teamName = parameters[1];

    return addPersonToTeam(personName, teamName);
  }

  private String addPersonToTeam(String personName, String teamName) {
    if (!modelsRepository.getAllPeople().containsKey(personName)) {
      throw new IllegalArgumentException(String.format(PERSON_NOT_EXISTS_MESSAGE, personName));
    }
    if (!modelsRepository.getAllTeams().containsKey(teamName)) {
      throw new IllegalArgumentException(String.format(TEAM_NOT_EXISTS_MESSAGE, teamName));
    }
    if (modelsRepository.getAllMembers().containsKey(personName)) {
      return String.format(PERSON_ALREADY_ADDED_TO_TEAM_MESSAGE, personName);
    }

    Team team = modelsRepository.getAllTeams().get(teamName);
    Member person = modelsRepository.getAllPeople().get(personName);

    team.addMember(person);
    modelsRepository.addToMembers(personName, person);
    modelsRepository.addToMembersAssignedToTeam(personName, teamName);
    person.addToActivityHistory(String.format(PERSON_ADDED_TO_TEAM_SUCCESS_MESSAGE, personName, teamName));

    return String.format(PERSON_ADDED_TO_TEAM_SUCCESS_MESSAGE, personName, teamName);
  }
}
