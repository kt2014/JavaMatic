package test.Model;

import Model.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientTest {
    private static Ingredient ingredient;

    /**
     * Create an Ingredient object before each test case
     */
    @BeforeEach
    void setUp() {
        ingredient = new Ingredient("Foamed Milk", 0.35);
    }

    /**
     * Tests whether the Ingredient name returns the right value
     */
    @Test
    void testName() {
        assertEquals(ingredient.getName(), "Foamed Milk");
    }

    /**
     * Tests whether the Ingredient cost returns the right value
     */
    @Test
    void testCost() {
        assert(ingredient.getCost() == 0.35);
    }
}