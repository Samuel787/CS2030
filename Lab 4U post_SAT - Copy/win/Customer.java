public abstract class Customer implements ICustomer{
    private int custID;
    private double arrivalTime;
    private double servedTime;

    public Customer(int custID, double arrivalTime){
        this.custID = custID;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public int getCustID() {
		return this.custID;
    }
    
    @Override
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
    @Override
	public double getWaitTime() {
		return this.servedTime - this.arrivalTime;
	}

    @Override
    public void setServedTime(double servedTime) {
		this.servedTime = servedTime;
	}

}