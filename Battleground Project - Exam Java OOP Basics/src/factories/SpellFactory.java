package factories;

import enums.SpellType;
import interfaces.Spell;
import models.spells.BulletRain;
import models.spells.RageShoot;

public final class SpellFactory {

  private SpellFactory() {
  }

  public static Spell createSpell(SpellType spellType, int energyCost) {
    switch (spellType) {
      case BULLET_RAIN:
        return new BulletRain(energyCost);
      case RAGE_SHOOT:
        return new RageShoot(energyCost);
      default:
        return null;
    }
  }
}
