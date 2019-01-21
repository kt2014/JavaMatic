package Model;
/**
 * This class provides the definition for an ingredient
 * used to make a drink
 */
public class Ingredient {

    // Data members for class Ingredient
    private String name;
    private double cost;

    /**
     * Parameterized constructor to initialize data members
     * with the values of passed arguments
     * @param name is the name of the ingredient
     * @param cost is the cost per unit of the ingredient
     */
    public Ingredient(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    /**
     * Method that returns the name of the ingredient
     */
    public String getName() {
        return name;
    }

    /**
     * Method that returns the cost of the ingredient
     */
    public double getCost() {
        return cost;
    }
}