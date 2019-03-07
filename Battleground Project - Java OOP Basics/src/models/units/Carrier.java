package models.units;

import interfaces.CombatHandler;
import interfaces.Position;

public class Carrier extends BaseUnit {

  private static final int RANGE = 2;

  public Carrier(String name,
                 Position position,
                 CombatHandler combatHandler) {
    super(name,
            RANGE,
            position,
            HEALTH_POINTS,
            ENERGY_POINTS,
            ATTACK_POINTS,
            DEFENCE_POINTS,
            combatHandler);
  }
}
