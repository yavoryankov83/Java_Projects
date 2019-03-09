package garage;

import contracts.Car;

import java.util.ArrayList;
import java.util.Collection;

public class Garage {

  private Collection<Car> parkedCars;

  public Garage() {
    this.parkedCars = new ArrayList<>();
  }

  public boolean isParked(Car car) {
    return this.parkedCars.contains(car);
  }

  public void parkCar(Car car) {
    this.parkedCars.add(car);
  }

  public void unparkCar(Car car) {
    this.parkedCars.remove(car);
  }

  public void tuneCars(int tuneIndex, String addOn) {
    this.parkedCars.forEach(car -> {
      car.increaseHorsepower(tuneIndex);
      car.increaseSuspension(tuneIndex / 2);

      switch (car.getClass().getSimpleName()) {
        case "PerformanceCar":
          car.tune(addOn);
          break;

        case "ShowCar":
          car.tune(tuneIndex);
          break;
      }
    });
  }
}
