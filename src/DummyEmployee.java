import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Robert Gilmore.
 */
public abstract class DummyEmployee extends Thread {

    private String jobTitle;

    public DummyEmployee(String jobTitle, String name) {
        super(name);
        this.jobTitle = jobTitle;
    }

    public void log(String actionName) {
        Logger.logAction(jobTitle + " " + getName(), actionName);
    }

    public void run() {
        int clockInLatenessMinutes = (int) (Math.random() * 31);
        SimulationClock.waitUntil(8, clockInLatenessMinutes);
    }

}
