public class GeneralCustomer extends ElderlyCustomer{

    private double reArrivalTime;
    
    public GeneralCustomer(int custID, double arrivalTime, double reArrivalTime, IServer server){
		super(custID, arrivalTime, server);
        this.reArrivalTime = reArrivalTime;
    }
    
	public double getReArrivalTime() {
		return this.reArrivalTime;
	}
	
	@Override
	public IServer getServer() {
		return super.getServer();
	}

	@Override
	public void setServer(IServer server) {
		super.setServer(server);
	}


	/*
    public int getCustID() {
		return super.getCustID();
	}

	public void setCustID(int custID) {
		super.setCustID(custID);
	}

	public double getArrivalTime() {
		return super.getArrivalTime();
	}

	@Override
	public void setArrivalTime(double arrivalTime) {
		super.setArrivalTime(arrivalTime);
	}

	public double getWaitTime() {
		return super.getServedTime() - super.getArrivalTime();
	}

	public double getServedTime() {
		return super.getServedTime();
	}

	public void setServedTime(double servedTime) {
		super.setServedTime(servedTime);
	}
	*/
}