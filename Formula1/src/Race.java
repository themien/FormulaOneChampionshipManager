public class Race {
    
    public int calculatePoints(int finalStanding) {
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
}
