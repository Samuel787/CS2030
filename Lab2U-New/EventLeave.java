public class EventLeave implements IEvent{

    private static int state_id = 3;
    private double time;
    private ICustomer customer;
    private Manager manager;

    public EventLeave(double time, ICustomer customer, Manager manager){
        this.time = time;
        this.customer = customer;
        this.manager = manager;
    }

    public IEvent getNextEvent(){
        //Update logs
        /*
        this.manager.getLog().logServed(this.customer.getServer(), this.customer, this.time);
        this.manager.getLog().addToServed(1);
        */

        this.manager.getLog().logLeaves(this.customer.getServer(), this.customer, this.time);
        //Leave is the last event and generates no new events
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
        return EventLeave.state_id;
    }
    
}