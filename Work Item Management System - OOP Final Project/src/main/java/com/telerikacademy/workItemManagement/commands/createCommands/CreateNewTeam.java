package com.telerikacademy.workItemManagement.commands.createCommands;//package com.telerikacademy.workItemManagement.commands.creation;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsFactory;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.Team;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class CreateNewTeam implements Command {

  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;

  private ModelsRepository modelsRepository;
  private ModelsFactory factory;

  public CreateNewTeam(ModelsRepository modelsRepository, ModelsFactory factory) {
    this.modelsRepository = modelsRepository;
    this.factory = factory;
  }

  public String execute(String... parameters) {
    Validator.validateCommandArgumentsCount(
            parameters,
            CORRECT_NUMBER_OF_ARGUMENTS,
            String.format(
                    INVALID_NUMBER_OF_ARGUMENTS_MESSAGE,
                    CORRECT_NUMBER_OF_ARGUMENTS,
                    parameters.length));

    String teamName = parameters[0];

    return createNewTeam(teamName);
  }

  private String createNewTeam(String teamName) {
    if (modelsRepository.getAllTeams().containsKey(teamName)) {
      throw new IllegalArgumentException(String.format(TEAM_EXISTS_MESSAGE, teamName));
    }

    Team team = factory.createTeam(teamName);
    modelsRepository.addToTeams(teamName, team);

    return String.format(TEAM_CREATED_SUCCESS_MESSAGE, teamName);
  }
}
