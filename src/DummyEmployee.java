import java.util.concurrent.locks.ReentrantLock;

public abstract class DummyEmployee extends Thread {

    private String jobTitle;
    private SimulationClock.Stopwatch workWatch = new SimulationClock.Stopwatch();
    private SimulationClock.Stopwatch lunchWatch = new SimulationClock.Stopwatch();
    protected int lunchHour = 12;
    protected int lunchMinute = 0;
    protected int lunchDuration = 30;
    protected int leavingHour = 4;
    protected int leavingMinute = 30;
    protected ReentrantLock busyLock = new ReentrantLock();

    public DummyEmployee(String jobTitle, String name) {
        super(name);
        busyLock.lock();
        this.jobTitle = jobTitle;
        this.start();
    }

    public void log(String actionName) {
        Logger.logAction(jobTitle + " " + getName(), actionName);
    }

    public void run() {
        // Clock in some time between 08:00 and 08:30.
        int clockInLatenessMinutes = (int) (Math.random() * 31);
        SimulationClock.waitUntil(8, clockInLatenessMinutes);
        clockIn();

        // Let the subclass do meetings or ask questions or whatever until lunch time.
        doMorningWork();

        // Do lunch.
        SimulationClock.waitUntil(lunchHour, lunchMinute);
        clockOutForLunch();
        SimulationClock.waitMinutes(lunchDuration);
        clockInFromLunch();

        // Let the subclass do meetings or ask questions or whatever until the end of the day.
        doAfternoonWork();

        // Clock out at the clock-out time.
        SimulationClock.waitUntil(leavingHour, leavingMinute);
        clockOut();
    }

    private void clockIn() {
        workWatch.reset();
        workWatch.start();
        log("clocked in");
        busyLock.unlock();
    }

    private void clockOutForLunch() {
        busyLock.lock();
        workWatch.pause();
        lunchWatch.reset();
        lunchWatch.start();
        log("clocked out for lunch");
    }

    private void clockInFromLunch() {
        lunchWatch.pause();
        workWatch.start();
        log("clocked in from lunch");
        busyLock.unlock();
    }

    private void clockOut() {
        busyLock.lock();
        workWatch.pause();
        log("clocked out");
    }

    public double getHoursWorked() {
        int minutesWorked = workWatch.totalTimeElapsed();
        return minutesWorked / 60;
    }

    protected abstract void doMorningWork();

    protected abstract void doAfternoonWork();

}
