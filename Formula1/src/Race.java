import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Race {

    private LocalDate date;
    private ArrayList<Formula1Driver> standings;

    /**
     * Constructs a race with a randomised date
     */
    public Race(ArrayList<Formula1Driver> drivers, boolean isWeighted) {
        this.standings = new ArrayList<Formula1Driver>(drivers);
        this.simulate(); 
        if (isWeighted) {
            this.simulateWithWeights();
        }
        // Assign date randomly
        RandomDate rd = new RandomDate(LocalDate.of(2021, 1, 1), LocalDate.of(2022, 8, 31));
        this.date = rd.nextDate();
        // displayStandings();
    }

    /**
     * Constructs a race with a given date
     */
    public Race(LocalDate date, ArrayList<Formula1Driver> drivers) {
        this.standings = new ArrayList<Formula1Driver>(drivers);
        // this.simulate(); 
        // if (isWeighted) {
        //     this.simulateWithWeights();
        // }
        this.date = date;
    }

    // getter methods
    public LocalDate getDate() {
        return this.date;
    }

    public ArrayList<Formula1Driver> getStandings() {
        return this.standings;
    }

    /**
     * Gets the driver with the provided standing
     */
    public Formula1Driver getDriverByStanding(int standing) {
        Formula1Driver driver = this.standings.get(standing);
        return driver;    
    }

    /**
     * Display the race standings
     */
    public void displayStandings () {
        for (int i=0; i<this.standings.size(); i++) {
            String driverName = this.standings.get(i).name;
            int points = assignPoints(i+1);
            System.out.println("");
            System.out.println("Position: " + (i+1));
            System.out.println("Name: " + driverName);
            System.out.println("Points: " + points);
            System.out.println("");
        }
    }


    /**
     * Assigns points based on the driver standing
     */
    public static int assignPoints(int finalStanding) {
        int points;
        if (finalStanding == 1) {points=25;}
        else if (finalStanding == 2) {points=18;}
        else if (finalStanding == 3) {points=15;}
        else if (finalStanding == 4) {points=12;}
        else if (finalStanding == 5) {points=10;}
        else if (finalStanding == 6) {points=8;}
        else if (finalStanding == 7) {points=6;}
        else if (finalStanding == 8) {points=4;}
        else if (finalStanding == 9) {points=2;}
        else if (finalStanding == 10) {points=1;}
        else points = 0;
        return points;
    }


    /**
     * Simulates a random race
     */
    public void simulate() {
        Collections.shuffle(this.standings);
    }


    /**
     * Simulates a race that is not fully random: the first positions starting the race are more likely to win 
     */
    public void simulateWithWeights() {
        Random r = new Random();
        int randomInt = r.nextInt(100) + 1;
        Formula1Driver firstPlaced = this.standings.get(0);
        if (randomInt < 41) {firstPlaced = this.standings.get(0);}
        else if (randomInt <71) {firstPlaced = this.standings.get(1);}
        else if (randomInt <81) {firstPlaced = this.standings.get(2);}
        else if (randomInt <91) {firstPlaced = this.standings.get(3);}
        else if (randomInt <93) {firstPlaced = this.standings.get(4);}
        else if (randomInt <95) {firstPlaced = this.standings.get(5);;}
        else if (randomInt <97) {firstPlaced = this.standings.get(6);}
        else if (randomInt <99) {firstPlaced = this.standings.get(7);}
        else if (randomInt <101) {firstPlaced = this.standings.get(8);}
        // The rest of the positions (2–10) are determined completely randomly
        Collections.shuffle(this.standings);
        Collections.swap(this.standings, 0, this.standings.indexOf(firstPlaced));
    }


}
