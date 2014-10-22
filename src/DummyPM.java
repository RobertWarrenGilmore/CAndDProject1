/**
 * Created by Robert Gilmore.
 */
public class DummyPM extends DummyEmployee {

    public DummyPM(String name) {
        super("PM", name);
    }

    public void askQuestion() {
        synchronized (this) {
            log("receives a question");
            try {
                // The buck stops here, but give it ten minutes.
                wait(SimulationClock.actualDelay(10 * 60 * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log("answers a question");
        }
    }
}
