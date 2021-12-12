import java.util.ArrayList;
import java.util.Collections;

public class Race {

    private String date;
    private ArrayList<Formula1Driver> standings;

    /*
        This constructor just randomises the drivers array to get the standings
    */
    public Race(ArrayList<Formula1Driver> drivers) {
        this.standings = new ArrayList<Formula1Driver>(drivers);
        simulate();
        //TODO: input date
        this.date = "2021-01-01";
        // displayStandings();
    }

    public String getDate() {
        return this.date;
    }

    public ArrayList<Formula1Driver> getStandings() {
        return this.standings;
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
        Collections.shuffle(this.standings);
    }
}
