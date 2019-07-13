
public class Server{
	private int serverID;
	private boolean serverBusy;
	private int custQueue; //waiting queue
	private double serverNext;
	private double currTime;
	private Queue custQueue1;

	public Server(int serverID, boolean serverBusy, int custQueue, double serverNext, Queue1 queue1){
		this.serverID = serverID;
		this.serverBusy = serverBusy;
		this.custQueue = custQueue;
		this.serverNext = serverNext;
		this.custQueue1 = queue1;
	 }
	
	public Queue1 getCustQueue1(){
		return (Queue1)this.custQueue1;
	}
	public void setServerID(int serverID){
		this.serverID = serverID;
	}

	public void setServerBusy(boolean serverBusy){
		this.serverBusy = serverBusy;
	}

	public void setCustQueue(int custQueue){
		this.custQueue = custQueue;
	}

	public void setServerNext(double serverNext){
		this.serverNext = serverNext;
	}

	public void setCurrTime(double currTime){
		this.currTime = currTime;
	}

	public int getServerID(){
		return this.serverID;
	}
	public boolean getServerBusy(){
		return this.serverBusy;
	}

	public int getCustQueue(){
		return this.custQueue;
	}

	public double getServerNext(){
		return this.serverNext;
	}

	public double getCurrTime(){
		return this.currTime;
	}
}