package com.telerikacademy.workItemManagement.models;

import com.telerikacademy.workItemManagement.models.contracts.*;
import com.telerikacademy.workItemManagement.models.enums.PriorityType;
import com.telerikacademy.workItemManagement.models.enums.SizeType;
import com.telerikacademy.workItemManagement.models.enums.StoryStatusType;

public class StoryImpl extends AbstractWorkItem implements Story, Prioritable, Assignable, Statusable {

  private PriorityType priority;
  private SizeType size;
  private StoryStatusType status;
  private Member assignee;

  public StoryImpl(
          String title,
          String description,
          PriorityType priority,
          SizeType size,
          StoryStatusType status,
          Member assignee) {
    super(title, description);
    setPriority(priority);
    setSize(size);
    setStatus(status);
    this.assignee = assignee;
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
  public SizeType getSize() {
    return size;
  }

  @Override
  public void changeSize(SizeType size) {
    this.size = size;
  }

  @Override
  public Member getAssignee() {
    return assignee;
  }

  @Override
  public String getStatus() {
    return status.toString();
  }

  @Override
  public void changeStatus(String status) {
    setStatus(StoryStatusType.valueOf(status));
  }

  @Override
  public String getAdditionalInfo() {
    StringBuilder builder = new StringBuilder();

    builder
            .append(String.format("%s with ID %d:%n",
                    getType(),
                    getId()))
            .append(String.format("Priority: %s%n", getPriority()))
            .append(String.format("Size: %s%n", getSize()))
            .append(String.format("Status: %s%n", getStatus()))
            .append(String.format("Assignee: %s%n", getAssignee().getName()));

    return builder.toString();
  }

  private void setPriority(PriorityType priority) {
    this.priority = priority;
  }

  private void setSize(SizeType size) {
    this.size = size;
  }

  private void setStatus(StoryStatusType status) {
    this.status = status;
  }
}
