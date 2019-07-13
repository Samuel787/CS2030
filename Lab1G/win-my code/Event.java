public class Event{
	private int eventState; //most important aspect for this class
	private int eventServerID;
	private int eventCustID;
	private double eventTime;
	private Customer customer;

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	//creating event with arguments
	public Event(int eventState, int eventServerID, Customer customer, double eventTime){
		this.eventState = eventState;
		this.eventServerID = eventServerID;
		if(customer == null){
			this.eventCustID = -1;
		} else {
			this.eventCustID = customer.getCustID();
		}
		this.customer = customer;
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