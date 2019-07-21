import java.util.ArrayList;

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

    public ArrayList<IEvent> getNextEvent(){
        //Update logs
        ArrayList<IEvent> resultEvents = new ArrayList<>();
        this.manager.getLog().logLeaves(this.customer.getServer(), this.customer, this.time);
        //Leave is the last event and generates no new events
        
        if(this.customer instanceof GeneralCustomer){
            //re-create arrive event
            GeneralCustomer generalCustomer = (GeneralCustomer)(this.customer);
            this.customer.setArrivalTime(generalCustomer.getReArrivalTime() + generalCustomer.getArrivalTime());
            resultEvents.add(new EventArrive(generalCustomer.getArrivalTime(), this.customer, manager));
            return resultEvents;
        } else if(this.customer instanceof ElderlyCustomer){
            return resultEvents;
        } else {
            //this block should not be reached
            return resultEvents;
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
        return EventLeave.state_id;
    }
    
}