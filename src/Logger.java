import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    /**
     * Provides standardised logging for actions.
     * @param actorName Who is doing the action?
     * @param actionName What is the action?
     */
    public static void logAction(String actorName, String actionName) {
        Date currentDate = SimulationClock.currentSimulationDate();
        String timeOfDay = new SimpleDateFormat("HH:mm").format(currentDate);
        String message = timeOfDay + " " + actorName + " " + actionName + ".";
        System.out.println(message);
    }

}
