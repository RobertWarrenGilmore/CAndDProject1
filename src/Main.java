import java.util.*;

public class Main {

    public static void main(String[] args) {
        // 2014-08-06 06:00:00
        Calendar startDate = new GregorianCalendar(2014, Calendar.AUGUST, 6, 6, 0, 0);
        // one simulated minute per ten actual milliseconds
        double speedFactor = 6000;
        SimulationClock.start(startDate, 6000);

        // Create a team hierarchy.
        DummyPM carmen = new DummyPM("Carmen");
        TeamLead fernando = new TeamLead("Fernando", carmen);
        DummyDeveloper boris = new DummyDeveloper("Boris", fernando);
//        DummyDeveloper yuri = new DummyDeveloper("Yuri", fernando);
//        TeamLead guillermo = new TeamLead("Guillermo", carmen);
//        DummyDeveloper anton = new DummyDeveloper("Anton", guillermo);
//        DummyDeveloper alexander = new DummyDeveloper("Alexander", guillermo);
//        DummyDeveloper fyodor = new DummyDeveloper("Fyodor", guillermo);
//        DummyDeveloper natasha = new DummyDeveloper("Natasha", guillermo);
//        DummyDeveloper regina = new DummyDeveloper("Regina", guillermo);
//        TeamLead eliana = new TeamLead("Eliana", carmen);
//        DummyDeveloper vladimir = new DummyDeveloper("Vladimir", eliana);
//        DummyDeveloper yelyena = new DummyDeveloper("Yelyena", eliana);
//        DummyDeveloper ivan = new DummyDeveloper("Ivan", eliana);
    }

}
