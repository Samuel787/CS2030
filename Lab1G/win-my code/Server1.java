public class Server1{
	
	private int serverID;
	
	//might be used in the future
	private int numCustomerPerTime;
	private double serviceTime;
	
	private double serverRestTime;
	private boolean serverBusy;
	private double serverNext;

	private double currTime;
	private Queue1 custQueue1;

	public Server1(int serverID, boolean serverBusy,
				 Queue1 queue1, int numCustomerPerTime,
				 double serviceTime, double serverRestTime,
				 double serverNext){

		this.serverID = serverID;
		//super.setServerID(serverID);
		this.serverBusy = serverBusy;
		//super.setServerBusy(serverBusy);
		this.custQueue1 = queue1;
		this.numCustomerPerTime = numCustomerPerTime;

		//super.setNumCustomerPerTime(numCustomerPerTime);
		this.serviceTime = serviceTime;
		this.serverRestTime = serverRestTime;
		this.serverNext = serverNext;
		
		//super.setServiceTime(serviceTime);
		//super.setServerRestTime(serverRestTime);
		//super.setServerNext(serverNext);
	}

	public double getServerNext() {
		return this.serverNext;
	}

	public void setServerNext(double serverNext) {
		this.serverNext = serverNext;
	}

	public boolean getServerBusy() {
		return this.serverBusy;
	}

	public void setServerBusy(boolean serverBusy) {
		this.serverBusy = serverBusy;
	}

	public int getServerID() {
		return this.serverID;
	}

	public void setServerID(int serverID) {
		this.serverID = serverID;
	}

	public double getServerRestTime() {
		return this.serverRestTime;
	}

	public void setServerRestTime(double serverRestTime) {
		this.serverRestTime = serverRestTime;
	}

	public Queue1 getCustQueue1(){
		return this.custQueue1;
	}

	public void setCurrTime(double currTime){
		this.currTime = currTime;
	}

	public double getCurrTime(){
		return this.currTime;
	}

	public boolean isQueueAvail(){
		return (!this.custQueue1.isQueueFull());
	}
}