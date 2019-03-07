package handlers;

import enums.SpellType;
import exceptionMessages.ExceptionMessages;
import exceptions.NotEnoughtEnergyForSpellException;
import factories.SpellFactory;
import interfaces.Spell;
import interfaces.Unit;

public class MarineCombatHandler extends BaseCombatHandler {

  private static final int HALF_MARINE_HEALTH = 25;

  private int getSpellEnergyCost() {
    Unit unit = super.getUnit();
    int energyCost = unit.getAttackPoints();

    if (unit.getHealthPoints() < HALF_MARINE_HEALTH) {
      return energyCost * 2;
    }

    return energyCost;
  }

  @Override
  public Spell generateAttack() throws NotEnoughtEnergyForSpellException {
    Spell spell = SpellFactory.createSpell(SpellType.BULLET_RAIN, this.getSpellEnergyCost());

    Unit unit = super.getUnit();

    if (unit.getEnergyPoints() <= spell.getEnergyCost()) {
      throw new NotEnoughtEnergyForSpellException(ExceptionMessages.NOT_ENOUGH_ENERGY_EXCEPTION_MESSAGE);
    }

    unit.setEnergyPoints(unit.getEnergyPoints() - spell.getEnergyCost());

    return spell;
  }
}
