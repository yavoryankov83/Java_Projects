import core.GameEngine;
import dispachers.CommandDispatcher;
import interfaces.*;
import io.ConsoleInputReader;
import io.ConsoleOutputWriter;
import models.BattlegroundImpl;
import repository.UnitRepository;

import java.io.IOException;

public class Main {

  private static final int ROWS = 5;
  private static final int COLS = 5;

  public static void main(String[] args) throws IOException {

    InputReader consoleReader = new ConsoleInputReader();
    OutputWriter consoleWriter = new ConsoleOutputWriter();
    Battleground battleground = new BattlegroundImpl(ROWS, COLS);
    Repository<Unit> unitRepository = new UnitRepository();
    CommandDispatcher commandDispatcher = new CommandDispatcher(battleground, unitRepository);

    Engine gameEngine = new GameEngine(consoleReader, consoleWriter, commandDispatcher);
    gameEngine.start();
  }
}
