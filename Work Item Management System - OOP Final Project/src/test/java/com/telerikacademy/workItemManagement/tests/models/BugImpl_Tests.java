package com.telerikacademy.workItemManagement.tests.models;

import com.telerikacademy.workItemManagement.models.BugImpl;
import com.telerikacademy.workItemManagement.models.MemberImpl;
import com.telerikacademy.workItemManagement.models.contracts.Bug;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.enums.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BugImpl_Tests {

  @Test(expected = UnsupportedOperationException.class)
  public void bug_getStepsToReproduce_should_returnShallowCopy() {
    // Arrange
    Bug bug = new BugImpl(
            "1234567890",
            "1234567890",
            PriorityType.HIGH,
            SeverityType.CRITICAL,
            BugStatusType.ACTIVE,
            new MemberImpl("Ivana"));

    // Act
    List<String> supposedShallowCopy = bug.getStepsToReproduce();

    // Assert
    supposedShallowCopy.add("First step");
  }

  @Test
  public void addStep_should_increaseGetStepsToReproduce() {
    // Arrange
    Bug bug = new BugImpl(
            "1234567890",
            "1234567890",
            PriorityType.HIGH,
            SeverityType.CRITICAL,
            BugStatusType.ACTIVE,
            new MemberImpl("Ivana"));

    // Act
    bug.addStep("Step");

    // Assert
    Assert.assertEquals(1, bug.getStepsToReproduce().size());
  }

  @Test
  public void getPriority_should_return_priority() {
    // Arrange
    Bug bug = new BugImpl(
            "1234567890",
            "1234567890",
            PriorityType.HIGH,
            SeverityType.CRITICAL,
            BugStatusType.ACTIVE,
            new MemberImpl("Ivana"));
    // Act
    String priority = bug.getPriority().toString();
    // Assert
    Assert.assertEquals("High", priority);
  }

  @Test
  public void getSeverity_should_return_severity() {
    // Arrange
    Bug bug = new BugImpl(
            "1234567890",
            "1234567890",
            PriorityType.HIGH,
            SeverityType.CRITICAL,
            BugStatusType.ACTIVE,
            new MemberImpl("Ivana"));
    // Act
    String severity = bug.getSeverity().toString();
    // Assert
    Assert.assertEquals("Critical", severity);
  }

  @Test
  public void getStatus_should_return_status() {
    // Arrange
    Bug bug = new BugImpl(
            "1234567890",
            "1234567890",
            PriorityType.HIGH,
            SeverityType.CRITICAL,
            BugStatusType.ACTIVE,
            new MemberImpl("Ivana"));
    // Act
    String status = "Active";
    // Assert
    Assert.assertEquals(status, bug.getStatus());
  }

  @Test
  public void getAssignee_should_return_assignee() {
    // Arrange
    Member member = new MemberImpl("Ivana");
    Bug bug = new BugImpl(
            "1234567890",
            "1234567890",
            PriorityType.HIGH,
            SeverityType.CRITICAL,
            BugStatusType.ACTIVE,
            member);
    // Act

    // Assert
    Assert.assertEquals(member, bug.getAssignee());
  }

  @Test
  public void getStatusType_should_return_statusType() {
    // Arrange
    Member member = new MemberImpl("Ivana");
    Bug bug = new BugImpl(
            "1234567890",
            "1234567890",
            PriorityType.HIGH,
            SeverityType.CRITICAL,
            BugStatusType.ACTIVE,
            member);
    // Act
    String status = "Active";
    // Assert
    Assert.assertEquals(status, bug.getStatus());
  }

  @Test
  public void getAdditionalInfo_should_return_correctSingleOutput() {
    // Arrange
    Member member = new MemberImpl("Ivana");
    BugImpl bug = new BugImpl(
            "1234567890",
            "1234567890",
            PriorityType.HIGH,
            SeverityType.CRITICAL,
            BugStatusType.ACTIVE,
            member);
    StringBuilder builder = new StringBuilder();
    // Act
    builder
            .append(String.format("%s with ID %d:%n",
                    bug.getType(),
                    bug.getId()))
            .append(String.format("Bug has %d steps to reproduce.%n", bug.getStepsToReproduce().size()))
            .append(String.format("Steps to reproduce: There are no steps to reproduce for this bug.%n"))
            .append(String.format("Priority: %s%n", bug.getPriority()))
            .append(String.format("Severity: %s%n", bug.getSeverity()))
            .append(String.format("Status: %s%n", bug.getStatus()))
            .append(String.format("Assignee: %s%n", bug.getAssignee().getName()));
    // Assert
    Assert.assertEquals(builder.toString(), bug.getAdditionalInfo());
  }

  @Test
  public void getAdditionalInfo_should_return_correctMultipleOutput() {
    // Arrange
    Member member = new MemberImpl("Ivana");
    BugImpl bug = new BugImpl(
            "1234567890",
            "1234567890",
            PriorityType.HIGH,
            SeverityType.CRITICAL,
            BugStatusType.ACTIVE,
            member);
    StringBuilder builder = new StringBuilder();
    // Act
    String step = "Step";
    bug.addStep(step);
    builder
            .append(String.format("%s with ID %d:%n",
                    bug.getType(),
                    bug.getId()))
            .append(String.format("Bug has %d steps to reproduce.%n", bug.getStepsToReproduce().size()))
            .append(String.format("Step to reproduce: %n"))
            .append(String.format("%s%n", step))
            .append(String.format("Priority: %s%n", bug.getPriority()))
            .append(String.format("Severity: %s%n", bug.getSeverity()))
            .append(String.format("Status: %s%n", bug.getStatus()))
            .append(String.format("Assignee: %s%n", bug.getAssignee().getName()));
    // Assert
    Assert.assertEquals(builder.toString(), bug.getAdditionalInfo());
  }

  @Test
  public void getAdditionalInfo_should_return_correctMultipleOutput_for_stepsToReproduce() {
    // Arrange
    Member member = new MemberImpl("Ivana");
    BugImpl bug = new BugImpl(
            "1234567890",
            "1234567890",
            PriorityType.HIGH,
            SeverityType.CRITICAL,
            BugStatusType.ACTIVE,
            member);
    StringBuilder builder = new StringBuilder();
    // Act
    String step = "Step";
    bug.addStep(step);
    bug.addStep(step);
    builder
            .append(String.format("%s with ID %d:%n",
                    bug.getType(),
                    bug.getId()))
            .append(String.format("Bug has %d steps to reproduce.%n", bug.getStepsToReproduce().size()))
            .append(String.format("Steps to reproduce: %n"))
            .append((String.format("%s%n", step)))
            .append((String.format("%s%n", step)))
            .append(String.format("Priority: %s%n", bug.getPriority()))
            .append(String.format("Severity: %s%n", bug.getSeverity()))
            .append(String.format("Status: %s%n", bug.getStatus()))
            .append(String.format("Assignee: %s%n", bug.getAssignee().getName()));
    // Assert
    Assert.assertEquals(builder.toString(), bug.getAdditionalInfo());
  }
}
