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
        formula1CM.autoloadData();

        int choice = 1;

        Menu.showMenu();

        /* loop to keep the application running.
           The loop will quit with -1 as input */
        while (choice != -1) {
                               
            choice = Menu.getMenuChoice(input);

            if (choice == 0) {Menu.showMenu();}
            else if (choice == 1) {formula1CM.addDriver();}
            // else if (choice == 2) {formula1CM.deleteDriver();}
            // else if (choice == 3) {formula1CM.changeDriverTeam();}
            else if (choice == 4) {formula1CM.viewDrivers();}
            else if (choice == 5) {formula1CM.addRace();}
            // else if (choice == 6) {formula1CM.displayStatistics();
            // else if (choice == 7) {formula1CM.displayTable();
            else if (choice == 8) {formula1CM.saveAllData();}
        }
        
    }


    /**
     * Loads the Formula 1 Championship data from "drivers.data" file
     * TODO: also load from races.data file
     */
    private void autoloadData() {
        try { 
            Scanner rf = new Scanner(new BufferedReader(new FileReader("Formula1/data/drivers.data")));
            String fileRecord;
            // does not check if file has more records than maxDrivers
            // not implemented as file save from program itslf, no user input
            while (rf.hasNext()) {                
                fileRecord = rf.nextLine(); 
                String[] parts = fileRecord.split(",");
                String name = parts[0], location = parts[1], team = parts[2];
                int timesFirst = parseFileInt(parts[3]), timesSecond = parseFileInt(parts[4]), timesThird = parseFileInt(parts[4]);
                int totalPoints = parseFileInt(parts[6]), racesAttended = parseFileInt(parts[7]);
                Formula1Driver driver = new Formula1Driver(name, location, team, timesFirst, timesSecond, timesThird, totalPoints, racesAttended);
                updateStateOnAdd(driver);
            }
            rf.close();
            System.out.println("Formula 1 Championship data loaded.");
        } catch (IOException e) {
            System.out.println("Error IOException is: " + e);
        }
    }

    private void updateStateOnAdd(Formula1Driver formula1Driver) {
        this.teams.add(formula1Driver.getTeam());
        this.drivers.add(formula1Driver);
        this.nOfDrivers = this.nOfDrivers + 1;
    }


    private static int parseFileInt(String fileString) {
        int parsedInt = Integer.parseInt(fileString);
        return parsedInt;
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
                updateStateOnAdd(driver);
                System.out.println("Driver added");
            }
        }
    }


    /*
        This method retrieves the Formula1Driver whose name equals the
        name provided in the method signature
    */
    public static Formula1Driver driverFindByName(ArrayList<Formula1Driver> drivers, String name) {
        return drivers.stream().filter(driver-> name.equals(driver.getName()))
                .findFirst().orElse(null);
    }


    public void viewDrivers() {
        for (int i=0; i<nOfDrivers; i++) {
            Formula1Driver driver = drivers.get(i);
            System.out.println("");
            System.out.println("Driver: " + driver.getName());
            System.out.println("Location: " + driver.getLocation());
            System.out.println("Team: " + driver.getTeam());
            System.out.println("First: " + driver.getTimesFirst());
            System.out.println("Second: " + driver.getTimesSecond());
            System.out.println("Third: " + driver.getTimesThird());
            System.out.println("Points: " + driver.getTotalPoints());
            System.out.println("Races attended: " + driver.getRacesAttended());
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


    public void addRace() {
        Race race = new Race(this.drivers);
        races.add(race);
        // update formula 1 drivers data
        ArrayList<Formula1Driver> standings = race.getStandings();
        for (int i=0; i<standings.size(); i++) {
            Formula1Driver driver = driverFindByName(this.drivers, standings.get(i).name);
            driver.updateStatistics(i+1, Race.assignPoints(i+1));
        }
    }



    /**
     * Saves the Formula 1 Chmpionship drivers data to a "drivers.data" file
     */
    public void saveDriversData() {
        // TODO: needs to also save races data, not only drivers
        try {
            FileWriter wf = new FileWriter("Formula1/data/drivers.data");
            for (int i=0; i<nOfDrivers; i++) {
                Formula1Driver driver = drivers.get(i);
                wf.write(driver.getName() + "," +
                driver.getLocation() + "," +
                driver.getTeam() + "," +
                driver.getTimesFirst() + "," +
                driver.getTimesSecond() + "," +
                driver.getTimesThird() + "," +
                driver.getTotalPoints() + "," +
                driver.getRacesAttended() +  "\n");
                }
            wf.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Drivers data saved.");
    }

    public void saveRacesData() {
        saveDriversData();
    }



    public void saveAllData() {
        saveDriversData();
    }

}
