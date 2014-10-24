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

        // Let the subclass do work stuff until lunch time.
        doWorkUntil(12, 0);

        SimulationClock.waitUntil(12, 0);
        clockOutForLunch();
        SimulationClock.waitMinutes(30);
        clockInFromLunch();

        // Let the subclass do work stuff until the end of the shift.
        doWorkUntil(4, 30);

        SimulationClock.waitUntil(4, 30);
        clockOut();
    }

    /**
     * Does work specific to the subclass's job role,
     * @param clockHour the hour until which to work
     * @param clockMinute the minute until which to work
     */
    protected abstract void doWorkUntil(int clockHour, int clockMinute);

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
