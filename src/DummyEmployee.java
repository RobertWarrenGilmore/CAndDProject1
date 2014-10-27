import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class DummyEmployee extends Thread {

    private String jobTitle;
    private boolean working = false;
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
        synchronized (this) {
            int clockInLatenessMinutes = (int) (Math.random() * 31);
            SimulationClock.waitUntil(8, clockInLatenessMinutes);
            clockIn();
        }

        // TODO Let the subclass do meetings or ask questions or whatever until lunch time.

        SimulationClock.waitUntil(12, 0);
        synchronized (this) {
            clockOutForLunch();
            SimulationClock.waitMinutes(30);
            clockInFromLunch();
        }

        // TODO Let the subclass do meetings or ask questions or whatever until the end of the day.

        SimulationClock.waitUntil(4, 30);
        synchronized (this) {
            clockOut();
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isWorking() {
        return working;
    }

    private void clockIn() {
        synchronized (this) {
            workWatch.reset();
            workWatch.start();
            working = true;
            log("clocked in");
        }
    }

    private void clockOutForLunch() {
        workWatch.pause();
        lunchWatch.reset();
        lunchWatch.start();
        working = false;
        log("clocked out for lunch");
    }

    private void clockInFromLunch() {
        lunchWatch.pause();
        workWatch.start();
        working = true;
        log("clocked in from lunch");
    }

    private void clockOut() {
        synchronized (this) {
            workWatch.pause();
            working = false;
            log("clocked out");
        }
    }

    public double getHoursWorked() {
        int minutesWorked = workWatch.totalTimeElapsed();
        return minutesWorked / 60;
    }

}
