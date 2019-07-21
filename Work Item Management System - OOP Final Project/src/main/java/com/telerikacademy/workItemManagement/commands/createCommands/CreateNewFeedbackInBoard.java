package com.telerikacademy.workItemManagement.commands.createCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsFactory;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class CreateNewFeedbackInBoard implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 5;

  private ModelsRepository modelsRepository;
  private ModelsFactory modelsFactory;

  public CreateNewFeedbackInBoard(ModelsRepository modelsRepository,
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

    String boardName = parameters[0];
    String title = parameters[1];
    String description = parameters[2];
    int rating = Integer.parseInt(parameters[3]);
    String status = parameters[4];

    return createNewFeedbackInBoard(
            boardName,
            title,
            description,
            rating,
            status);
  }

  private String createNewFeedbackInBoard(
          String boardName,
          String title,
          String description,
          int rating,
          String status) {

    List<Board> allBoardsWithGivenName = modelsRepository.getAllTeams().values()
            .stream()
            .flatMap(team -> team.getBoards()
                    .stream())
            .filter(board -> board.getName().equals(boardName))
            .collect(Collectors.toList());

    if (allBoardsWithGivenName.isEmpty()) {
      throw new IllegalArgumentException(String.format(BOARD_NAME_NOT_EXISTS_MESSAGE, boardName));
    }

    Feedback feedback = modelsFactory.createFeedback(title, description, rating, status);

    Board board = allBoardsWithGivenName.get(0);
    board.addWorkItem(feedback);
    modelsRepository.addToWorkItems(feedback.getId(), feedback);
    modelsRepository.addToFeedbacks(feedback.getId(), feedback);
    board.addToActivityHistory(
            String.format(
                    FEEDBACK_IN_BOARD_CREATED_SUCCESS_MESSAGE,
            feedback.getId(),
            feedback.getTitle(),
            boardName));
    feedback.addToActivityHistory(
            String.format(
                    FEEDBACK_IN_BOARD_CREATED_SUCCESS_MESSAGE,
            feedback.getId(),
            feedback.getTitle(),
            boardName));

    return String.format(
            FEEDBACK_IN_BOARD_CREATED_SUCCESS_MESSAGE,
            feedback.getId(),
            feedback.getTitle(),
            boardName);
  }
}
