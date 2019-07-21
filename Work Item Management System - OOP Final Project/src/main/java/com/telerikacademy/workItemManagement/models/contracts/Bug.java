package com.telerikacademy.workItemManagement.models.contracts;

import com.telerikacademy.workItemManagement.models.enums.SeverityType;

import java.util.List;

public interface Bug extends WorkItem, Prioritable, Assignable, Statusable {
  List<String> getStepsToReproduce();

  void addStep(String step);

  SeverityType getSeverity();

  void changeSeverity(SeverityType severity);
}
