import java.util.*;

public class Main {

    public static void main(String[] args) {
        // 2014-08-06 06:00:00
        Date startDate = new GregorianCalendar(2014, Calendar.AUGUST, 6, 6, 0, 0).getTime();
        // one simulated minute per ten actual milliseconds
        double speedFactor = 6000;
        SimulationClock.start(startDate, 6000);

        // Create a team hierarchy and ask two questions simultaneously.
        DummyPM carmen = new DummyPM();
        TeamLead fernando = new TeamLead(carmen);
        TeamLead guillermo = new TeamLead(carmen);
        fernando.askQuestion();
        guillermo.askQuestion();

        // TODO Finish this.
    }

}
