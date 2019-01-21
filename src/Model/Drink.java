package Model;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * This class provides definition of a drink that can
 * be dispensed by the JavaMatic machine
 */
public class Drink {
    private String name;
    private Map<Ingredient, Integer> ingredients;
    private double cost;
    private boolean inStock;

    /**
     *  Parameterized constructor to initialize data members
     *  with the values of passed arguments
     * @param name is the name of the drink
     */
    public Drink(String name) {
        this.name = name;
        this.ingredients = new HashMap<>();
        this.cost = 0;
    }

    /**
     * Returns the name of the Drink object.
     */
    public String getName() {
        return name;
    }

    /**
     * Add ingredient to the drink, compute total cost
     */
    public void addIngredientToDrink(Ingredient ingredient, int numUnits) {
        ingredients.put(ingredient, numUnits);
        cost += ingredient.getCost() * numUnits;
    }

    /**
     * Whether the drink is currently in stock
     */
    public boolean getInStock() {
        return inStock;
    }

    /**
     * Whether the drink is in stock and can be dispensed
     */
    public void setInStock(boolean isInStock) {
        this.inStock = isInStock;
    }

    /**
     * Returns the total cost of the drink
     * as a string in 2 decimal places
     */
    public String getTotalCost() {
        return String.format("%.2f", cost);
    }

    /**
     * Returns a set of Ingredients that have been used to make the drink
     */
    public Set<Ingredient> getIngredients() {
        return ingredients.keySet();
    }

    /**
     * Returns number of units of an ingredient that are needed to dispense the drink
     */
    public int getIngredientAmount(Ingredient ingredient) {
        return ingredients.get(ingredient);
    }
}