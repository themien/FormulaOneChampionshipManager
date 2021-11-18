
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

    public String getTeam() {
        return this.team;
    }

    public void setTeam(String newTeam) {
        this.team = newTeam;
    }

}
