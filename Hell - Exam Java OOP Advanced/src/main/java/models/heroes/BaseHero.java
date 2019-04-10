package models.heroes;

import models.ItemCollection;
import interfaces.Hero;
import interfaces.Inventory;
import interfaces.Item;
import interfaces.Recipe;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class BaseHero implements Hero {

  private String name;
  private int strength;
  private int agility;
  private int intelligence;
  private int hitPoints;
  private int damage;
  private Inventory inventory;

  protected BaseHero(String name, int strength, int agility, int intelligence, int hitPoints, int damage, Inventory inventory) {
    this.name = name;
    this.strength = strength;
    this.agility = agility;
    this.intelligence = intelligence;
    this.hitPoints = hitPoints;
    this.damage = damage;
    this.inventory = inventory;
  }

  public String getName() {
    return this.name;
  }

  public long getStrength() {
    return this.strength + this.inventory.getTotalStrengthBonus();
  }

  public long getAgility() {
    return this.agility + this.inventory.getTotalAgilityBonus();
  }

  public long getIntelligence() {
    return this.intelligence + this.inventory.getTotalIntelligenceBonus();
  }

  public long getHitPoints() {
    return this.hitPoints + this.inventory.getTotalHitPointsBonus();
  }

  public long getDamage() {
    return this.damage + this.inventory.getTotalDamageBonus();
  }

  @SuppressWarnings("uncheked")
  public Collection<Item> getItems() {
    Collection<Item> items = null;

    Field[] inventoryFilds = this.inventory
            .getClass()
            .getDeclaredFields();

    for (Field inventoryFild : inventoryFilds) {
      if (inventoryFild.isAnnotationPresent(ItemCollection.class)) {
        inventoryFild.setAccessible(true);
        Map<String, Item> itemMap = null;

        try {
          itemMap = (Map<String, Item>) inventoryFild.get(this.inventory);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }


        items = itemMap.values();
      }
    }
    return items;
  }

  public void addItem(Item item) {
    this.inventory.addCommonItem(item);
  }

  public void addRecipe(Recipe recipe) {
    this.inventory.addRecipeItem(recipe);
  }

  @Override
  public String toString() {
    StringBuilder hero = new StringBuilder();

    String items = this.getItems().size() == 0
            ? " None"
            : System.lineSeparator() + this.getItems()
            .stream()
            .map(Item::toString)
            .collect(Collectors.joining(System.lineSeparator()));

            hero
                    .append(String.format("Hero: %s, Class: %s", this.getName(), this.getClass().getSimpleName()))
                    .append(System.lineSeparator())
                    .append(String.format("Hitpoints: %d, Damage: %d", this.getHitPoints(), this.getDamage()))
                    .append(System.lineSeparator())
                    .append(String.format("Strength: %d", this.getStrength()))
                    .append(System.lineSeparator())
                    .append(String.format("Agility: %d", this.getAgility()))
                    .append(System.lineSeparator())
                    .append(String.format("Intelligence: %d", this.getIntelligence()))
                    .append(System.lineSeparator())
                    .append(String.format("Items:%s", items));

    return hero.toString();
  }
}
