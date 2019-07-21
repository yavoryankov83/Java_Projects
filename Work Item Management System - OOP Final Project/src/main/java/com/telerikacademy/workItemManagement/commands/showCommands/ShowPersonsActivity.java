package com.telerikacademy.workItemManagement.commands.showCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.Member;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.INVALID_NUMBER_OF_ARGUMENTS_MESSAGE;
import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.PERSON_NOT_EXISTS_MESSAGE;

public class ShowPersonsActivity implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;

  private ModelsRepository modelsRepository;

  public ShowPersonsActivity(ModelsRepository modelsRepository) {
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

    return showPersonsActivity(personName);
  }

  private String showPersonsActivity(String personName) {
    if (!modelsRepository.getAllPeople().containsKey(personName)) {
      throw new IllegalArgumentException(String.format(PERSON_NOT_EXISTS_MESSAGE, personName));
    }

    Member person = modelsRepository.getAllPeople().get(personName);

    return person.showActivityHistory();
  }
}
