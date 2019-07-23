import java.util.ArrayList;

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

    public ArrayList<IEvent> getNextEvent(){

        ArrayList<IEvent> resultEvents = new ArrayList<>();
        //serve the customer if server is not busy
        if(!this.customer.getServer().isBusy()){

            //this will automatically udpate the serverNext based on whether server becomes busy or not
            this.customer.getServer().addServing(this.time);

            //update the server's next serve time
            //this.customer.getServer().setServerNext(this.time + IServer.SERVICE_TIME);
        
            //update the customer serve time -> When he starts getting served
            this.customer.setServedTime(this.time);

            //Log the event
            this.manager.getLog().logServed(this.customer.getServer(), this.customer, this.time);

            //spwan new done event
            
            resultEvents.add(new EventDone(this.time + IServer.SERVICE_TIME, this.customer, this.manager));
        }

        return resultEvents;
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