/**
 * Created by Robert Gilmore.
 * Edited by Courtney McGorrill.
 */

public abstract class Employee  extends Thread {

	private String jobTitle;
    private SimulationClock.Stopwatch workWatch = new SimulationClock.Stopwatch();
    private SimulationClock.Stopwatch lunchWatch = new SimulationClock.Stopwatch();

	
	public Employee(String jobTitle, String name) {
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
        SimulationClock.waitMinutes(30);
        clockInFromLunch();

        //TODO Let the subclass do work stuff until the end of the shift.

        clockOut();
    }

    protected void clockIn() {
        workWatch.reset();
        workWatch.start();
        log("clocked in");
    }

    protected void clockOutForLunch() {
        workWatch.pause();
        lunchWatch.reset();
        lunchWatch.start();
        log("clocked out for lunch");
    }

    protected void clockInFromLunch() {
        lunchWatch.pause();
        workWatch.start();
        log("clocked in from lunch");
    }

    protected void clockOut() {
        workWatch.pause();
        log("clocked out");
    }

    public double getHoursWorked() {
        int minutesWorked = workWatch.totalTimeElapsed();
        return minutesWorked / 60;
    }
    
    public void projectStatusMeeting(){
    	
    }

}
