import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// interface ChampionshipManager {
//     public void addDriver();
//     public void deleteDriver();
//     public void changeDriverTeam();
//     public void addRace();
//     public void displayStatistics();
//     public void displayTable();
//     public void saveData();
//     public void autoloadData();
// }



public class Formula1ChampionshipManager {//implements ChampionshipManager{

    // scanner instance to get user input
    static Scanner input = new Scanner(System.in).useDelimiter("\n");

    // sets a max amount of driver that can 
    // participate to the Formula 1 Championship 
    final int maxDrivers = 10;

    // number of drivers participating
    // NOTICE: may be not needed as can be obtained 
    // by counting the array drivers
    private int nOfDrivers = 0;

    // to maintain a list of all formula 1 teams
    public ArrayList<String> teams = new ArrayList<String>();

    // collection of the championship drivers
    public ArrayList<Formula1Driver> drivers = new ArrayList<Formula1Driver>();

    // collection of the championship races
    public ArrayList<Race> races = new ArrayList<Race>();


    public static void main(String[] args) throws Exception {

        Formula1ChampionshipManager formula1CM = new Formula1ChampionshipManager();
        int choice = 1;

        showMenu();

        /* loop to keep the application running.
           The loop will quit with -1 as input */
        while (choice != -1) {
                               
            choice = getMenuChoice();

            if (choice == 0) {showMenu();}
            else if (choice == 1) {formula1CM.addDriver();}
            // else if (choice == 2) {formula1CM.deleteDriver();}
            else if (choice == 3) {formula1CM.changeDriverTeam();}
            else if (choice == 4) {formula1CM.viewDrivers();}
            // else if (choice == 5) {formula1CM.addRace();
            // else if (choice == 6) {formula1CM.displayStatistics();
            // else if (choice == 7) {formula1CM.displayTable();
            else if (choice == 8) {formula1CM.saveData();}
        }
        
    }




    /**
     * Display the menu in the console
     */
    private static void showMenu() {
        System.out.println("----Menu-----");
        System.out.println("1. New Driver");
        System.out.println("2. Delete driver");
        System.out.println("3. Change driver team");
        System.out.println("4. Display stats");
        System.out.println("5. Display table");
        System.out.println("6. Display table");
        System.out.println("");
        System.out.println("8. Save data");
    }



    /**
     * Prompts for a menu option
     * @return the menu option chosen
     */
    private static int getMenuChoice() {
        System.out.println("");
        int choice;
        System.out.println("Press 0 to show the menu.");
        System.out.print("Please enter an option: ");
        choice = input.nextInt();
        System.out.println();
         

        if (choice < -1 || choice >10 ) {
            System.out.println("Choice not recognised, please try again.");
            // Recursive method to get a valid input
            choice = getMenuChoice();
        }

        return choice;
    }



    public void addDriver() {
        if (this.nOfDrivers == this.maxDrivers) {
            System.out.println("No more drivers can be added to the championship. Limit reached.");
        }
        else {
            System.out.print("Enter driver name: ");
            String name = input.next();
            System.out.print("Enter driver location: ");
            String location = input.next();
            System.out.print("Enter driver team: ");
            String team = input.next();
            Formula1Driver driver = new Formula1Driver(name, location, team);
            // check that there is no driver already participating with that team
            if (teams.contains(driver.getTeam())) {
                System.out.println("There is already a driver belonging to that team");
            }    
            else {
                teams.add(driver.getTeam());
                drivers.add(driver);
                this.nOfDrivers = this.nOfDrivers + 1;
                System.out.println("Driver added");
            }
        }
    }


    public void viewDrivers() {
        for (int i=0; i<nOfDrivers; i++) {
            Formula1Driver driver = drivers.get(i);
            System.out.println("");
            System.out.println("Driver: " + driver.name);
            System.out.println("Location: " + driver.location);
            System.out.println("Team: " + driver.getTeam());
            System.out.println("");
        }
    }


    public void changeDriverTeam() {
        System.out.print("Enter the name of the driver performing a change of team: ");
        String driversearch = input.next();
        // TODO: Get driver instance from name
        Formula1Driver driver = drivers.get(0);
        // TODO: CHECK IF DRIVER EXISTS 
        System.out.print("Enter the name of the new team: ");
        // TODO: CHECK THAT THERE IS NO DRIVER W THAT TEAM ALREADY
        String newTeam =  input.next();
        driver.setTeam(newTeam);
    }


    /**
     * Saves the Formula 1 Chmpionship data to a "formula1.data" file
     */
    public void saveData() {
        // TODO: needs to save championship races and drivers data, not only drivers
        try {
            FileWriter wf = new FileWriter("Formula1/data/drivers.data");
            for (int i=0; i<nOfDrivers; i++) {
                Formula1Driver driver = drivers.get(i);
                wf.write(driver.name + "," +
                driver.location + "," +
                driver.getTeam() +  "\n");
                }
            wf.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Formula 1 data saved.");
    }


    // /**
    //  * Loads the hotel data from "hotel.data" file
    //  */
    // public void autoloadData() {
    //     try { 
    //         Scanner rf = new Scanner(new BufferedReader(new FileReader("hotel.data")));
    //         String fileLine;
    //         // does not check if file has more lines than number of rooms
    //         // not implemented as file save from program itslf, no user input
    //         int index = 0;
    //         while (rf.hasNext()) {                
    //             fileLine = rf.nextLine(); 
    //             if (fileLine.equals("e")) {
    //                 this.rooms[index] = new Room(index);
    //             } else {
    //                 String[] parts = fileLine.split(",");
    //                 Person guest = new Person(parts[0], parts[1], parts[2]);
    //                 this.rooms[index] = new Room(index, guest, Integer.parseInt(parts[3]));
    //             }
    //             index++;
    //         }
    //         rf.close();
    //         System.out.println("Hotel data loaded.");
    //     } catch (IOException e) {
    //         System.out.println("Error IOException is: " + e);
    //     }
    // }

}
