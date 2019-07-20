public class EventDone implements IEvent{

    private static int state_id = 4;
    private double time;
    private ICustomer customer;
    private Manager manager;

    public EventDone(double time, ICustomer customer, Manager manager){
        this.time = time;
        this.customer = customer;
        this.manager = manager;
    }

    public IEvent getNextEvent(){
        //update server
        this.customer.getServer().removeServing();

        //Log information
        this.manager.getLog().logDone(this.customer.getServer(), this.customer, this.time);
        //this.manager.getLog().addToWait(this.customer.getWaitTime());

        //Spwan the new event 
        if(!this.customer.getServer().isQueueEmpty()){
            //Server's queue has people waiting
            return new EventServed(this.time, this.customer.getServer().getFrontCustomer(), this.manager);
        } else {
            //No new event is spawned
            return null;
        }
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
        return EventDone.state_id;
    }
}