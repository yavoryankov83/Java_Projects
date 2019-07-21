package com.telerikacademy.workItemManagement.tests.commands;

import com.telerikacademy.workItemManagement.commands.addingCommands.AssignWorkItemToPerson;
import com.telerikacademy.workItemManagement.commands.addingCommands.UnAssignWorkItemToPerson;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.models.BugImpl;
import com.telerikacademy.workItemManagement.models.MemberImpl;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.contracts.WorkItem;
import com.telerikacademy.workItemManagement.models.enums.BugStatusType;
import com.telerikacademy.workItemManagement.models.enums.PriorityType;
import com.telerikacademy.workItemManagement.models.enums.SeverityType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UnassignWorkItemToPerson_Tests {
  private Command testCommand;
  private Command testCommand_1;
  private Member testPerson;
  private WorkItem testWorkItem;
  private ModelsRepository testRepository;

  @Before
  public void before() {
    testRepository = new ModelsRepositoryImpl();
    testCommand = new UnAssignWorkItemToPerson(testRepository);
    testCommand_1 = new AssignWorkItemToPerson(testRepository);
    testPerson = new MemberImpl("PersonName");
    testWorkItem = new BugImpl("NewBugName", "Description",
            PriorityType.HIGH, SeverityType.CRITICAL, BugStatusType.ACTIVE, testPerson);
    testRepository.addToMembers("PersonName", testPerson);
    testRepository.addToPeople("PersonName", testPerson);
    testRepository.addToWorkItems(1L, testWorkItem);
    testRepository.addToMembers(testPerson.getName(), testPerson);
    testRepository.addToMembersAssignedToTeam("PersonName", "TeamName");
    testPerson.addWorkItem(testWorkItem);
    testRepository.addToWorkItemsAlreadyAssigned(1L, testWorkItem);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throwException_when_passedLessArguments() {
    // Arrange
    String[] testParameters = new String[1];
    testParameters[0] = "1";

    // Act & Assert
    testCommand.execute(testParameters);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throwException_when_passedMoreArguments() {
    // Arrange
    String[] testParameters = new String[3];
    testParameters[0] = "1";
    testParameters[1] = "PersonName";
    testParameters[2] = "TeamName";

    // Act & Assert
    testCommand.execute(testParameters);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throwException_when_passedInvalid_person() {
    // Arrange
    String[] testParameters = new String[2];
    testParameters[0] = "1";
    testParameters[1] = "Person Name";

    // Act & Assert
    testCommand.execute(testParameters);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throwException_when_passed_unassigned_member() {
    // Arrange
    String[] testParameters = new String[2];
    testParameters[0] = "1";
    testParameters[1] = "PersonName";
    testRepository.removeFromWorkItemsAlreadyAssigned(1L, testWorkItem);

    // Act & Assert
    testCommand.execute(testParameters);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throwException_when_passed_invalid_workItem() {
    // Arrange
    String[] testParameters = new String[2];
    testParameters[0] = "5";
    testParameters[1] = "PersonName";

    // Act&&Assert
    testCommand.execute(testParameters);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throwException_when_passedInvalid_id_workItem() {
    // Arrange
    String[] testParameters = new String[2];
    testParameters[0] = "PersonName";
    testParameters[1] = "PersonName";

    // Act & Assert
    testCommand.execute(testParameters);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throwException_when_work_item_is_not_assigned_to_person() {
    // Arrange
    Member member = new MemberImpl("Ivana");
    WorkItem bug = new BugImpl(
            "NewBugName",
            "Description",
            PriorityType.HIGH,
            SeverityType.CRITICAL,
            BugStatusType.ACTIVE,
            member);
    testRepository.addToMembers(member.getName(), member);
    testRepository.addToWorkItems(bug.getId(), bug);
    testRepository.addToWorkItemsAlreadyAssigned(bug.getId(), bug);

    String[] testParameters = new String[2];
    testParameters[0] = String.valueOf(bug.getId());
    testParameters[1] = member.getName();

    // Act & Assert
    testCommand.execute(testParameters);
  }

  @Test
  public void execute_should_return_correct_message_when_work_item_is_unassigned_from_person() {
    // Arrange
    Member member = new MemberImpl("Ivana");
    WorkItem bug = new BugImpl(
            "NewBugName",
            "Description",
            PriorityType.HIGH,
            SeverityType.CRITICAL,
            BugStatusType.ACTIVE,
            member);
    testRepository.addToMembers(member.getName(), member);
    testRepository.addToWorkItems(bug.getId(), bug);
    member.addWorkItem(bug);
    testRepository.addToWorkItemsAlreadyAssigned(bug.getId(), bug);

    String[] testParameters = new String[2];
    testParameters[0] = String.valueOf(bug.getId());
    testParameters[1] = member.getName();

    // Act
    String execute = testCommand.execute(testParameters);
    //Assert
    Assert.assertEquals(
            String.format(
                    "Work item with ID %d removed from member %s.",
                    bug.getId(),
                    member.getName()),
            execute);
  }
}
