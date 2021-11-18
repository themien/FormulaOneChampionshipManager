
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
        this.driverName = name;
        this.driverLocation = location;
        this.driverTeam = team;
    }

    public void getTeam() {}

    public void setTeam(String newTeam) {
        this.driverTeam = newTeam;
    }

}
