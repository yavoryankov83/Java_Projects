package factories;

import commands.*;
import enums.CommandType;
import interfaces.Battleground;
import interfaces.Command;
import interfaces.Repository;
import interfaces.Unit;

public final class CommandFactory {

  private CommandFactory() {
  }

  public static Command createCommand(CommandType commandType, Battleground battleground, Repository<Unit> unitRepository) {
    switch (commandType) {
      case MOVE:
        return new MoveCommand(battleground, unitRepository);
      case SPAWN:
        return new SpawnCommand(battleground, unitRepository);
      case STATUS:
        return new StatusCommand(unitRepository);
      case FIGHT:
        return new FightCommand(battleground, unitRepository);
      case GAME_OVER:
        return new GameOverCommand();
      default:
        return null;
    }
  }
}
