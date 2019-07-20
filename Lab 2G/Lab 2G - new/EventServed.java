public class EventServed implements IEvent{

    private static int state_id = 2;
    private double time;
    private ICustomer customer;
    private Manager manager;

    public EventServed(double time, ICustomer customer, Manager manager){
        this.time = time;
        this.customer = customer;
        this.manager = manager;
    }

    public IEvent getNextEvent(){

        //serve the customer if server is not busy
        if(!this.customer.getServer().isBusy()){
            this.customer.getServer().addServing();
        }
        //update the server's next serve time
        this.customer.getServer().setServerNext(this.time + IServer.SERVICE_TIME);
        //update the customer serve time
        this.customer.setServedTime(this.time);

        //Log the event
        this.manager.getLog().logServed(this.customer.getServer(), this.customer, this.time);
        //this.manager.getLog().addToServed(1);

        //spwan new done event
        return new EventDone(this.time + IServer.SERVICE_TIME, this.customer, this.manager);
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
        return EventServed.state_id;
    }
}