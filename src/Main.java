import java.util.*;

public class Main {

    public static void main(String[] args) {
        // 2014-08-06 06:00:00
        Calendar startDate = new GregorianCalendar(2014, Calendar.AUGUST, 6, 6, 0, 0);
        // one simulated minute per ten actual milliseconds
        double speedFactor = 6000;
        SimulationClock.start(startDate, 6000);

        // Create a team hierarchy.
        PM carmen = new PM("Carmen");
        TeamLead fernando = new TeamLead("Fernando", carmen);
        Developer boris = new Developer("Boris", fernando);
//        Developer yuri = new Developer("Yuri", fernando);
//        TeamLead guillermo = new TeamLead("Guillermo", carmen);
//        Developer anton = new Developer("Anton", guillermo);
//        Developer alexander = new Developer("Alexander", guillermo);
//        Developer fyodor = new Developer("Fyodor", guillermo);
//        Developer natasha = new Developer("Natasha", guillermo);
//        Developer regina = new Developer("Regina", guillermo);
//        TeamLead eliana = new TeamLead("Eliana", carmen);
//        Developer vladimir = new Developer("Vladimir", eliana);
//        Developer yelyena = new Developer("Yelyena", eliana);
//        Developer ivan = new Developer("Ivan", eliana);
    }

}
