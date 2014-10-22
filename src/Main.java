import java.util.*;

public class Main {

    public static void main(String[] args) {
        // 2014-08-06 06:00:00
        Date startDate = new GregorianCalendar(2014, Calendar.AUGUST, 6, 6, 0, 0).getTime();
        // one simulated minute per ten actual milliseconds
        double speedFactor = 6000;
        SimulationClock.start(startDate, 6000);

        // Here is an example of how to use the simulated clock and the logger.
        // I doubt that we want to use Timers for our actual simulation, but
        // here they provide simple timed threads.
        Timer timer = new Timer();
        long delay;
        // After a delay of two simulated hours, log that a Manager goes to lunch.
        delay = SimulationClock.actualDelay(2 * 60 * 60 * 1000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Logger.logAction("Manager", "goes to lunch");
            }
        }, delay);
        // After a delay of three and a half simulated hours, log that Arturo swims to Antarctica.
        delay = SimulationClock.actualDelay((long) (3.5 * 60 * 60 * 1000));
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Logger.logAction("Arturo", "swims to Antarctica");
            }
        }, delay);

        // TODO Finish this.
    }

}
