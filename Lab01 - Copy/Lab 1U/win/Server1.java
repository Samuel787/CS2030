public class Server1 extends Server{
	//private int serverID;
	//private boolean serverBusy;
	private int custQueue; //waiting queue
	
	private double currTime;
	private Queue1 custQueue1;

	public Server1(int serverID, boolean serverBusy,
				 Queue1 queue1, int numCustomerPerTime,
				 double serviceTime, double serverRestTime,
				 double serverNext){
		super.setServerID(serverID);
		//this.serverBusy = serverBusy;
		super.setServerBusy(serverBusy);
		this.custQueue1 = queue1;
		super.setNumCustomerPerTime(numCustomerPerTime);
		super.setServiceTime(serviceTime);
		super.setServerRestTime(serverRestTime);
		super.setServerNext(serverNext);
	}

	public Queue1 getCustQueue1(){
		return (Queue1)this.custQueue1;
	}

/*
	public Server1(int serverID, boolean serverBusy, int custQueue, double serverNext, Queue1 queue1){
		this.serverID = serverID;
		this.serverBusy = serverBusy;
		this.custQueue = custQueue;
		this.serverNext = serverNext;
		this.custQueue1 = queue1;
	 }
	*/

	public void setCustQueue(int custQueue){
		this.custQueue = custQueue;
	}

	public void setCurrTime(double currTime){
		this.currTime = currTime;
	}

	public int getCustQueue(){
		return this.custQueue;
	}

	public double getCurrTime(){
		return this.currTime;
	}

	@Override
	public boolean isQueueAvail(){
		return (!this.custQueue1.isQueueFull());
	}
}