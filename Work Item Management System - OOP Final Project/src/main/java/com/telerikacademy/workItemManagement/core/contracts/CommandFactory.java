package com.telerikacademy.workItemManagement.core.contracts;

import com.telerikacademy.workItemManagement.commands.contracts.Command;

public interface CommandFactory {
  Command createCommand(
          String commandTypeAsString,
          ModelsRepository modelsRepository,
          ModelsFactory modelsFactory);
}
