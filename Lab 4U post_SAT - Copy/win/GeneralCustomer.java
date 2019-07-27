public class GeneralCustomer extends Customer{

    private double reArrivalTime;
	private IServer server;
	
    public GeneralCustomer(int custID, double arrivalTime, double reArrivalTime, IServer server){
		super(custID, arrivalTime);
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
}