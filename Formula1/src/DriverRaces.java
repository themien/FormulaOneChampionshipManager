import java.util.ArrayList;

public class DriverRaces {

    public ArrayList<DriverRace> driverRaces = new ArrayList<DriverRace>();
    
    public DriverRaces(Formula1Driver driver, Formula1ChampionshipManager formula1CM) {
        for (int i = 0; i<formula1CM.races.size(); i++) {
            Race race = formula1CM.races.get(i);
            if (race.getStandings().indexOf(driver) != -1) {
                driverRaces.add(new DriverRace(race.getDate(), race.getStandings().indexOf(driver)));
            }
        }
    }

    
}
