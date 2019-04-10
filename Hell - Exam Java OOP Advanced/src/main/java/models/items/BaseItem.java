package models.items;

import interfaces.Item;

public abstract class BaseItem implements Item {

  private String name;
  private int strengthBonus;
  private int agilityBonus;
  private int intelligenceBonus;
  private int hitPointsBonus;
  private int damageBonus;

  protected BaseItem(String name, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitPointsBonus, int damageBonus) {
    this.name = name;
    this.strengthBonus = strengthBonus;
    this.agilityBonus = agilityBonus;
    this.intelligenceBonus = intelligenceBonus;
    this.hitPointsBonus = hitPointsBonus;
    this.damageBonus = damageBonus;
  }

  public String getName() {
    return this.name;
  }

  public int getStrengthBonus() {
    return this.strengthBonus;
  }

  public int getAgilityBonus() {
    return this.agilityBonus;
  }

  public int getIntelligenceBonus() {
    return this.intelligenceBonus;
  }

  public int getHitPointsBonus() {
    return this.hitPointsBonus;
  }

  public int getDamageBonus() {
    return this.damageBonus;
  }

  @Override
  public String toString() {
    StringBuilder item = new StringBuilder();

    item
            .append(String.format("###Item: %s", this.getName()))
            .append(System.lineSeparator())
            .append(String.format("###+%d Strength", this.getStrengthBonus()))
            .append(System.lineSeparator())
            .append(String.format("###+%d Agility", this.getAgilityBonus()))
            .append(System.lineSeparator())
            .append(String.format("###+%d Intelligence", this.getIntelligenceBonus()))
            .append(System.lineSeparator())
            .append(String.format("###+%d HitPoints", this.getHitPointsBonus()))
            .append(System.lineSeparator())
            .append(String.format("###+%d Damage", this.getDamageBonus()));

    return item.toString();
  }
}
