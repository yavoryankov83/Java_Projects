package core;

import dispachers.CommandDispatcher;
import exceptions.BaseException;
import interfaces.Engine;
import interfaces.InputReader;
import interfaces.OutputWriter;

import java.io.IOException;

public class GameEngine implements Engine {

  private InputReader consoleReader;
  private OutputWriter consoleWriter;
  private CommandDispatcher commandDispatcher;
  private boolean isStarted;

  public GameEngine(InputReader consoleReader, OutputWriter consoleOutputWriter, CommandDispatcher commandDispatcher) {
    this.consoleWriter = consoleOutputWriter;
    this.consoleReader = consoleReader;
    this.commandDispatcher = commandDispatcher;
  }

  public void start() throws IOException {
    this.isStarted = true;

    while (this.isStarted) {
      try {
        String userInput = this.consoleReader.readLine();
        String[] args = userInput.split("\\s+");

        String result = this.commandDispatcher.dispatchCommand(args);

        this.consoleWriter.writeLine(result);

        if (result.equals("Game over!")) {
          this.isStarted = false;
        }
      } catch (BaseException | IOException e) {
        this.consoleWriter.writeLine(e.getMessage());
      }
    }
  }
}
