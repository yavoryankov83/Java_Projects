package engines;

import contracts.CarManager;
import contracts.InputReader;
import contracts.OutputWriter;
import utilities.InputParser;

import java.io.IOException;
import java.util.List;

public class Engine {

  private static final String TERMINATED_COMMAND = "Cops Are Here";

  private InputReader inputReader;
  private OutputWriter outputWriter;
  private InputParser inputParser;
  private CarManager carManager;

  public Engine(InputReader inputReader, OutputWriter outputWriter, InputParser inputParser, CarManager carManager) {
    this.inputReader = inputReader;
    this.outputWriter = outputWriter;
    this.inputParser = inputParser;
    this.carManager = carManager;
  }

  public void run() throws IOException {
    String input;

    while (true) {
      input = this.inputReader.readLine();
      List<String> commandParams = this.inputParser.parseInput(input);

      this.dispatchCommand(commandParams);

      if (TERMINATED_COMMAND.equals(input)) {
        break;
      }
    }
  }

  private void dispatchCommand(List<String> commandParams) {
    String command = commandParams.get(0);

    switch (command) {
      case "register":
        int carId = Integer.parseInt(commandParams.get(1));
        String carType = commandParams.get(2);
        String brand = commandParams.get(3);
        String model = commandParams.get(4);
        int yearOfProduction = Integer.parseInt(commandParams.get(5));
        int horsepower = Integer.parseInt(commandParams.get(6));
        int acceleration = Integer.parseInt(commandParams.get(7));
        int suspension = Integer.parseInt(commandParams.get(8));
        int durability = Integer.parseInt(commandParams.get(9));

        this.carManager.register(carId, carType, brand, model, yearOfProduction, horsepower, acceleration, suspension, durability);
        break;

      case "check":
        carId = Integer.parseInt(commandParams.get(1));

        this.outputWriter.writeLine(this.carManager.check(carId));
        break;

      case "open":
        carId = Integer.parseInt(commandParams.get(1));
        String raceType = commandParams.get(2);
        int lenght = Integer.parseInt(commandParams.get(3));
        String routeName = commandParams.get(4);
        int prizePool = Integer.parseInt(commandParams.get(5));

        this.carManager.open(carId, raceType, lenght, routeName, prizePool);
        break;

      case "participate":
        carId = Integer.parseInt(commandParams.get(1));
        int raceId = Integer.parseInt(commandParams.get(2));

        this.carManager.participate(carId, raceId);
        break;

      case "park":
        carId = Integer.parseInt(commandParams.get(1));

        this.carManager.park(carId);
        break;

      case "unpark":
        carId = Integer.parseInt(commandParams.get(1));

        this.carManager.unpark(carId);
        break;

      case "tune":
        int tuneIndex = Integer.parseInt(commandParams.get(1));
        String addOns = commandParams.get(2);

        this.carManager.tune(tuneIndex, addOns);
        break;

      case "start":
        raceId = Integer.parseInt(commandParams.get(1));

        this.outputWriter.writeLine(this.carManager.start(raceId));
        break;
    }
  }
}
