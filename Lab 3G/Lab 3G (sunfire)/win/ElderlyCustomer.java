public class ElderlyCustomer implements ICustomer{
    
    private int custID;
    private double arrivalTime;
	private IServer server;
    //private double waitTime;
    private double servedTime;
    
    public ElderlyCustomer(int custID, double arrivalTime, IServer server){
        this.custID = custID;
		this.arrivalTime = arrivalTime;
		this.server = server;
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

	public double getArrivalTime() {
		return this.arrivalTime;
	}

	@Override
	public void setArrivalTime(double arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * This will return the time taken between arrival time and served time
	 */
	public double getWaitTime() {
		return this.servedTime - this.arrivalTime;
	}

	public double getServedTime() {
		return this.servedTime;
	}

	public void setServedTime(double servedTime) {
		this.servedTime = servedTime;
	}

}