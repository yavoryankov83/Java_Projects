package com.telerikacademy.workItemManagement.models.contracts;

import com.telerikacademy.workItemManagement.models.enums.PriorityType;

public interface Prioritable extends WorkItem {
  PriorityType getPriority();

  void changePriority(PriorityType priority);
}
