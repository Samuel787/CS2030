public class EventArrive implements IEvent{

    private static int state_id = 0;
    private double time;
    private ICustomer customer;
    private Manager manager;

    public EventArrive(double time, ICustomer customer, Manager manager){
        this.time = time;
        this.customer = customer;
        this.manager = manager;
    }
    
    public IEvent getNextEvent(){
        IServer nextServer;
        //update logs
        this.manager.getLog().logArrives(null, this.customer, this.time);
        this.manager.getLog().addToTotal(1);
        
        //try to join a queue
        nextServer = manager.findFirstFreeServer();
        if(nextServer == null){
            nextServer = manager.findFirstAvailServer();
            if(nextServer == null){
                //All queues are full and customer can leave
                return new EventLeave(this.time, this.customer, this.manager);
            } else {
                //have to WAIT
                this.customer.setServer(nextServer);
                return new EventWait(time, customer, manager);
            }
        } else {
            //can straight away get SERVED
            this.customer.setServer(nextServer);
            return new EventServed(time, customer, manager);
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
        return EventArrive.state_id;
    }

}