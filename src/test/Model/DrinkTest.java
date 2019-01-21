package test.Model;

import Model.Drink;
import Model.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DrinkTest {
    private static Drink drink;

    @BeforeEach
    /**
     * Create a new Drink object before each test case
     */
    public void setUp() {
        drink = new Drink("Cappuccino");
        drink.setInStock(true);
    }

    /**
     * Tests whether the drink name is being properly set.
     */
    @Test
    public void testName() {
        assertEquals(drink.getName(), "Cappuccino");
    }

    /**
     * Tests whether the drink is marked either in stock or not.
     */
    @Test
    public void testInStock() {
        assertEquals(drink.getInStock(), true);
    }

    /**
     * Tests that the drink correctly calculates its cost.
     */
    @Test
    public void testCost() {
        Ingredient ingredient1 = new Ingredient("ingredient1", 0.55);
        Ingredient ingredient2 = new Ingredient("ingredient2", 4.00);
        Ingredient ingredient3 = new Ingredient("ingredient3", 2.20);

        drink.addIngredientToDrink(ingredient1, 2);
        drink.addIngredientToDrink(ingredient2, 3);
        drink.addIngredientToDrink(ingredient3, 6);

        assertEquals(drink.getTotalCost(), "26.30");
    }

    /**
     * Tests the drink correctly returns the number of units needed for an ingredient.
     */
    @Test
    public void testIngredientAmount() {
        Ingredient ingredient = new Ingredient("ingredient", 2.85);
        drink.addIngredientToDrink(ingredient, 12);

        assertEquals(drink.getIngredientAmount(ingredient), 12);
    }
}