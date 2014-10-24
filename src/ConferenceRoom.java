import java.util.ArrayList;

/**
 * 
 * Created by Courtney McGorrill.
 *
 */

public class ConferenceRoom{

	static boolean inUse = false;
	static ArrayList<Employee> waiting = new ArrayList<Employee>();

	/**
	 * When all memebers of a team arrive, the team lead attempts to enter the room
	 * @param tl
	 */
	public static void morningMeeting(TeamLead tl){
		if (inUse) {
			waiting.add(tl);
		} else {
			tl.startMeeting();
			inUse = true;
		}
	}
	
	/**
	 * team lead/manager ends any meeting, any waiting parties may enter
	 */
	public static void finishMeeting(){
		inUse = false;
		if (waiting.size()>0) {
			waiting.remove(0);
			inUse = true;
		}
	}
	
	/**
	 * until all employees are present, wait to start meeting
	 * @param e
	 */
	public static void projectStatusMeeting(Employee e){
		waiting.add(e);
		if (waiting.size() == 13) {
			inUse = true;
			waiting.clear();
		}
	}
}
