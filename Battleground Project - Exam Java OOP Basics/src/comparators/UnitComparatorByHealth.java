package comparators;

import interfaces.Unit;

import java.util.Comparator;

public class UnitComparatorByHealth implements Comparator<Unit> {

  @Override
  public int compare(Unit unit_1, Unit unit_2) {
    return unit_1.getHealthPoints() - unit_2.getHealthPoints();
  }
}
