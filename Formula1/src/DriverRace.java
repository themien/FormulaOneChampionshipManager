import java.time.LocalDate;

/**
 * Utility class to hold the information of a driver s race
*/
public class DriverRace {
    LocalDate raceDate;
    int position;

    public DriverRace(LocalDate rDate, int position) {
        this.raceDate = rDate;
        this.position = position;
    }
}
