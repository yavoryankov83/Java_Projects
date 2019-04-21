package interfaces;

import exceptions.InvalidPositionException;
import models.units.BaseUnit;

import java.util.List;

public interface Battleground {

  List<Unit> getUnitsInRange(int xPosition, int yPosition, int range);

  void add(Unit unit) throws InvalidPositionException;

  void remove(Unit unit) throws InvalidPositionException;

  void move(Unit unit, int xPosition, int yPosition) throws InvalidPositionException;
}
