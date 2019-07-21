package com.telerikacademy.workItemManagement.commands.contracts;

public interface Command {
  String execute(String... parameters);
}
