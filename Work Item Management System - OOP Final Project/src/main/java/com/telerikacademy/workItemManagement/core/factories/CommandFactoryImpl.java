package com.telerikacademy.workItemManagement.core.factories;

import com.telerikacademy.workItemManagement.commands.addingCommands.*;
import com.telerikacademy.workItemManagement.commands.changingCommands.*;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.commands.createCommands.*;
import com.telerikacademy.workItemManagement.commands.enums.CommandType;
import com.telerikacademy.workItemManagement.commands.showCommands.*;
import com.telerikacademy.workItemManagement.core.contracts.*;

import java.util.Arrays;

public class CommandFactoryImpl implements CommandFactory {
  private static final String INVALID_COMMAND_EXCEPTION =
          "Invalid command name: %s!";
  private static final String INPUTED_COMMAND_NOT_SUPPORTED =
          "Inputed command %s is not supported by te application, please enter SHOWCOMMANDS to see available commands.";

  public Command createCommand(
          String commandTypeAsString,
          ModelsRepository modelsRepository,
          ModelsFactory modelsFactory) {

    try {
      CommandType.valueOf(commandTypeAsString.toUpperCase());
    } catch (IllegalArgumentException ex) {
      throw new RuntimeException(
              String.format(INPUTED_COMMAND_NOT_SUPPORTED,
                      commandTypeAsString.toUpperCase()));
    }

    CommandType commandType = CommandType.valueOf(commandTypeAsString.toUpperCase());
    switch (commandType) {

      case SHOWCOMMANDS:
        return new ShowCommands();

      case CREATENEWPERSON:
        return new CreateNewPerson(modelsRepository, modelsFactory);

      case SHOWALLPEOPLE:
        return new ShowAllPeople(modelsRepository);

      case SHOWPERSONSACTIVITY:
        return new ShowPersonsActivity(modelsRepository);

      case CREATENEWTEAM:
        return new CreateNewTeam(modelsRepository, modelsFactory);

      case SHOWALLTEAMS:
        return new ShowAllTeams(modelsRepository);

      case SHOWTEAMSACTIVITY:
        return new ShowTeamsActivity(modelsRepository);

      case ADDPERSONTOTEAM:
        return new AddPersonToTeam(modelsRepository);

      case SHOWALLTEAMMEMBERS:
        return new ShowAllTeamMembers(modelsRepository);

      case CREATENEWBOARDINTEAM:
        return new CreateNewBoardInTeam(modelsRepository, modelsFactory);

      case SHOWALLTEAMBOARDS:
        return new ShowAllTeamBoards(modelsRepository);

      case SHOWBOARDSACTIVITY:
        return new ShowBoardsActivity(modelsRepository);

      case CREATENEWBUGINBOARD:
        return new CreateNewBugInBoard(modelsRepository, modelsFactory);

      case CREATENEWSTORYINBOARD:
        return new CreateNewStoryInBoard(modelsRepository, modelsFactory);

      case CREATENEWFEEDBACKINBOARD:
        return new CreateNewFeedbackInBoard(modelsRepository, modelsFactory);

      case CHANGEBUGPRIORITY:
        return new ChangeBugPriority(modelsRepository);

      case CHANGEBUGSEVERITY:
        return new ChangeBugSeverity(modelsRepository);

      case CHANGEBUGSTATUS:
        return new ChangeBugStatus(modelsRepository);

      case CHANGESTORYPRIORITY:
        return new ChangeStoryPriority(modelsRepository);

      case CHANGESTORYSIZE:
        return new ChangeStorySize(modelsRepository);

      case CHANGESTORYSTATUS:
        return new ChangeStoryStatus(modelsRepository);

      case CHANGEFEEDBACKRATING:
        return new ChangeFeedbackRating(modelsRepository);

      case CHANGEFEEDBACKSTATUS:
        return new ChangeFeedbackStatus(modelsRepository);

      case ASSIGNWORKITEMTOPERSON:
        return new AssignWorkItemToPerson(modelsRepository);

      case UNASSIGNWORKITEMTOPERSON:
        return new UnAssignWorkItemToPerson(modelsRepository);

      case ADDCOMMENTTOWORKITEM:
        return new AddCommentToWorkItem(modelsRepository, modelsFactory);

      case LISTALLWORKITEMS:
        return new ListAllWorkItems(modelsRepository);

      case LISTALLWORKITEMSFILTEREDBYBUG:
        return new ListWorkItemsFilteredByBug(modelsRepository);

      case LISTALLWORKITEMSFILTEREDBYSTORY:
        return new ListWorkItemsFilteredByStory(modelsRepository);

      case LISTALLWORKITEMSFILTEREDBYFEEDBACK:
        return new ListWorkItemsFilteredByFeedback(modelsRepository);

      case LISTALLWORKITEMSFILTEREDBYSTATUS:
        return new ListAllWorkItemsFilteredByStatus(modelsRepository);

      case LISTALLWORKITEMSFILTEREDBYASSIGNEE:
        return new ListAllWorkItemsFilteredByAssignee(modelsRepository);

      case LISTALLWORKITEMSFILTEREDBYSTATUSANDASSIGNEE:
        return new ListAllWorkItemsFilteredByStatusAndAssignee(modelsRepository);

      case LISTALLWORKITEMSSORTEDBYTITLE:
        return new ListAllWorkItemsSortedByTitle(modelsRepository);

      case LISTALLWORKITEMSSORTEDBYPRIORITY:
        return new ListAllWorkItemsSortedByPriority(modelsRepository);

      case LISTALLWORKITEMSSORTEDBYSEVERITY:
        return new ListAllWorkItemsSortedBySeverity(modelsRepository);

      case LISTALLWORKITEMSSORTEDBYSIZE:
        return new ListAllWorkItemsSortedBySize(modelsRepository);

      case LISTALLWORKITEMSSORTEDBYRATING:
        return new ListAllWorkItemsSortedByRating(modelsRepository);
    }

    throw new IllegalArgumentException(String.format(INVALID_COMMAND_EXCEPTION, commandTypeAsString));
  }
}
