public class GeneralCustomer implements ICustomer{
    
    private int custID;
    private double arrivalTime;
	private IServer server;
    //private double waitTime;
    private double servedTime;
    private double reArrivalTime;
    
    public GeneralCustomer(int custID, double arrivalTime, double reArrivalTime, IServer server){
        this.custID = custID;
		this.arrivalTime = arrivalTime;
        this.server = server;
        this.reArrivalTime = reArrivalTime;
    }
    
	public double getReArrivalTime() {
		return this.reArrivalTime;
	}
	
	@Override
	public IServer getServer() {
		return this.server;
	}

	@Override
	public void setServer(IServer server) {
		this.server = server;
	}


    public int getCustID() {
		return this.custID;
	}

	public void setCustID(int custID) {
		this.custID = custID;
	}

	public double getArrivalTime() {
		return this.arrivalTime;
	}

	@Override
	public void setArrivalTime(double arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public double getWaitTime() {
		return this.servedTime - this.arrivalTime;
	}

	// public void setWaitTime(double waitTime) {
	// 	this.waitTime = waitTime;
	// }

	public double getServedTime() {
		return this.servedTime;
	}

	public void setServedTime(double servedTime) {
		this.servedTime = servedTime;
	}

}