package interfaces;

import exceptions.NoUnitToRemoveException;

import java.util.Collection;
import java.util.List;

public interface Repository<T> {

  void save(T element);

  void remove(T element) throws NoUnitToRemoveException;

  Collection<T> findAll();

  T findByName(String name);
}
