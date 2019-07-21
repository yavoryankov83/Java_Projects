package com.telerikacademy.workItemManagement.models.contracts;

public interface Assignable extends WorkItem {
  Member getAssignee();
}
