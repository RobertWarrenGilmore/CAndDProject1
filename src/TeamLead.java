import java.util.ArrayList;

/**
 * Created by Robert Gilmore.
 */
public class TeamLead extends Employee {

    private DummyPM boss;
    private ArrayList<Developer> arrived;
    private SimulationClock.Stopwatch meetingWatch = new SimulationClock.Stopwatch();
    		
    TeamLead(String name, DummyPM boss) {
        super("team lead", name);
        this.boss = boss;
        this.arrived = new ArrayList<Developer>();
    }

    /**
     * Asks a question of this team lead. The team lead might answer it or might pass the buck to the boss.
     */
    public void askQuestion() {
        synchronized (this) {
            log("receives a question");

            // Flip a coin.
            boolean answerable = (Math.random() < 0.5);
            if (!answerable) {
                // Let's ask the PM.
                log("takes a question to the head honcho");
                boss.askQuestion();
                log("gets an answer from the head honcho");
                return;
            }

            // Take your answer and go.
            log("answers a question");
            return;
        }
    }
    
    public void run() {
        int clockInLatenessMinutes = (int) (Math.random() * 31);
        SimulationClock.waitUntil(8, clockInLatenessMinutes);
        clockIn();

        // TODO PM meeting
        
        // Team meeting
        if(meetingWatch.totalTimeElapsed()==15){
        	ConferenceRoom.finishMeeting();
        	meetingWatch.pause();
        }

        clockOutForLunch();
        SimulationClock.waitMinutes(30);
        clockInFromLunch();

        //TODO Let the subclass do work stuff until the end of the shift.

        clockOut();
    }
    
    public void startMeeting(){
    	meetingWatch.start();
    }
    
    public void morningMeeting(Developer d){
    	arrived.add(d);
    	if(arrived.size()==3){
    		ConferenceRoom.morningMeeting(this);
    	}
    }

}
