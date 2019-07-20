public class EventWait implements IEvent{
    
    private static int state_id = 1;
    private double time;
    private ICustomer customer;
    private Manager manager;

    public EventWait(double time, ICustomer customer, Manager manager){
        this.time = time;
        this.customer = customer;
        this.manager = manager;
    }

    public IEvent getNextEvent(){
        //update logs
        this.manager.getLog().logWaits(this.customer.getServer(), this.customer, this.time);
        //add this customer to the server queue
        this.customer.getServer().addToQueue(this.customer);
        //spawns no new events
        return null;
    }

    @Override
    public ICustomer getCustomer(){
        return this.customer;
    }

    @Override
    public IServer getServer(){
        return this.customer.getServer();
    }

    @Override
    public double getTime(){
        return this.time;
    }

    @Override
    public int getStateID(){
        return EventWait.state_id;
    }
}