import java.util.ArrayList;
import java.util.Scanner;

interface ChampionshipManager {
    public void addDriver();
}



public class Formula1ChampionshipManager implements ChampionshipManager{

    static final Scanner input = new Scanner(System.in);

    final int maxDrivers = 10;
    private int nOfDrivers = 0;

    // to maintain a list of all formla 1 teams
    public ArrayList<String> teams = new ArrayList<String>();

    // collection of drivers
    public ArrayList<Formula1Driver> drivers = new ArrayList<Formula1Driver>();

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

}
