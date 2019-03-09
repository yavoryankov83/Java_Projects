package core;

import cars.PerformanceCar;
import cars.ShowCar;
import contracts.Car;
import contracts.CarManager;
import contracts.Race;
import enums.CarType;
import enums.RaceType;
import garage.Garage;
import races.CasualRace;
import races.DragRace;
import races.DriftRace;

import java.util.LinkedHashMap;
import java.util.Map;

public class CarManagerImpl implements CarManager {

  private Map<Integer, Car> cars;
  private Map<Integer, Race> races;
  private Garage garage;

  public CarManagerImpl() {
    this.cars = new LinkedHashMap<>();
    this.races = new LinkedHashMap<>();
    this.garage = new Garage();
  }

  @Override
  public void register(int id, String type, String brand, String model, int yearOfProduction, int horsepower, int acceleration, int suspension, int durability) {
    Car car = null;

    switch (CarType.valueOf(type.toUpperCase())) {
      case PERFORMANCE:
        car = new PerformanceCar(brand, model, yearOfProduction, horsepower, acceleration, suspension, durability);
        break;
      case SHOW:
        car = new ShowCar(brand, model, yearOfProduction, horsepower, acceleration, suspension, durability);
        break;
    }

    if (car != null) {
      cars.put(id, car);
    }
  }

  @Override
  public void open(int id, String raceType, int length, String routeName, int prizePool) {
    Race race = null;

    switch (RaceType.valueOf(raceType.toUpperCase())) {
      case DRAG:
        race = new DragRace(length, routeName, prizePool);
        break;

      case DRIFT:
        race = new DriftRace(length, routeName, prizePool);
        break;

      case CASUAL:
        race = new CasualRace(length, routeName, prizePool);
    }

    if (race != null) {
      this.races.put(id, race);
    }
  }

  @Override
  public void participate(int carId, int raceId) {
    Car car = cars.get(carId);
    Race race = races.get(raceId);

    if (this.garage.isParked(car)) {
      return;
    }

    race.addParticipant(car);
  }

  @Override
  public void park(int id) {
    Car car = this.cars.get(id);

    boolean hasCarInRace = this.races.values().stream().anyMatch(race -> race.hasCar(car));

    if (hasCarInRace) {
      return;
    }

    this.garage.parkCar(car);

  }

  @Override
  public void unpark(int id) {
    Car car = this.cars.get(id);

    this.garage.unparkCar(car);

  }

  @Override
  public void tune(int tuneIndex, String addOn) {
    this.garage.tuneCars(tuneIndex, addOn);
  }

  @Override
  public String check(int id) {
    return this.cars.get(id).toString();
  }

  @Override
  public String start(int id) {
    Race race = this.races.get(id);

    if (!race.hasParticipants()) {
      return "Cannot start the race with zero participants.";
    }

    String raceResult = race.toString();

    this.races.remove(id);

    return raceResult;
  }
}
