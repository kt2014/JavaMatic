package Main;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.Drink;
import Model.Ingredient;

class MachineTest {

    private static Machine coffeeMachine;

    @BeforeEach
    void setUp() {
        coffeeMachine = new Machine();

        /**
         * Add the ingredients to coffeeMachine object
         */
        Ingredient espresso = coffeeMachine.addIngredient("Espresso", 1.10);
        Ingredient decafCoffee = coffeeMachine.addIngredient("Decaf Coffee", 0.75);
        Ingredient sugar = coffeeMachine.addIngredient("Sugar", 0.25);
        Ingredient cream = coffeeMachine.addIngredient("Cream", 0.25);
        Ingredient steamedMilk = coffeeMachine.addIngredient("Steamed Milk", 0.35);

        /**
         * Add the drinks to coffeeMachine object
         */
        Drink caffeAmericano = new Drink("Caffe Americano");
        caffeAmericano.addIngredientToDrink(espresso, 3);
        coffeeMachine.addDrink(caffeAmericano);

        Drink caffeLatte = new Drink("Caffe Latte");
        caffeLatte.addIngredientToDrink(espresso, 2);
        caffeLatte.addIngredientToDrink(steamedMilk, 1);
        coffeeMachine.addDrink(caffeLatte);

        Drink decafCoffeeDrink = new Drink("Decaf Coffee");
        decafCoffeeDrink.addIngredientToDrink(decafCoffee, 3);
        decafCoffeeDrink.addIngredientToDrink(sugar, 1);
        decafCoffeeDrink.addIngredientToDrink(cream, 1);
        coffeeMachine.addDrink(decafCoffeeDrink);
    }

    /**
     * Test whether or not it returns the correct number of drinks currently available
     */
    @Test
    void testNumberOfDrinks() {
        assert(coffeeMachine.getNumDrinks() == 3);
    }

    /**
     * Test whether coffeeMachine returns the drink
     * corresponding to the selected number from user input
     */
    @Test
    void testDrinkChoice() {
        Drink index1 = coffeeMachine.dispenseDrink(1);
        assertEquals(index1.getName(), "Caffe Americano");

        Drink index2 = coffeeMachine.dispenseDrink(2);
        assertEquals(index2.getName(), "Caffe Latte");

        Drink index3 = coffeeMachine.dispenseDrink(3);
        assertEquals(index3.getName(), "Decaf Coffee");

    }

    /**
     * Tests whether the coffeeMachine correctly returns the drinks inventory
     */
    @Test
    void testDrinkInStock() {
        Drink choice = coffeeMachine.dispenseDrink(2);
        coffeeMachine.updateInventory(choice);
        coffeeMachine.checkInStock(choice);
        assert(choice.getInStock() == true);

        coffeeMachine.updateInventory(choice);
        coffeeMachine.checkInStock(choice);
        assert(choice.getInStock() == true);

        coffeeMachine.updateInventory(choice);
        coffeeMachine.checkInStock(choice);
        assert(choice.getInStock() == true);

        coffeeMachine.updateInventory(choice);
        coffeeMachine.checkInStock(choice);
        assert(choice.getInStock() == true);


        coffeeMachine.updateInventory(choice);
        coffeeMachine.checkInStock(choice);

        // Make sure this time the drink is not available for purchase
        choice = coffeeMachine.dispenseDrink(2);
        assert(choice.getInStock() == false);
    }

    /**
     * Test that inventory is correctly updated after purchase
     */
    @Test
    void testRestock() {
        // Select drinks to purchase
        Drink choice = coffeeMachine.dispenseDrink(2);
        coffeeMachine.updateInventory(choice);
        coffeeMachine.updateInventory(choice);
        coffeeMachine.updateInventory(choice);
        coffeeMachine.updateInventory(choice);
        coffeeMachine.updateInventory(choice);
        coffeeMachine.checkInStock(choice);
        assert(choice.getInStock() == false);

        // Restock inventory
        coffeeMachine.restockInventory();
        // Check if choice is in stock
        coffeeMachine.checkInStock(choice);
        assert(choice.getInStock() == true);
    }
}