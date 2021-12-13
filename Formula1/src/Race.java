import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Race {

    private LocalDate date;
    private ArrayList<Formula1Driver> standings;

    /*
        This constructor just randomises the drivers array to get the standings
    */
    public Race(ArrayList<Formula1Driver> drivers) {
        // TODO: assign standings manually
        this.standings = new ArrayList<Formula1Driver>(drivers);
        simulate(); //////////////////////////////////////////////////////
        // Assign date randomly
        RandomDate rd = new RandomDate(LocalDate.of(2021, 1, 1), LocalDate.of(2022, 8, 31));
        this.date = rd.nextDate();
        // displayStandings();
    }

    public Race(LocalDate date, ArrayList<Formula1Driver> drivers) {
        this.standings = new ArrayList<Formula1Driver>(drivers);
        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public ArrayList<Formula1Driver> getStandings() {
        return this.standings;
    }

    public Formula1Driver getDriverByStanding(int standing) {
        Formula1Driver driver = this.standings.get(standing);
        return driver;    
    }

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


    public void simulate() {
        // TODO: to take arraylist of formula 1 drivers
        Collections.shuffle(this.standings);
    }



    public void simulateWithWeights() {
        //TODO:The rest of the positions (2–10) are determined completely randomly
        Collections.shuffle(this.standings);
        ArrayList<Formula1Driver> driversStandings = new ArrayList<Formula1Driver>(20);
        Random r = new Random();
        for (int i=0; i<this.standings.size(); i++) {
            //create a random number
            int randomInt = r.nextInt(100) + 1;
            if (randomInt < 41) {driversStandings.add(this.standings.remove(0));}
            else if (randomInt <71) {driversStandings.add(this.standings.remove(1));}
            else if (randomInt <81) {driversStandings.add(this.standings.remove(2));}
            else if (randomInt <91) {driversStandings.add(this.standings.remove(3));}
            else if (randomInt <93) {driversStandings.add(this.standings.remove(4));}
            else if (randomInt <95) {driversStandings.add(this.standings.remove(5));}
            else if (randomInt <97) {driversStandings.add(this.standings.remove(6));}
            else if (randomInt <99) {driversStandings.add(this.standings.remove(7));}
            else if (randomInt <101) {driversStandings.add(this.standings.remove(8));}
        }
        this.standings = driversStandings;
    }


}
