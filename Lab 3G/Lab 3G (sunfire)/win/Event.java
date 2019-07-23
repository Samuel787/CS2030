/*
public class Event implements IEvent{
    private State eventState;
    //private int eventServerID;
    //private int eventCustID;
    private double eventTime;
    private ICustomer customer; //customer contains custID and serverID

    public Event(State newState, ICustomer customer, double eventTime){
        this.eventState = newState;
        this.customer = customer;
        this.eventTime = eventTime;
    }

    @Override 
    public State getState(){
        return this.eventState;
    }

    @Override
    public double getTime(){
        return eventTime;
    }

    @Override
    public IServer getServer(){
        return customer.getServer();
    }
    
    @Override
    public ICustomer getCustomer(){
        return this.customer;
    }

    @Override
    public int compareTo(IEvent event) {
        if(this.getTime() < event.getTime()){
            return -1;
        } else if(this.getTime() == event.getTime() &&
                    this.getState().ordinal() >= event.getState().ordinal()){
                        return -1;
        } else {
            return 0;
        }
    }
}
*/