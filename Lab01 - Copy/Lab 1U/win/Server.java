abstract public class Server{
    private int serverID;
    private int numCustomerPerTime;
    private double serviceTime;
    private double serverRestTime;
	private boolean serverBusy;
	private double serverNext;

	public double getServerNext() {
		return this.serverNext;
	}

	public void setServerNext(double serverNext) {
		this.serverNext = serverNext;
	}


    abstract public boolean isQueueAvail();

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

	public int getNumCustomerPerTime() {
		return this.numCustomerPerTime;
	}

	public void setNumCustomerPerTime(int numCustomerPerTime) {
		this.numCustomerPerTime = numCustomerPerTime;
	}

	public double getServiceTime() {
		return this.serviceTime;
	}

	public void setServiceTime(double serviceTime) {
		this.serviceTime = serviceTime;
	}

	public double getServerRestTime() {
		return this.serverRestTime;
	}

	public void setServerRestTime(double serverRestTime) {
		this.serverRestTime = serverRestTime;
	}

}