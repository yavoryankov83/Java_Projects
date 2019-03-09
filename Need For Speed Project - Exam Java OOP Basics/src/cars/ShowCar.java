package cars;

import enums.CarType;

public class ShowCar extends BaseCar {

  private int stars;

  public ShowCar(String brand,
                 String model,
                 int yearOfProduction,
                 int horsepower,
                 int acceleration,
                 int suspension,
                 int durability) {
    super(brand,
            model,
            yearOfProduction,
            horsepower,
            acceleration,
            suspension,
            durability);
    this.stars = 0;
  }

  @Override
  public void tune(Object... params) {
    int tuneIndex = (int) params[0];
    this.stars += tuneIndex;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder
            .append(super.toString())
            .append(String.format("%d *", this.stars));

    return builder.toString();
  }
}
