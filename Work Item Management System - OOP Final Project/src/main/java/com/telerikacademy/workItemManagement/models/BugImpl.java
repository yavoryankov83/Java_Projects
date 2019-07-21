package com.telerikacademy.workItemManagement.models;

import com.telerikacademy.workItemManagement.models.contracts.*;
import com.telerikacademy.workItemManagement.models.enums.PriorityType;
import com.telerikacademy.workItemManagement.models.enums.SeverityType;
import com.telerikacademy.workItemManagement.models.enums.BugStatusType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BugImpl extends AbstractWorkItem implements Bug, Prioritable, Assignable, Statusable {
  private List<String> stepsToReproduce;
  private PriorityType priority;
  private SeverityType severity;
  private BugStatusType status;
  private Member assignee;

  public BugImpl(
          String title,
          String description,
          PriorityType priority,
          SeverityType severity,
          BugStatusType status,
          Member assignee) {
    super(title, description);
    stepsToReproduce = new ArrayList<>();
    setPriority(priority);
    setSeverity(severity);
    setStatus(status);
    this.assignee = assignee;
  }

  @Override
  public List<String> getStepsToReproduce() {
    return Collections.unmodifiableList(stepsToReproduce);
  }

  @Override
  public void addStep(String step) {
    stepsToReproduce.add(step);
  }

  @Override
  public PriorityType getPriority() {
    return priority;
  }

  @Override
  public void changePriority(PriorityType priority) {
    this.priority = priority;
  }

  @Override
  public SeverityType getSeverity() {
    return severity;
  }

  @Override
  public void changeSeverity(SeverityType severity) {
    this.severity = severity;
  }

  @Override
  public String getStatus() {
    return status.toString();
  }

  @Override
  public void changeStatus(String status) {
    setStatus(BugStatusType.valueOf(status));
  }

  @Override
  public Member getAssignee() {
    return assignee;
  }

  @Override
  public String getAdditionalInfo() {

    StringBuilder builder = new StringBuilder();

    builder
            .append(getBaseBugInfo())
            .append(getBugStepsCountInfo());

    getBugStepsInfo(builder);

    getBugAdditionalInfo(builder);

    return builder.toString();
  }

  private String getBaseBugInfo() {
    return String.format("%s with ID %d:%n",
            getType(),
            getId()) +
            String.format("Bug has %d steps to reproduce.%n", getStepsToReproduce().size());
  }

  private String getBugStepsCountInfo() {
    return getStepsToReproduce().isEmpty()
            ? String.format("Steps to reproduce: There are no steps to reproduce for this bug.%n") :
            (getStepsToReproduce().size() == 1 ?
                    String.format("Step to reproduce: %n") :
                    String.format("Steps to reproduce: %n"));
  }

  private void getBugStepsInfo(StringBuilder builder) {
    getStepsToReproduce().forEach(step -> builder.append(String.format("%s%n", step)));
  }

  private void getBugAdditionalInfo(StringBuilder builder) {
    builder
            .append(String.format("Priority: %s%n", getPriority()))
            .append(String.format("Severity: %s%n", getSeverity()))
            .append(String.format("Status: %s%n", getStatus()))
            .append(String.format("Assignee: %s%n", getAssignee().getName()));
  }

  private void setPriority(PriorityType priority) {
    this.priority = priority;
  }

  private void setSeverity(SeverityType severity) {
    this.severity = severity;
  }

  private void setStatus(BugStatusType status) {
    this.status = status;
  }
}
