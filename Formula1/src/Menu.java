
import java.util.Scanner;

public class Menu {

    /**
     * Display the menu in the console
     */
    public static void showMenu() {
        System.out.println("----Menu-----");
        System.out.println("1. New Driver");
        System.out.println("2. Delete driver");
        System.out.println("3. Change driver team");
        System.out.println("4. Display stats for a specified driver");
        System.out.println("5. Add a race to the Formula 1 Championship");
        System.out.println("6. Show the GUI");
        System.out.println("7. Display table");
        System.out.println("8. Save data");
    }


    /**
     * Prompts for a menu option
     * @return the menu option chosen
     */
    public static int getMenuChoice(Scanner input) {
        System.out.println("");
        int choice;
        System.out.println("Press 0 to show the menu.");
        System.out.print("Please enter an option: ");
        String inputed = input.next();
        try {

            choice = Integer.parseInt(inputed);
            System.out.println("");
            if (choice < -1 || choice >10 ) {
                System.out.println("Choice not recognised, please try again.");
                // Recursive method to get a valid input
                choice = getMenuChoice(input);
            }
        } catch (NumberFormatException e) {
            System.out.println("");
            System.out.print("Seleceted choice is not a number. PLease insert a correct choice: ");
            choice = getMenuChoice(input);
        }
        return choice;
    }

    
}
