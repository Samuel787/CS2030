public class ElderlyCustomer extends Customer{
	
	private IServer server;

    public ElderlyCustomer(int custID, double arrivalTime, IServer server){
		super(custID, arrivalTime);
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
	
}