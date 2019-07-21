package com.telerikacademy.workItemManagement.tests.commands;

import com.telerikacademy.workItemManagement.commands.showCommands.ShowCommands;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShowCommands_Tests {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;
  private static final String CREATION_COMMANDS =
          "createNewPerson PERSON_NAME," +
                  "createNewTeam TEAM_NAME," +
                  "createNewBoardInTeam BOARD_NAME TEAM_NAME," +
                  "createNewBugInBoard " +
                  "BOARD_NAME " +
                  "BUG_TITLE " +
                  "BUG_DESCRIPTION " +
                  "BUG_PRIORITY " +
                  "BUG_SEVERITY " +
                  "BUG_STATUS " +
                  "BUG_MEMBER_NAME " +
                  "STEPS_TO_REPRODUCE_SEPARATED_BY_COMMA," +
                  "createNewStoryInBoard " +
                  "BOARD_NAME " +
                  "STORY_TITLE " +
                  "STORY_DESCRIPTION " +
                  "STORY_PRIORITY " +
                  "STORY_SIZE " +
                  "STORY_STATUS " +
                  "STORY_MEMBER_NAME," +
                  "createNewFeedbackInBoard " +
                  "BOARD_NAME " +
                  "FEEDBACK_TITLE " +
                  "FEEDBACK_DESCRIPTION " +
                  "FEEDBACK_RATING " +
                  "FEEDBACK_STATUS";

  private static final String ADDING_COMMANDS =
          "addPersonToTeam PERSON_NAME TEAM_NAME," +
                  "addCommentToWorkItem WORKITEM_ID AUTHOR_NAME MESSAGE," +
                  "assignWorkItemToPerson WORKITEM_ID PERSON_NAME," +
                  "unAssignWorkItemToPerson WORKITEM_ID PERSON_NAME";

  private static final String CHANGING_COMMANDS =
          "changeBugPriority BUG_ID PRIORITY," +
                  "changeBugSeverity BUG_ID SEVERITY," +
                  "changeBugStatus BUG_ID STATUS," +
                  "changeStoryPriority STORY_ID PRIORITY," +
                  "changeStorySize STORY_ID SIZE," +
                  "changeStoryStatus STORY_ID STATUS," +
                  "changeFeedbackRating FEEDBACK_ID RATING," +
                  "changeFeedbackStatus FEEDBACK_ID STATUS";

  private static final String SHOWING_COMMANDS =
          "showAllPeople," +
                  "showPersonsActivity PERSON_NAME," +
                  "showAllTeams," +
                  "showTeamsActivity TEAM_NAME," +
                  "showAllTeamMembers," +
                  "showAllTeamBoards," +
                  "showBoardsActivity BOARD_NAME," +
                  "listAllWorkItems," +
                  "listAllWorkItemsFilteredByBug," +
                  "listAllWorkItemsFilteredByStory," +
                  "listAllWorkItemsFilteredByFeedback," +
                  "listAllWorkItemsFilteredByStatus," +
                  "listAllWorkItemsFilteredByAssignee," +
                  "listAllWorkItemsFilteredByStatusAndAssignee," +
                  "listAllWorkItemsSortedByTitle," +
                  "listAllWorkItemsSortedByPriority," +
                  "listAllWorkItemsSortedBySeverity," +
                  "listAllWorkItemsSortedBySize," +
                  "listAllWorkItemsSortedByRating";

  private ShowCommands showCommands;

  @Before
  public void before(){
    showCommands = new ShowCommands();
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throwException_when_passed_moreArguments() {
    // Arrange
    String [] testParameters  = new String[1];
    testParameters [0] = "PersonName";

    // Act & Assert
    showCommands.execute(testParameters);
  }

  @Test
  public void ShowCommands_should_return_commands_message_when_pass_no_parameters() {
    //Arrange
    String result = applicationSupportedCommands();

    //Act
    String execute = showCommands.execute();
    //Assert
    Assert.assertEquals(result, execute);
  }

  private void getCommands(StringBuilder builder, String[] commandsAsList) {
    for (String command : commandsAsList) {
      builder.append(command)
              .append(System.lineSeparator());
    }
  }
  private String applicationSupportedCommands() {
    StringBuilder builder = new StringBuilder();

    String[] creationCommands = CREATION_COMMANDS.split(",");
    String[] addingCommands = ADDING_COMMANDS.split(",");
    String[] changingCommands = CHANGING_COMMANDS.split(",");
    String[] showingCommands = SHOWING_COMMANDS.split(",");

    builder
            .append("Work Item Management System supports following commands:")
            .append(System.lineSeparator())
            .append("CREATION COMMANDS:")
            .append(System.lineSeparator());
    getCommands(builder, creationCommands);

    builder.append("ADDING COMMANDS:")
            .append(System.lineSeparator());
    getCommands(builder, addingCommands);

    builder.append("CHANGING COMMANDS:")
            .append(System.lineSeparator());
    getCommands(builder, changingCommands);

    builder.append("SHOWING COMMANDS:")
            .append(System.lineSeparator());
    getCommands(builder, showingCommands);

    builder
            .append(System.lineSeparator())
            .append("To see available application commands enter SHOWCOMMANDS");

    return builder.toString();
  }
}
