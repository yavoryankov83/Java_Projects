package com.telerikacademy.workItemManagement.commands.createCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsFactory;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class CreateNewStoryInBoard implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 7;

  private ModelsRepository modelsRepository;
  private ModelsFactory modelsFactory;

  public CreateNewStoryInBoard(ModelsRepository modelsRepository,
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
    String priority = parameters[3];
    String size = parameters[4];
    String status = parameters[5];
    String personName = parameters[6];

    return createNewStoryInBoard(
            boardName,
            title,
            description,
            priority,
            size,
            status,
            personName);
  }

  private String createNewStoryInBoard(
          String boardName,
          String title,
          String description,
          String priority,
          String size,
          String status,
          String personName) {

    List<Board> allBoardsWithGivenName = modelsRepository.getAllTeams().values()
            .stream()
            .flatMap(team -> team.getBoards()
                    .stream())
            .filter(board -> board.getName().equals(boardName))
            .collect(Collectors.toList());

    if (allBoardsWithGivenName.isEmpty()) {
      throw new IllegalArgumentException(String.format(BOARD_NAME_NOT_EXISTS_MESSAGE, boardName));
    }

    if (!modelsRepository.getMembersAssignedToTeam().containsKey(personName)) {
      throw new IllegalArgumentException(String.format(PERSON_NAME_NOT_ASSIGN_TO_TEAM, personName));
    }

    String teamName = modelsRepository.getMembersAssignedToTeam().get(personName);

    Team team = modelsRepository.getAllTeams().get(teamName);

    List<Board> boardsWithGivenName = team.getBoards().
            stream()
            .filter(board -> board.getName().equals(boardName))
            .collect(Collectors.toList());

    if (boardsWithGivenName.isEmpty()) {
      throw new IllegalArgumentException(String.format(NO_BOARDS_WITH_GIVEN_NAME_EXIST_MESSAGE, boardName));
    }

    Board board = boardsWithGivenName.get(0);
    Member memberOfStory = modelsRepository.getAllMembers().get(personName);
    Story story = modelsFactory.createStory(title, description, priority, size, status, memberOfStory);

    board.addWorkItem(story);
    modelsRepository.addToWorkItems(story.getId(), story);
    modelsRepository.addToStories(story.getId(), story);
    board.addToActivityHistory(
            String.format(
                    STORY_IN_BOARD_CREATED_SUCCESS_MESSAGE,
            story.getId(),
            story.getTitle(),
            personName,
            boardName));
    story.addToActivityHistory(
            String.format(
                    STORY_IN_BOARD_CREATED_SUCCESS_MESSAGE,
            story.getId(),
            story.getTitle(),
            personName,
            boardName));

    memberOfStory.addToActivityHistory(
            String.format(
                    STORY_IN_BOARD_CREATED_SUCCESS_MESSAGE,
            story.getId(),
            story.getTitle(),
            personName,
            boardName));

    return String.format(
            STORY_IN_BOARD_CREATED_SUCCESS_MESSAGE,
            story.getId(),
            story.getTitle(),
            personName,
            boardName);
  }
}
