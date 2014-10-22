import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Robert Gilmore.
 */
public class SimulationClock {

    private static boolean started = false;
    private static Calendar simulatedStartDate;
    private static Calendar actualStartDate;
    private static double speedMultiplier;

    /**
     * Starts the simulation clock at a given simulated Date, running at a given speed.
     * @param startCal The date at which the clock starts according to the simulation.
     * @param speedMultiplier How many milliseconds pass in the simulation for each real millisecond.
     */
    public synchronized static void start(Calendar startCal, double speedMultiplier) {
        if (started)
            throw new IllegalStateException("The SimulationClock was already running. It can only be start()ed once.");
        started = true;
        simulatedStartDate = startCal;
        actualStartDate = GregorianCalendar.getInstance();
        SimulationClock.speedMultiplier = speedMultiplier;
    }

    /**
     * Pauses the current thread for the given number of simulated minutes.
     * @param simulatedMinutes how many simulated minutes to wait
     */
    public static void waitMinutes(int simulatedMinutes) {
        if (!started)
            throw new IllegalStateException("The SimulationClock hasn't been started. Call start() first.");
        long simulatedMilliseconds = simulatedMinutes * 60 * 1000;
        long actualMilliseconds = actualDelay(simulatedMilliseconds);
        try {
            Thread.currentThread().wait(actualMilliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pauses the current thread until the simulated time specified.
     * @param clockHour the hour of the simulated time until which to wait
     * @param clockMinute the minute of the simulated time until which to wait
     */
    public static void waitUntil(int clockHour, int clockMinute) {
        if (!started)
            throw new IllegalStateException("The SimulationClock hasn't been started. Call start() first.");
        Calendar until = getNext(clockHour, clockMinute);
        long simulatedDelay = until.getTimeInMillis() - currentSimulationDate().getTimeInMillis();
        long actualDelay = actualDelay(simulatedDelay);
        try {
            Thread.currentThread().wait(actualDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes a duration in actual milliseconds and returns the same duration in simulated milliseconds.
     * @param actualMilliseconds a duration in actual milliseconds
     * @return the same duration in simulated milliseconds
     */
    private static long simulatedDelay(long actualMilliseconds) {
        if (!started)
            throw new IllegalStateException("The SimulationClock hasn't been started. Call start() first.");
        return (long) (actualMilliseconds * speedMultiplier);
    }

    /**
     * Takes a duration in actual milliseconds and returns the same duration in simulated milliseconds.
     * @param simulatedMilliseconds a duration in actual milliseconds
     * @return the same duration in simulated milliseconds
     */
    private static long actualDelay(long simulatedMilliseconds) {
        if (!started)
            throw new IllegalStateException("The SimulationClock hasn't been started. Call start() first.");
        return (long) (simulatedMilliseconds / speedMultiplier);
    }

    private static Calendar getNext(int hour, int minute) {
        Calendar currentDate = SimulationClock.currentSimulationDate();
        Calendar desiredDate = GregorianCalendar.getInstance();
        desiredDate.setTimeInMillis(currentDate.getTimeInMillis());
        desiredDate.set(Calendar.HOUR_OF_DAY, hour);
        desiredDate.set(Calendar.MINUTE, minute);
        desiredDate.set(Calendar.SECOND, 0);
        desiredDate.set(Calendar.MILLISECOND, 0);
        if (desiredDate.before(currentDate)) {
            desiredDate.add(Calendar.DATE, 1);
        }
        return desiredDate;
    }

    /**
     * @return the current date, according to the simulation
     */
    private static Calendar currentSimulationDate() {
        if (!started)
            throw new IllegalStateException("The SimulationClock hasn't been started. Call start() first.");
        Calendar actualNow = GregorianCalendar.getInstance();
        long actualDifference = actualNow.getTimeInMillis() - actualStartDate.getTimeInMillis();
        long simulatedDifference = simulatedDelay(actualDifference);
        long simulatedStartTime = simulatedStartDate.getTimeInMillis();
        Calendar simulatedNow = GregorianCalendar.getInstance();
        simulatedNow.setTimeInMillis(simulatedStartTime + simulatedDifference);
        return simulatedNow;
    }

    public static String currentTimeString() {
        return new SimpleDateFormat("HH:mm").format(currentSimulationDate());
    }

}
