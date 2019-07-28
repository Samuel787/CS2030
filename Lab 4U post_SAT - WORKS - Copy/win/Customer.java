public abstract class Customer implements ICustomer{
    
    //attributes of all customers
    private int custID;
    private double arrivalTime;
    private IServer server;
    private double servedTime;

    public Customer(int custID, double arrivalTime, IServer server){
        this.custID = custID;
        this.arrivalTime = arrivalTime;
        this.server = server;
    }

    @Override
    public IServer getServer(){
        return this.server;
    }

    @Override
    public void setServer(IServer server){
        this.server = server;
    }

    @Override
    public int getCustID(){
        return this.custID;
    }

    @Override
    public double getArrivalTime(){
        return this.arrivalTime;
    }

    @Override
    public void setArrivalTime(double arrivalTime){
        this.arrivalTime = arrivalTime;
    }

    @Override
    public double getWaitTime(){
        return this.servedTime - this.arrivalTime;
    }

    @Override
    public void setServedTime(double time){
        this.servedTime = time;
    }
}