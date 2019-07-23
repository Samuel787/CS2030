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
}