import java.util.Scanner;

public class App {

    static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        Formula1ChampionshipManager formula1CM = new Formula1ChampionshipManager();
        int choice = 1;

        showMenu();

        /* loop to keep the application running.
           The loop will quit with "q" as input */
        while (choice != -1) {
                               
            choice = getMenuChoice();

            if (choice == 0) {showMenu();}
            else if (choice == 1) {formula1CM.addDriver();}
            else if (choice == 2) {formula1CM.viewDrivers();}
            // else if (choice == 'e') {hotel.viewEmptyRooms();}
            // else if (choice == 'd') {hotel.deleteCustomer();}
            // else if (choice == 'f') {hotel.findRoom();}
            // else if (choice == 's') {hotel.saveData();}
            // else if (choice == 'l') {hotel.loadData();}
            // else if (choice == 'o') {hotel.viewNames();}
            // // Shows the status of the waiting list, used for testing
            // else if (choice == 'w') {hotel.getWaitingList().show();}
        }
        
    }


    /**
     * Display the menu in the console
     */
    private static void showMenu() {
        System.out.println("----Menu-----");
        System.out.println("1. New Driver");
        System.out.println("2. Delete Driver");
        System.out.println("3. Change driver team");
        System.out.println("4. Display stats");
        System.out.println("5. Display table");
        System.out.println("6. Display table");
        System.out.println("");
    }



    /**
     * Prompts for a menu option
     * @return the menu ption chosen
     */
    private static int getMenuChoice() {
        System.out.println("");
        int choice;
        System.out.print("Press 0 to show the menu.");
        System.out.print(" Please enter an option: ");
        choice = input.nextInt();
         

        // if (validChoices.indexOf(choice) == -1) {
        //     System.out.println("Choice not recognised, please try again.");
        //     // Recursive method to get a valid input
        //     choice = getMenuChoice();
        // }

        return choice;
    }

    

    
}
