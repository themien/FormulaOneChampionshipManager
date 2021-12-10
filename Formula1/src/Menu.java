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
        System.out.println("4. Display stats");
        System.out.println("5. Add a race to the Formula 1 Championship");
        System.out.println("6. Display table");
        System.out.println("");
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
        choice = input.nextInt();
        System.out.println();
         

        if (choice < -1 || choice >10 ) {
            System.out.println("Choice not recognised, please try again.");
            // Recursive method to get a valid input
            choice = getMenuChoice(input);
        }

        return choice;
    }

    
}
