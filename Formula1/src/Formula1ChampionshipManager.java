import java.util.ArrayList;

interface ChampionshipManager {
    public void addDriver();
}


public class Formula1ChampionshipManager implements ChampionshipManager{

    private int nOfDrivers = 0;

    // to maintain a list of all formla 1 teams
    // Do new teams enter or is it a fixed number?????
    public ArrayList<String> teams = new ArrayList<String>();

    // collection of drivers
    public ArrayList<Formula1Driver> drivers = new ArrayList<Formula1Driver>();

    public void addDriver() {
        // TO CHANGE BACK TO VARIABLES
        // Needs to ask for input
        Formula1Driver driver = new Formula1Driver("Damiano", "Italy", "Ferrari");
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

    public void viewDrivers() {
        for (int i=0; i<nOfDrivers; i++) {
            Formula1Driver driver = drivers.get(i);
            System.out.println(driver.name);
        }
    }

}
