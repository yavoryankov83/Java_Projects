package com.telerikacademy.workItemManagement.commands.showCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.Team;

import java.util.Collection;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.INVALID_NUMBER_OF_ARGUMENTS_MESSAGE;
import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.NO_TEAMS_EXIST_MESSAGE;

public class ShowAllTeams implements Command {

  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;

  private ModelsRepository modelsRepository;

  public ShowAllTeams(ModelsRepository modelsRepository) {
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

    return showAllTeams();
  }

  private String showAllTeams() {
    if (modelsRepository.getAllTeams().isEmpty()) {
      return NO_TEAMS_EXIST_MESSAGE;
    }

    Collection<Team> allTeams = modelsRepository.getAllTeams().values();

    StringBuilder builder = new StringBuilder();
    allTeams.forEach(team -> builder.append(String.format("%s, ", team.getName())));

    return builder.toString().substring(0, builder.length() - 2);
  }
}
