package factories;

import comparators.UnitComparatorByHealth;
import comparators.UnitComparatorByName;
import interfaces.Unit;

import java.util.Comparator;

public final class UnitComparatotFactory {

  private UnitComparatotFactory() {
  }

  public static Comparator<Unit> createComparatorByHealth() {
    return new UnitComparatorByHealth();
  }

  public static Comparator<Unit> createComparatorByName() {
    return new UnitComparatorByName();
  }
}