public class Event{
	private int eventState;
	private int eventServerID;
	private int eventCustID;
	private double eventTime;

	public Event(){
		//empty contructor
	}

	//creating event with arguments
	public Event(int eventState, int eventServerID, int eventCustID, double eventTime){
		this.eventState = eventState;
		this.eventServerID = eventServerID;
		this.eventCustID = eventCustID;
		this.eventTime = eventTime;
	}
	//all the getters and setters
	public void setState(int eventState){
		this.eventState = eventState;
	}

	public int getState(){
		return this.eventState;
	}

	public void setServerID(int eventServerID){
		this.eventServerID = eventServerID;
	}

	public int getServerID(){
		return this.eventServerID;
	}
	public void setCustId(int eventCustID){
		this.eventCustID = eventCustID;
	}

	public int getCustID(){
		return this.eventCustID;
	}

	public void setTime(double eventTime){
		this.eventTime = eventTime;
	}

	public double getTime(){
		return this.eventTime;
	}


}