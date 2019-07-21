package com.telerikacademy.workItemManagement.models.contracts;

import com.telerikacademy.workItemManagement.models.enums.SizeType;

public interface Story extends WorkItem, Prioritable, Assignable, Statusable {
  SizeType getSize();

  Member getAssignee();

  void changeSize(SizeType size);
}
