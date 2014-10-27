import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Robert Gilmore.
 */
public abstract class DummyEmployee extends Thread {

    private String jobTitle;
    private SimulationClock.Stopwatch workWatch = new SimulationClock.Stopwatch();
    private SimulationClock.Stopwatch lunchWatch = new SimulationClock.Stopwatch();
    protected int lunchLength = 30;

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
        clockIn();

        // TODO Let the subclass do work stuff until lunch time.

        clockOutForLunch();
        SimulationClock.waitMinutes(lunchLength);
        clockInFromLunch();

        //TODO Let the subclass do work stuff until the end of the shift.

        clockOut();
    }

    private void clockIn() {
        workWatch.reset();
        workWatch.start();
        log("clocked in");
    }

    private void clockOutForLunch() {
        workWatch.pause();
        lunchWatch.reset();
        lunchWatch.start();
        log("clocked out for lunch");
    }

    private void clockInFromLunch() {
        lunchWatch.pause();
        workWatch.start();
        log("clocked in from lunch");
    }

    private void clockOut() {
        workWatch.pause();
        log("clocked out");
    }

    public double getHoursWorked() {
        int minutesWorked = workWatch.totalTimeElapsed();
        return minutesWorked / 60;
    }

}
