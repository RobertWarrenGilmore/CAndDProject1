package canddproject1;

import java.util.Date;

public class SimulationClock {

    private static boolean started = false;
    private static Date simulatedStartDate;
    private static Date actualStartDate;
    private static double speedMultiplier;

    /**
     * Starts the simulation clock at a given simulated Date, running at a given speed.
     * @param startDate The Date at which the clock starts according to the simulation.
     * @param speedMultiplier How many milliseconds pass in the simulation for each real millisecond.
     */
    public synchronized static void start(Date startDate, double speedMultiplier) {
        if (started)
            throw new IllegalStateException("The SimulationClock was already running. It can only be start()ed once.");
        started = true;
        simulatedStartDate = startDate;
        actualStartDate = new Date();
        SimulationClock.speedMultiplier = speedMultiplier;
    }

    /**
     * Takes a duration in actual milliseconds and returns the same duration in simulated milliseconds.
     * @param actualMilliseconds a duration in actual milliseconds
     * @return the same duration in simulated milliseconds
     */
    public static long simulatedDelay(long actualMilliseconds) {
        if (!started)
            throw new IllegalStateException("The SimulationClock hasn't been started. Call start() first.");
        return (long) (actualMilliseconds * speedMultiplier);
    }

    /**
     * Takes a duration in actual milliseconds and returns the same duration in simulated milliseconds.
     * @param simulatedMilliseconds a duration in actual milliseconds
     * @return the same duration in simulated milliseconds
     */
    public static long actualDelay(long simulatedMilliseconds) {
        if (!started)
            throw new IllegalStateException("The SimulationClock hasn't been started. Call start() first.");
        return (long) (simulatedMilliseconds / speedMultiplier);
    }

    /**
     * @return the current Date, according to the simulation
     */
    public static Date currentSimulationDate() {
        if (!started)
            throw new IllegalStateException("The SimulationClock hasn't been started. Call start() first.");
        Date actualNow = new Date();
        long actualDifference = actualNow.getTime() - actualStartDate.getTime();
        long simulatedDifference = simulatedDelay(actualDifference);
        long simulatedStartTime = simulatedStartDate.getTime();
        Date simulatedNow = new Date(simulatedStartTime + simulatedDifference);
        return simulatedNow;
    }

}
