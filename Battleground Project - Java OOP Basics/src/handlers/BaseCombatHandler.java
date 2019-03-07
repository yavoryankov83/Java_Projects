package handlers;

import factories.UnitComparatotFactory;
import interfaces.CombatHandler;
import interfaces.Unit;

import java.util.List;

public abstract class BaseCombatHandler implements CombatHandler {

  private static final int CLOSEST_ENEMY = 0;

  private Unit unit;

  protected BaseCombatHandler() {
  }

  protected Unit getUnit() {
    return this.unit;
  }

  @Override
  public void setUnit(Unit unit) {
    this.unit = unit;
  }

  public Unit pickNextTarget(List<Unit> targetCandidates) {
    targetCandidates.sort(UnitComparatotFactory.createComparatorByName()
            .thenComparing(UnitComparatotFactory.createComparatorByHealth()));

    return targetCandidates.get(this.CLOSEST_ENEMY);
  }
}
