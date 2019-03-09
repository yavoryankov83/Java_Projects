package contracts;

public interface Car {

  void setHorsepower(int horsepower);

  void setSuspension(int suspension);

  void increaseHorsepower(int tuneIndex);

  void increaseSuspension(int tuneIndex);

  void tune(Object... params);

  int getEnginePerformancePoints();

  int getSuspensionPerformancePoints();

  int getOverallPerformancePoints();

  String getBrand();

  String getModel();
}
