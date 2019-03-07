package factories;

import handlers.CarrierCombatHandler;
import handlers.MarineCombatHandler;
import interfaces.CombatHandler;

public final class CombatHandlerFactory {

  private CombatHandlerFactory() {
  }

  public static CombatHandler createCarrierCombatHandler() {
    return new CarrierCombatHandler();
  }

  public static CombatHandler createMarineCombatHandler() {
    return new MarineCombatHandler();
  }
}
