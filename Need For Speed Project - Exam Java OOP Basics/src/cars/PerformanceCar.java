package cars;

import java.util.ArrayList;
import java.util.Collection;

public class PerformanceCar extends BaseCar {

  private Collection<String> addOns;

  public PerformanceCar(String brand,
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

    //initialized addOns
    this.addOns = new ArrayList<>();
  }

  @Override
  public void setHorsepower(int horsepower) {
    super.setHorsepower(horsepower + horsepower / 2);
  }

  @Override
  public void setSuspension(int suspension) {
    super.setSuspension(suspension - suspension / 4);
  }

  @Override
  public void tune(Object... params) {
    String addOn = (String) params[0];
    this.addOns.add(addOn);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    String addOns = this.addOns.size() == 0 ? "None" : String.join(", ", this.addOns);

    builder
            .append(super.toString())
            .append(String.format("Add-ons: %s", addOns));

    return builder.toString();
  }
}
