package models.units;

import interfaces.CombatHandler;
import interfaces.Position;

public class Marine extends BaseUnit {

  private static final int RANGE = 1;

  public Marine(String name,
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
