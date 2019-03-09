package cars;

import enums.CarType;
import contracts.Car;

public abstract class BaseCar implements Car {

  private String brand;
  private String model;
  private int yearOfProduction;
  protected int horsepower;
  private int acceleration;
  protected int suspension;
  private int durability;
  private CarType carType;

  protected BaseCar(String brand,
                    String model,
                    int yearOfProduction,
                    int horsepower,
                    int acceleration,
                    int suspension,
                    int durability) {
    this.brand = brand;
    this.model = model;
    this.yearOfProduction = yearOfProduction;
    setHorsepower(horsepower);
    this.acceleration = acceleration;
    setSuspension(suspension);
    this.durability = durability;
  }

  @Override
  public void setHorsepower(int horsepower) {
    this.horsepower = horsepower;
  }

  @Override
  public void setSuspension(int suspension) {
    this.suspension = suspension;
  }

  @Override
  public String getBrand() {
    return this.brand;
  }

  @Override
  public String getModel() {
    return this.model;
  }

  @Override
  public int getOverallPerformancePoints() {
    return (this.horsepower / this.acceleration) + (this.suspension + this.durability);
  }

  @Override
  public int getEnginePerformancePoints() {
    return (this.horsepower / this.acceleration);
  }

  @Override
  public int getSuspensionPerformancePoints() {
    return this.suspension + this.durability;
  }

  @Override
  public void increaseHorsepower(int tuneIndex) {
    this.horsepower += tuneIndex;
  }

  @Override
  public void increaseSuspension(int tuneIndex) {
    this.suspension += tuneIndex;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder
            .append(String.format("%s %s %d", this.brand, this.model, this.yearOfProduction))
            .append(System.lineSeparator())
            .append(String.format("%d HP, 100 m/h in %s s", this.horsepower, this.acceleration))
            .append(System.lineSeparator())
            .append(String.format("%d Suspension force, %d Durability", this.suspension, this.durability))
            .append(System.lineSeparator());

    return builder.toString();
  }
}
