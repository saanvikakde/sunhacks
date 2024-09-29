
package javaapplicationsunhacks;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class JavaApplicationSunhacks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    //Variables 
    Scanner scnr = new Scanner(System.in);
    CoffeeShop coffee = new CoffeeShop(); 
    boolean continueGame = true;

     System.out.println("Congrats on opening your coffee shop! Your initial budget is $800.\n-------------------------------------------------------------------");

        for (int i = 0; i < 10 && continueGame; i++) {
            System.out.println("Today is Day " + (i + 1) + ". Good luck! <3\n-------------------------");
            getUserInput(scnr, coffee);
            factors(coffee);
            results(coffee);

            // Prompt user to continue or not
            if (i < 9) {  // Don't ask if it's the last day
                System.out.println("Do you want to continue to the next day? (yes/no)");
                String response = scnr.nextLine().toLowerCase();
                if (response.equals("no")) {
                    continueGame = false;
                    System.out.println("Thank you for playing!");
                }
            }

            if (i == 9 && continueGame) {
                System.out.println("Congratulations! You've completed 10 days of managing your coffee shop.");
            }
        }
    }
    
    
public static void getUserInput(Scanner scnr, CoffeeShop coffee) { //obtain input from user 
String option; boolean choice = true; 


        while (choice) {
            System.out.println("Current Values:");
            System.out.printf("Balance: $%.2f\n", coffee.getBalance());
            System.out.printf("a. Price: $%.2f\n", coffee.getPrice());
            System.out.println("b. Supplier: " + (coffee.getSupplier()));
            System.out.printf("c. Advertising Budget: $%.2f\n", coffee.getAdvertising());
            System.out.println("d. Number of Cups: " + coffee.getCups());
            System.out.println("Enter a/b/c/d to change a value or type 'done' to exit:");
            option = scnr.nextLine().toLowerCase();  // Handle case-insensitive input

            if (option.equals("done")) {
                choice = false;  // Exit loop
                System.out.println("Exiting...");
                break;
            }

            try {
                // Handle options
                switch (option) {
                    case "a":  // Set price of coffee
                        System.out.println("Enter price of coffee: ");
                        coffee.setPrice(scnr.nextDouble());
                        scnr.nextLine();  // Consume the newline character left by nextDouble
                        break;

                    case "b":  // Choose supplier
                        System.out.println("Choose a supplier:\na. Local organic farm : $400\nb. Bulk corporate supplier : $200\nEnter a/b:");
                        String supplierOption = scnr.nextLine();
                        if (supplierOption.equals("a")) {
                           
                            coffee.setSupplier("a");
                        } else if (supplierOption.equals("b")) {
                
                            coffee.setSupplier("b");
                        } else {
                            System.out.println("Invalid supplier choice. Please enter 'a' or 'b'.");
                        }
                        break;

                    case "c":  // Set advertising budget
                        System.out.println("Enter the amount of money you would like to allocate to advertising: ");
                        coffee.setAdvertising(scnr.nextDouble());
                        scnr.nextLine();  // Consume the newline character left by nextDouble
                        break;

                    case "d":  // Set number of cups
                        System.out.println("Enter the number of cups you plan to buy: ");
                        coffee.setCups(scnr.nextInt());
                        coffee.setBalance(-(coffee.getCups()));
                        scnr.nextLine();  // Consume the newline character left by nextInt
                        break;

                    default:
                        System.out.println("Invalid option. Please enter a valid choice (a/b/c/d) or type 'DONE' to exit.");
                }
            } catch (InputMismatchException e) {
                // Handle input mismatch exceptions
                System.out.println("Input mismatch error! Please enter a valid number.");
                scnr.nextLine();  // Clear the buffer
            }
        } // end of while loop


}

 
public static void factors(CoffeeShop coffee) {
    // Define the lower and upper bounds
    int min = 100;
    int max = 200;

    // Generate a random number between 250 and 300
    int numPeople = (int) (Math.random() * ((max - min) + 1)) + min;
    Random rand = new Random();

    String[] weather = {"Stormy", "Hail", "Windy", "Sunny", "Rainy", "Tsunami"};
    int randomIndex = rand.nextInt(weather.length);
    String randomWeather = weather[randomIndex];

    System.out.println("The weather today is " + randomWeather);

    // Weather-based customer adjustments
    switch (randomWeather) {
        case "Rainy":
        case "Stormy":
            System.out.println("You lost 10 customers, now you have " + (numPeople - 10) + " customers.");
            numPeople -= 10;
            break;
        case "Sunny":
        case "Windy":
            System.out.println("You gained 20 customers, now you have " + (numPeople + 20) + " customers.");
            numPeople += 20;
            break;
        case "Tsunami":
            System.out.println("You had no customers today.");
            numPeople = 0;
            break;
        case "Hail":
            System.out.println("You lost 15 customers, now you have " + (numPeople - 15) + " customers.");
            numPeople -= 15;
            break;
    }

    // Price-based customer adjustments
    if (coffee.getPrice() > 10) {
        System.out.println("Your coffee price is too high! No customers today.");
        numPeople = 0;
    } else if (coffee.getPrice() < 5) {
        System.out.println("Your coffee price is low! More customers are coming.");
        numPeople += 30;  // Increase customers when price is less than $5
    }

    // Supplier-based customer adjustments
    if (coffee.getSupplier().equals("a")) {
        numPeople += 15;
    } 
    coffee.addCustomers(numPeople);
}
    public static void advertisingImpact(CoffeeShop coffee) {
    // Calculate 10% of the advertising budget
    coffee.addCustomers((int) (coffee.getAdvertising() * 0.10));
 
}

    public static void results(CoffeeShop coffee) {
        // Calculate revenue and expenses
        int customers = coffee.getCustomers();
        double price = coffee.getPrice();
        double dailyRevenue = customers * price;
        double bal = coffee.getBalance() + dailyRevenue; 
        coffee.setCups(0); 
        coffee.setAdvertising(0); 
        coffee.setSupplier("c");
        coffee.setBalance(bal);      

        System.out.println("\nDaily Results:");
        System.out.printf("Customers served: %d\n", customers);
        System.out.printf("Total Revenue: $%.2f\n", dailyRevenue);

        if (price > 10) {
            System.out.println("Warning: Your coffee price is too high! No customers.");
        } else if (price < 5) {
            System.out.println("Good choice! Lower price attracted more customers.");
        }

        System.out.printf("Remaining balance: $%.2f\n", coffee.getBalance());
        System.out.println("-------------------------------------------------\n");
    }
}
