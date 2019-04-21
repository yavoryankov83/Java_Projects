package models.spells;

import interfaces.Spell;

public abstract class BaseSpell implements Spell {

  private static final int DAMAGE = 15;

  private int damage;
  private int energyCost;

  protected BaseSpell(int energyCost) {
    this.damage = DAMAGE;
    this.energyCost = energyCost;
  }

  public int getDamage() {
    return this.damage;
  }

  public int getEnergyCost() {
    return this.energyCost;
  }
}
