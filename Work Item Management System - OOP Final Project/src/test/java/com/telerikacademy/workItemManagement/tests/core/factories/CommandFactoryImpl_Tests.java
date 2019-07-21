package com.telerikacademy.workItemManagement.tests.core.factories;

import com.telerikacademy.workItemManagement.core.contracts.CommandFactory;
import com.telerikacademy.workItemManagement.core.contracts.ModelsFactory;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.core.factories.CommandFactoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class CommandFactoryImpl_Tests {
  private CommandFactory testCommandFactory;
  private ModelsFactory testFactory;
  private ModelsRepository testRepository;
  private String testCommandTypeAsString;

  @Before
  public void before() {
    testCommandFactory = new CommandFactoryImpl();
    testFactory = Mockito.mock(ModelsFactory.class);
    testRepository = Mockito.mock(ModelsRepository.class);

  }

  @Test(expected = RuntimeException.class)
  public void createCommand_should_throwException_when_passed_invalidCommand() {
    // Arrange
    testCommandTypeAsString = "COMMAND";

    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_SHOWCOMMANDS_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "SHOWCOMMANDS";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_CREATENEWPERSON_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "CREATENEWPERSON";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_SHOWALLPEOPLE_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "SHOWALLPEOPLE";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_SHOWPERSONSACTIVITY_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "SHOWPERSONSACTIVITY";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_CREATENEWTEAM_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "CREATENEWTEAM";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_SHOWALLTEAMS_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "SHOWALLTEAMS";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_SHOWTEAMSACTIVITY_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "SHOWTEAMSACTIVITY";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_createNewCommand_when_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "ADDPERSONTOTEAM";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_SHOWALLTEAMMEMBERS_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "SHOWALLTEAMMEMBERS";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_CREATENEWBOARDINTEAM_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "CREATENEWBOARDINTEAM";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_SHOWALLTEAMBOARDS_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "SHOWALLTEAMBOARDS";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_SHOWBOARDSACTIVITY_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "SHOWBOARDSACTIVITY";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_CREATENEWBUGINBOARD_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "CREATENEWBUGINBOARD";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_CREATENEWSTORYINBOARD_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "CREATENEWSTORYINBOARD";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_CREATENEWFEEDBACKINBOARD_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "CREATENEWFEEDBACKINBOARD";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_CHANGEBUGPRIORITY_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "CHANGEBUGPRIORITY";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_CHANGEBUGSEVERITY_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "CHANGEBUGSEVERITY";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_CHANGEBUGSTATUS_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "CHANGEBUGSTATUS";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_CHANGESTORYPRIORITY_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "CHANGESTORYPRIORITY";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_CHANGESTORYSIZE_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "CHANGESTORYSIZE";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_CHANGESTORYSTATUS_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "CHANGESTORYSTATUS";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_CHANGEFEEDBACKRATING_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "CHANGEFEEDBACKRATING";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_CHANGEFEEDBACKSTATUS_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "CHANGEFEEDBACKSTATUS";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_ASSIGNWORKITEMTOPERSON_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "ASSIGNWORKITEMTOPERSON";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_UNASSIGNWORKITEMTOPERSON_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "UNASSIGNWORKITEMTOPERSON";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_ADDCOMMENTTOWORKITEM_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "ADDCOMMENTTOWORKITEM";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_LISTALLWORKITEMS_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "LISTALLWORKITEMS";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_LISTALLBUGWORKITEMS_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "LISTALLWORKITEMSFILTEREDBYBUG";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_LISTALLSTORYWORKITEMS_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "LISTALLWORKITEMSFILTEREDBYSTORY";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_LISTALLFEEDBACKWORKITEMS_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "LISTALLWORKITEMSFILTEREDBYFEEDBACK";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_LISTALLWORKITEMFSFILTEREDBYSTATUS_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "LISTALLWORKITEMSFILTEREDBYSTATUS";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_LISTALWORKITEMSFILTEREDBYASSIGNEE_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "LISTALLWORKITEMSFILTEREDBYASSIGNEE";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_LISTALLWORKITEMSFILTEREDBYSTATUSANDASSIGNEE_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "LISTALLWORKITEMSFILTEREDBYSTATUSANDASSIGNEE";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_LISTALLWORKITEMSSORTEDBYTITLE_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "LISTALLWORKITEMSSORTEDBYTITLE";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_LISTALLWORKITEMSSORTEDBYPRIORITY_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "LISTALLWORKITEMSSORTEDBYPRIORITY";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_LISTALLWORKITEMSSORTEDBYSEVERITY_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "LISTALLWORKITEMSSORTEDBYSEVERITY";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_LISTALLWORKITEMSSORTEDBYSIZE_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "LISTALLWORKITEMSSORTEDBYSIZE";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }

  @Test
  public void createCommand_should_create_LISTALLWORKITEMSSORTEDBYRATING_when_call_command_and_passedValidParameters() {
    // Arrange
    testCommandTypeAsString = "LISTALLWORKITEMSSORTEDBYRATING";


    // Act & Assert
    testCommandFactory.createCommand(testCommandTypeAsString, testRepository, testFactory);
  }
}
