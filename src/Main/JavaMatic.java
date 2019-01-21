package Main;
import Model.Drink;
import Model.Ingredient;

import java.util.Scanner;

/**
 * Class JavaMatic needed to create and run a machine object
 * coffeeMachine and load it with drinks and ingredients
 */
public class JavaMatic {

    /**
     * Main method
     */
    public static void main(String[] args) {
        // Create a new object machine
        Machine coffeeMachine = new Machine();

        // Load the machine with inventory
        createMachine(coffeeMachine);

        // Run the JavaMatic automatic coffee dispensing machine
        runMachine(coffeeMachine);
    }

    /**
     * Method handles inputting the drinks and ingredients for the
     * JavaMatic coffeeMachine
     */
    private static void createMachine(Machine coffeeMachine) {
        // define the ingredients and their cost per unit
        Ingredient coffee = coffeeMachine.addIngredient("Coffee", 0.75);
        Ingredient decafCoffee = coffeeMachine.addIngredient("Decaf Coffee", 0.75);
        Ingredient sugar = coffeeMachine.addIngredient("Sugar", 0.25);
        Ingredient cream = coffeeMachine.addIngredient("Cream", 0.25);
        Ingredient steamedMilk = coffeeMachine.addIngredient("Steamed Milk", 0.35);
        Ingredient foamedMilk = coffeeMachine.addIngredient("Foamed Milk", 0.35);
        Ingredient espresso = coffeeMachine.addIngredient("Espresso", 1.10);
        Ingredient cocoa = coffeeMachine.addIngredient("Cocoa", 0.90);
        Ingredient whippedCream = coffeeMachine.addIngredient("Whipped Cream", 1.00);

        // define the drinks and their ingredients
        Drink coffeeDrink = new Drink("Coffee");
        coffeeDrink.addIngredientToDrink(coffee, 3);
        coffeeDrink.addIngredientToDrink(sugar, 1);
        coffeeDrink.addIngredientToDrink(cream, 1);
        coffeeMachine.addDrink(coffeeDrink);

        Drink decafCoffeeDrink = new Drink("Decaf Coffee");
        decafCoffeeDrink.addIngredientToDrink(decafCoffee, 3);
        decafCoffeeDrink.addIngredientToDrink(sugar, 1);
        decafCoffeeDrink.addIngredientToDrink(cream, 1);
        coffeeMachine.addDrink(decafCoffeeDrink);

        Drink caffeLatte = new Drink("Caffe Latte");
        caffeLatte.addIngredientToDrink(espresso, 2);
        caffeLatte.addIngredientToDrink(steamedMilk, 1);
        coffeeMachine.addDrink(caffeLatte);

        Drink caffeAmericano = new Drink("Caffe Americano");
        caffeAmericano.addIngredientToDrink(espresso, 3);
        coffeeMachine.addDrink(caffeAmericano);

        Drink caffeMocha = new Drink("Caffe Mocha");
        caffeMocha.addIngredientToDrink(espresso, 1);
        caffeMocha.addIngredientToDrink(cocoa, 1);
        caffeMocha.addIngredientToDrink(steamedMilk, 1);
        caffeMocha.addIngredientToDrink(whippedCream, 1);
        coffeeMachine.addDrink(caffeMocha);

        Drink cappuccino = new Drink("Cappuccino");
        cappuccino.addIngredientToDrink(espresso, 2);
        cappuccino.addIngredientToDrink(steamedMilk, 1);
        cappuccino.addIngredientToDrink(foamedMilk, 1);
        coffeeMachine.addDrink(cappuccino);
    }

    /**
     * Method for handing user input and printing output
     * @param coffeeMachine is the Machine object with the drinks and ingredients
     */
    private static void runMachine(Machine coffeeMachine) {
        // construct Scanner object to read console input
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;
        boolean printCurrentState = true;

        while(keepRunning == true) {

            if(printCurrentState == true) {
                // upon start display the current inventory
                coffeeMachine.printInventory();

                // follow by a menu to allow user to select drink
                coffeeMachine.displayMenu();
            }

            // read user input, one command per line
            String input = scanner.nextLine();

            if(input.length() > 0) {
                if(input.toLowerCase().equals("r")) {
                    // restock the inventory
                    coffeeMachine.restockInventory();
                }
                else if(input.toLowerCase().equals("q")) {
                    // quit the application
                    keepRunning = false;
                }
                else {
                    try {
                        // convert to integer
                        int choice = Integer.parseInt(input);
                        // print current inventory and display the menu
                        printCurrentState = true;

                        // if user enters invalid command, display message
                        if(choice < 1 || choice > coffeeMachine.getNumDrinks()) {
                            System.out.println("Invalid Selection: " + input);
                        }

                        else {
                            Drink drinkChoice = coffeeMachine.dispenseDrink(choice);

                            // check whether there is a sufficient amount of ingredients to make the drink
                            if(drinkChoice.getInStock() == true) {
                                System.out.println("Dispensing: " + drinkChoice.getName());

                                // update the inventory
                                coffeeMachine.updateInventory(drinkChoice);
                            }

                            // if there is not a sufficient amount of ingredients available
                            // in order to make the drink, display a message to the user
                            else {
                                System.out.println("Out of stock: " + drinkChoice.getName());
                            }
                        }
                    }
                    // if the user enters an invalid command, display a message
                    catch(NumberFormatException exception) {
                        System.out.println("Invalid Selection: " + input);
                    }
                }
            }
            else {
                printCurrentState = false;
            }
        }

        scanner.close();
    }

}