package handlers;

import enums.SpellType;
import exceptionMessages.ExceptionMessages;
import exceptions.NotEnoughtEnergyForSpellException;
import factories.SpellFactory;
import interfaces.Spell;
import interfaces.Unit;

public class CarrierCombatHandler extends BaseCombatHandler {

  private int spellCount;

  @Override
  public Spell generateAttack() throws NotEnoughtEnergyForSpellException {
    Spell spell = SpellFactory.createSpell(SpellType.BULLET_RAIN, super.getUnit().getAttackPoints());

    Unit unit = super.getUnit();

    this.spellCount++;

    if (this.spellCount % 3 == 0) {
      return spell;
    } else {
      if (unit.getEnergyPoints() <= spell.getEnergyCost()) {
        throw new NotEnoughtEnergyForSpellException(ExceptionMessages.NOT_ENOUGH_ENERGY_EXCEPTION_MESSAGE);
      }
    }

    unit.setEnergyPoints(unit.getEnergyPoints() - spell.getEnergyCost());

    return spell;
  }
}
