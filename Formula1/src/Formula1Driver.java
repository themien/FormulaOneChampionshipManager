
/*
This class which inherits from driver holds the information of 
*/
public class Formula1Driver extends Driver {

    private int timesFirst;
    private int timesSecond;
    private int timesThird;
    private int totalPoints;
    private int racesAttended;
    private String team;

    public Formula1Driver(String name, String location, String team) {
        this.name = name;
        this.location = location;
        this.team = team;
        this.timesFirst = 0;
        this.timesSecond = 0;
        this.timesThird = 0;
        this.totalPoints = 0;
        this.racesAttended = 0;
    }

    public Formula1Driver(String name, String location, String team, int timesFirst, int timesSecond, int timesThird, int totalPoints, int racesAttended) {
        this.name = name;
        this.location = location;
        this.team = team;
        this.timesFirst = timesFirst;
        this.timesSecond = timesSecond;
        this.timesThird = timesThird;
        this.totalPoints = totalPoints;
        this.racesAttended = racesAttended;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTeam() {
        return this.team;
    }

    public void setTeam(String newTeam) {
        this.team = newTeam;
    }

    public int getTimesFirst() {
        return this.timesFirst;
    }

    public int getTimesSecond() {
        return this.timesSecond;
    }

    public int getTimesThird() {
        return this.timesThird;
    }

    public int getTotalPoints() {
        return this.totalPoints;
    }

    public int getRacesAttended() {
        return this.racesAttended;
    }

    public void updateStatistics(int position, int newPoints) {
        if (position == 1) {this.timesFirst++;}
        else if (position == 2) {this.timesSecond++;}
        else if (position == 3) {this.timesThird++;}

        this.totalPoints = this.totalPoints + newPoints;
        this.racesAttended++;
    }

}
