package com.telerikacademy.workItemManagement.commands.createCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsFactory;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.Member;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class CreateNewPerson implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;

  private ModelsRepository modelsRepository;
  private ModelsFactory modelsFactory;

  public CreateNewPerson(
          ModelsRepository modelsRepository,
          ModelsFactory modelsFactory) {
    this.modelsRepository = modelsRepository;
    this.modelsFactory = modelsFactory;
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

    return createNewPerson(personName);
  }

  private String createNewPerson(String personName) {
    if (modelsRepository.getAllPeople().containsKey(personName)) {
      throw new IllegalArgumentException(String.format(PERSON_EXISTS_MESSAGE, personName));
    }

    Member person = modelsFactory.createMember(personName);

    modelsRepository.addToPeople(personName, person);

    person.addToActivityHistory(String.format(PERSON_CREATED_SUCCESS_MESSAGE, personName));

    return String.format(PERSON_CREATED_SUCCESS_MESSAGE, personName);
  }
}
