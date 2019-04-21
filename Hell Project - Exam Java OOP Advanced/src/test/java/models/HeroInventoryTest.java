package models;

import interfaces.Inventory;
import interfaces.Item;
import interfaces.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HeroInventoryTest {

  private static final Integer VALUE = Integer.MAX_VALUE;
  private static final String STRENGTH_MESSAGE = "Strength is not correct";
  private static final String AGILITY_MESSAGE = "Agility is not correct";
  private static final String INTELLIGENCE_MESSAGE = "Intelligence is not correct";
  private static final String HIT_POINTS_MESSAGE = "HitPoints is not correct";
  private static final String DAMAGE_MESSAGE = "Damage is not correct";

  private Inventory inventory;

  @Before
  public void setUp() throws Exception {
    this.inventory = new HeroInventory();
  }

  private Item createCommonItemMock() {
    Item commonItem = Mockito.mock(Item.class);

    Mockito.when(commonItem.getStrengthBonus()).thenReturn(VALUE);
    Mockito.when(commonItem.getAgilityBonus()).thenReturn(VALUE);
    Mockito.when(commonItem.getIntelligenceBonus()).thenReturn(VALUE);
    Mockito.when(commonItem.getHitPointsBonus()).thenReturn(VALUE);
    Mockito.when(commonItem.getDamageBonus()).thenReturn(VALUE);

    return commonItem;
  }

  private void seedCommonItems() {
    Item commonItemMock_1 = this.createCommonItemMock();
    Mockito.when(commonItemMock_1.getName()).thenReturn("commonItemMock_1");
    Item commonItemMock_2 = this.createCommonItemMock();
    Mockito.when(commonItemMock_2.getName()).thenReturn("commonItemMock_2");
    Item commonItemMock_3 = this.createCommonItemMock();
    Mockito.when(commonItemMock_3.getName()).thenReturn("commonItemMock_3");

    this.inventory.addCommonItem(commonItemMock_1);
    this.inventory.addCommonItem(commonItemMock_2);
    this.inventory.addCommonItem(commonItemMock_3);
  }

  private void seedRecipeItem() {
    Recipe recipeMock = Mockito.mock(Recipe.class);
    List<String> requiredItems = new ArrayList<>();
    requiredItems.add("commonItemMock_1");
    requiredItems.add("commonItemMock_2");
    requiredItems.add("commonItemMock_4");

    Mockito.when(recipeMock.getRequiredItems()).thenReturn(requiredItems);
    this.inventory.addRecipeItem(recipeMock);
  }

  @Test
  public void getTotalStrengthBonus() {
    this.seedCommonItems();

    long actualValue = this.inventory.getTotalStrengthBonus();
    long expectedValue = 3L * VALUE;

    Assert.assertEquals(STRENGTH_MESSAGE, expectedValue, actualValue);
  }

  @Test
  public void getTotalAgilityBonus() {
    this.seedCommonItems();

    long actualValue = this.inventory.getTotalAgilityBonus();
    long expectedValue = 3l * VALUE;

    Assert.assertEquals(AGILITY_MESSAGE, expectedValue, actualValue);
  }

  @Test
  public void getTotalIntelligenceBonus() {
    this.seedCommonItems();

    long actualValue = this.inventory.getTotalIntelligenceBonus();
    long expectedValue = 3l * VALUE;

    Assert.assertEquals(INTELLIGENCE_MESSAGE, expectedValue, actualValue);
  }

  @Test
  public void getTotalHitPointsBonus() {
    this.seedCommonItems();

    long actualValue = this.inventory.getTotalHitPointsBonus();
    long expectedValue = 3l * VALUE;

    Assert.assertEquals(HIT_POINTS_MESSAGE, expectedValue, actualValue);
  }

  @Test
  public void getTotalDamageBonus() {
    this.seedCommonItems();

    long actualValue = this.inventory.getTotalDamageBonus();
    long expectedValue = 3l * VALUE;

    Assert.assertEquals(DAMAGE_MESSAGE, expectedValue, actualValue);
  }

  @Test
  public void addCommonItem() {
    this.seedCommonItems();
    this.seedRecipeItem();
    Item commonItemMock_4 = Mockito.mock(Item.class);

    Mockito.when(commonItemMock_4.getName()).thenReturn("commonItemMock_4");

    this.inventory.addCommonItem(commonItemMock_4);
    int actualCountOfCommonItems = 0;

    try {
      Field commonItemsField = this.inventory.getClass().getDeclaredField("commonItems");
      commonItemsField.setAccessible(true);
      Map<String, Recipe> commonItemsMap = (Map<String, Recipe>) commonItemsField.get(this.inventory);

      actualCountOfCommonItems = commonItemsMap.size();
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }

    int expectedCountOfCommonItems = 2;

    Assert.assertEquals(expectedCountOfCommonItems, actualCountOfCommonItems);
  }

  @Test
  public void addRecipeItem() {
    Recipe recipeMock = Mockito.mock(Recipe.class);

    this.inventory.addRecipeItem(recipeMock);
    int actualCountOfRecipes = 0;

    try {
      Field recipesField = this.inventory.getClass().getDeclaredField("recipeItems");
      recipesField.setAccessible(true);
      Map<String, Recipe> recipeMap = (Map<String, Recipe>) recipesField.get(this.inventory);

      actualCountOfRecipes = recipeMap.size();
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }

    int expectedCountOfRecipes = 1;

    Assert.assertEquals(expectedCountOfRecipes, actualCountOfRecipes);
  }
}