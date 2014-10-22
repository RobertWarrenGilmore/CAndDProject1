import java.util.Date;


public abstract class Employee {

	protected Date timeIn;
	protected Date timeOut;
	protected Date lunchOut;
	protected Date lunchIn;
	
	public Employee(SimulationClock clock) {

	}
	
	public boolean clockIn() {
		Date currentTime = SimulationClock.currentSimulationDate();
		//while current time is before 8, wait
		while(true) {
			break;
		}
		//if the time is between 8 and 8:30, set timeIn
		if(true) {
			//timeIn = currentTime;
			return true;
		} else {
			//illegal state
			return false;
		}
			
	}
	
	public boolean takeLunch() {
		//if employee is on the clock
		if(timeIn != null) {
			//set lunchOut
			return true;
		} else {
			//illegal state
			return false;
		}
		
	}
	
	public boolean returnFromLunch() {
		//if on lunch
		if(lunchOut != null) {
			//if duration of lunch is valid
			if(true) {
				//set lunchIn
				return true;
			}
		} 
		return false;
	}
	
	public boolean clockOut() {
		//if on the clock and returned from lunch
		if(lunchIn != null) {
			//if duration on the clock is valid
			if(true){
				//set time out
				return true;
			}
		}
		return false;
	}

}
