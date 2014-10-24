/**
 * Created by Courtney McGorrill.
 */
public class Developer extends Employee{

	TeamLead boss;

    public Developer(String name, TeamLead boss) {
        super("developer", name);
        this.boss = boss;
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
    
    public void morningMeeting(){
    	
    }
    
    

}
