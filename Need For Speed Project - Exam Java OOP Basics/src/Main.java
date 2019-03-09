import core.CarManagerImpl;
import engines.Engine;
import contracts.CarManager;
import contracts.InputReader;
import contracts.OutputWriter;
import io.ConsoleInputReader;
import io.ConsoleOutputWriter;
import utilities.InputParser;

import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    InputReader inputReader = new ConsoleInputReader();
    OutputWriter outputWriter = new ConsoleOutputWriter();
    InputParser inputParser = new InputParser();
    CarManager carManager = new CarManagerImpl();
    Engine engine = new Engine(inputReader, outputWriter, inputParser, carManager);

    engine.run();
  }
}
