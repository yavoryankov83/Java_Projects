package dispachers;

import enums.CommandType;
import exceptionMessages.ExceptionMessages;
import exceptions.BaseException;
import exceptions.InvalidCommandException;
import factories.CommandFactory;
import interfaces.Battleground;
import interfaces.Command;
import interfaces.Repository;
import interfaces.Unit;

import java.util.HashMap;
import java.util.Map;

public class CommandDispatcher {

  private Map<String, Command> commands;

  public CommandDispatcher(Battleground battleground, Repository<Unit> unitRepository) {
    this.commands = new HashMap<>();
    this.seedCommands(battleground, unitRepository);
  }

  public String dispatchCommand(String[] args) throws BaseException {
    String command = args[0];

    if (commands.containsKey(command)) {
      return this.commands.get(command).execute(args);
    }

    throw new InvalidCommandException(ExceptionMessages.INVALID_COMMAND_EXCEPTION_MESSAGE);
  }

  private void seedCommands(Battleground battleground, Repository<Unit> unitRepository) {
    commands.put(CommandType.SPAWN.toString(), CommandFactory.createCommand(CommandType.SPAWN, battleground, unitRepository));
    commands.put(CommandType.MOVE.toString(), CommandFactory.createCommand(CommandType.MOVE, battleground, unitRepository));
    commands.put(CommandType.FIGHT.toString(), CommandFactory.createCommand(CommandType.FIGHT, battleground, unitRepository));
    commands.put(CommandType.STATUS.toString(), CommandFactory.createCommand(CommandType.STATUS, battleground, unitRepository));
    commands.put(CommandType.GAME_OVER.toString(), CommandFactory.createCommand(CommandType.GAME_OVER, battleground, unitRepository));

  }
}
