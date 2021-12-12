import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

interface ChampionshipManager {
    public void addDriver();
    public void deleteDriver();
    public void changeDriverTeam();
    public void addRace();
    public void displayStatistics();
    public void displayTable();
    public void saveData();
    public void autoLoadData();
}



public class Formula1ChampionshipManager {//implements ChampionshipManager{

    // scanner instance to get user input
    public static Scanner input = new Scanner(System.in).useDelimiter("\n");

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
    // TODO: either remove static or implement this class as static class
    public ArrayList<Formula1Driver> drivers = new ArrayList<Formula1Driver>();

    // collection of the championship races
    public ArrayList<Race> races = new ArrayList<Race>();


    public static void main(String[] args) throws Exception {

        Formula1ChampionshipManager formula1CM = new Formula1ChampionshipManager();
        formula1CM.autoLoadData();

        Menu.showMenu();
        int choice = 1;

        /* loop to keep the application running.
           The loop will quit with -1 as input */
        while (choice != -1) {
                               
            choice = Menu.getMenuChoice(input);

            if (choice == 0) {Menu.showMenu();}
            else if (choice == 1) {formula1CM.addDriver();}
            else if (choice == 2) {formula1CM.deleteDriver();}
            else if (choice == 3) {formula1CM.changeDriverTeam();}
            else if (choice == 4) {new Formula1TableSwing(formula1CM.sortByPointsAndTimesFirst());} // not needed
            else if (choice == 5) {formula1CM.addRace();}
            else if (choice == 6) {formula1CM.displayStatistics();}
            else if (choice == 7) {formula1CM.displayTable();}
            else if (choice == 8) {formula1CM.saveAllData();}
        }
        
    }


        /* 
     * Updates the properties of the Formula 1 Driver Championship instance
     * when a new formula1Driver is added. 
     */
    private void updateStateOnAdd(Formula1Driver formula1Driver) {
        this.teams.add(formula1Driver.getTeam());
        this.drivers.add(formula1Driver);
        this.nOfDrivers = this.nOfDrivers + 1;
    }



    /**
     * Loads the Formula 1 Championship data from "drivers.data" file
     * TODO: also load from races.data file
     */
    private void autoLoadData() {
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
                Formula1Driver driver = new Formula1Driver(name, location, team, timesFirst, timesSecond, 
                                                        timesThird, totalPoints, racesAttended);
                updateStateOnAdd(driver);
            }
            rf.close();
            System.out.println("Formula 1 Championship data loaded.");
        } catch (IOException e) {
            System.out.println("Error IOException is: " + e);
        }
    }


    public static int parseFileInt(String fileString) {
        int parsedInt = Integer.parseInt(fileString);
        return parsedInt;
    }


    /*
      This method retrieves the Formula1Driver whose name equals the
      name provided in the method signature
    */
    public Formula1Driver driverFindByName(String name) {
        return this.drivers.stream().filter(driver-> name.equals(driver.getName()))
                .findFirst().orElse(null);
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
                System.out.println("Driver not added. There is already a driver belonging to that team.");
            }    
            else {
                updateStateOnAdd(driver);
                System.out.println("Driver added.");
            }
        }
    }


    public void deleteDriver() {
        System.out.print("Deleting driver. Enter the name of the driver to delete permanently: ");
        String driversearch = input.next();
        Formula1Driver driver = driverFindByName(driversearch);
        if (driver == null) {System.out.println("Driver does not exist");}
        else {
            this.teams.remove(driver.getTeam());
            this.drivers.remove(driver);
        }
    }


    public void displayStatistics() {
        System.out.print("Select the driver name for which to provide statistics: ");
        String driversearch = input.next();
        Formula1Driver driver = driverFindByName(driversearch);
        System.out.println("Driver: " + driver.getName());
        System.out.println("Location: " + driver.getLocation());
        System.out.println("Team: " + driver.getTeam());
        System.out.println("First places: " + driver.getTimesFirst());
        System.out.println("Second places: " + driver.getTimesSecond());
        System.out.println("Third places: " + driver.getTimesThird());
        System.out.println("Points: " + driver.getTotalPoints());
        System.out.println("Races attended: " + driver.getRacesAttended());
    }


    public void changeDriverTeam() {
        System.out.print("Enter the name of the driver performing a change of team: ");
        String driversearch = input.next();
        Formula1Driver driver = driverFindByName(driversearch);
        if (driver == null) {System.out.println("Driver does not exist");}
        else {
            System.out.print("Enter the name of the new team: ");
            String newTeam =  input.next();
            // check if there is a driver participating with that team already
            if (this.teams.contains(newTeam)) {
                System.out.println("Driver's team not changed. There is already another driver participating with the same team.");
            } else {
                this.teams.remove(driver.getTeam());
                driver.setTeam(newTeam);
                this.teams.add(newTeam);
                System.out.println("Driver team updated.");
            }
        }
    }


    public void addRace() {
        Race race = new Race(this.drivers);
        races.add(race);
        // update formula 1 drivers data
        ArrayList<Formula1Driver> standings = race.getStandings();
        for (int i=0; i<standings.size(); i++) {
            Formula1Driver driver = driverFindByName(standings.get(i).name);
            driver.updateStatistics(i+1, Race.assignPoints(i+1));
        }
    }


    public ArrayList<Formula1Driver> sortByPointsAndTimesFirst() {
        ArrayList<Formula1Driver> driversSort = new ArrayList<Formula1Driver>(this.drivers);
        //TODO: this is a mistake, just resorting for 1st positions
        Collections.sort(driversSort, Comparator.comparing((Formula1Driver driver) -> driver.getTotalPoints()).reversed());
        return driversSort;
    }


    public void displayTable() {
        ArrayList<Formula1Driver> drivers = sortByPointsAndTimesFirst();
        // TODO:make table nicer
		for (int i = 0; i<drivers.size(); i++) {
            Formula1Driver driver = drivers.get(i);
            System.out.println(driver.getName() + " | " + driver.getTotalPoints() + " | " + driver.getTimesFirst());
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
        // TODO:save races data
        saveDriversData();
    }



    public void saveAllData() {
        saveDriversData();
    }

}
