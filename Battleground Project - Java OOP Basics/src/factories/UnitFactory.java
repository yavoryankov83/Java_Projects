package factories;

import enums.UnitType;
import exceptions.InvalidUnitTypeException;
import interfaces.Position;
import interfaces.Unit;
import models.PositionImpl;
import models.units.Carrier;
import models.units.Marine;

public final class UnitFactory {

  private UnitFactory() {
  }

  public static Unit createUnit(UnitType unitType, String name, int xPosition, int yPosition) {
    Position position;

    switch (unitType) {
      case MARINE:
        position = new PositionImpl(xPosition, yPosition);
        return new Marine(name, position, CombatHandlerFactory.createMarineCombatHandler());
      case CARRIER:
        position = new PositionImpl(xPosition, yPosition);
        return new Carrier(name, position, CombatHandlerFactory.createCarrierCombatHandler());
      default:
        return null;
    }
  }
}
