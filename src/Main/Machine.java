package Main;

import Model.Drink;
import Model.Ingredient;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Machine describes the JavaMatic coffee dispensing machine
 */
public class Machine {

    private HashMap<String, Integer> ingredientInventory;
    private HashMap<String, Double> ingredientCost;

    private List<Ingredient> ingredients;
    private List<Drink> drinks;

    private final int MAX_QTY = 10;

    /**
     * Default constructor
     */
    public Machine() {
        this.ingredientInventory = new HashMap<>();
        this.ingredientCost = new HashMap<>();
        this.ingredients = new ArrayList<>();
        this.drinks = new ArrayList<>();
    }

    /**
     * Method for adding new ingredients to the coffee dispensing machine
     */
    public Ingredient addIngredient(String name, double unitCost) {
        ingredientInventory.put(name, MAX_QTY);
        ingredientCost.put(name, unitCost);

        Ingredient newIngredient = new Ingredient(name, unitCost);
        ingredients.add(newIngredient);

        // Sort the list of ingredients in alphabetical order
        ingredients.sort((ingredient1, ingredient2) -> {
            if(ingredient1.getName().compareTo(ingredient2.getName()) > 0) {
                return 1;
            }
            else {
                return -1;
            }
        });

        return newIngredient;
    }

    /**
     * Add new drink
     */
    public void addDrink(Drink drink) {
        drinks.add(drink);

        // Sort the list of drinks in alphabetical order
        drinks.sort((drink1, drink2) -> {
            if(drink1.getName().compareTo(drink2.getName()) > 0) {
                return 1;
            }
            else {
                return -1;
            }
        });
    }

    /**
     * Return number of drink options available in the machine
     */
    public int getNumDrinks() {
        return drinks.size();
    }

    /**
     * Return the drink element at the specified position in the list
     */
    public Drink dispenseDrink(int index) {
        return drinks.get(index - 1);
    }

    /**
     * A method to restock the inventory
     */
    public void restockInventory() {
        for(int i = 0; i < ingredients.size(); ++i) {
            ingredientInventory.put(ingredients.get(i).getName(), MAX_QTY);
        }
    }

    /**
     * Check if the machine has sufficient numbe of ingredients to make the chosen drink.
     * If units needed is greater than what is currently available, return false
     * Otherwise, return true
     */
    public void checkInStock(Drink drink) {
        for(Ingredient ingredient : drink.getIngredients()) {
            int unitCost = drink.getIngredientAmount(ingredient);
            if(unitCost > ingredientInventory.get(ingredient.getName())) {
                drink.setInStock(false);
                return;
            }
        }

        drink.setInStock(true);
        return;
    }

    /**
     * Remove the ingredients amounts used to make the drink
     * and update the inventory with the new quantity
     */
    public void updateInventory(Drink drink) {
        for(Ingredient ingredient : drink.getIngredients()) {
            int unitsUsed = drink.getIngredientAmount(ingredient);
            int newQty= ingredientInventory.get(ingredient.getName()) - unitsUsed;
            ingredientInventory.put(ingredient.getName(), newQty);
        }
    }

    /**
     * Print the current inventory with ingredients names and amounts
     */
    public void printInventory() {
        System.out.println("Inventory:");
        for(Ingredient ing : ingredients) {
            System.out.println(ing.getName()+','+ingredientInventory.get(ing.getName()));
        }
    }

    /**
     * Output a list of drink options and whether or not there is
     * sufficient amount of ingredients to make those drinks
     */
    public void displayMenu() {
        System.out.println("Menu:");

        for(int i = 0; i < drinks.size(); ++i) {
            checkInStock(drinks.get(i));
            System.out.println(Integer.toString(i+1)+','+drinks.get(i).getName()+",$"
                    +drinks.get(i).getTotalCost()+','+drinks.get(i).getInStock());
        }
    }
}