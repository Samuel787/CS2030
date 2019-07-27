import java.util.ArrayList;

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
    
    public ArrayList<IEvent> getNextEvent(){
        IServer nextServer;
        //update logs
        this.manager.getLog().logArrives(null, this.customer, this.time);
        
        ArrayList<IEvent> resultEvents = new ArrayList<>();

        if(this.customer instanceof GeneralCustomer){
            nextServer = manager.findFirstFreeServer();
            if(nextServer == null){
                //Try to find an available server to his liking
                nextServer = manager.findServerForGeneralCustomer();
                if(nextServer == null){
                    //all the servers have full queues, so leave
                    resultEvents.add(new EventLeave(this.time, this.customer, this.manager)); 
                } else {
                    //Add this customer into the queue of that server
                    this.customer.setServer(nextServer);
                    nextServer.setServerNext(this.time);
                    resultEvents.add(new EventWait(time, customer, manager));
                }
            } else {
                //General Customer found a free server 
                //Managed to find free server, so can get served
                this.customer.setServer(nextServer);
                nextServer.setServerNext(this.time);
                resultEvents.add(new EventServed(time, customer, manager));
            }
        } else if(this.customer instanceof ElderlyCustomer){
            //Try to find a free server
            nextServer = manager.findFirstFreeServer();
            if(nextServer == null){
                //Try to find an available server with lowest id
                nextServer = manager.findFirstAvailServer();
                if(nextServer == null){
                    //all the servers have full queues, so leave
                    resultEvents.add(new EventLeave(this.time, this.customer, this.manager)); 
                } else {
                    //Add this customer into the queue of that server
                    this.customer.setServer(nextServer);
                    nextServer.setServerNext(this.time);
                    resultEvents.add(new EventWait(time, customer, manager));
                }
            } else {
                //Managed to find free server, so can get served
                this.customer.setServer(nextServer);
                nextServer.setServerNext(this.time);
                resultEvents.add(new EventServed(time, customer, manager));
            }
        } else {
            //Should not get into this block
        }

        /*
        //try to join a queue
        nextServer = manager.findFirstFreeServer();
        if(nextServer == null){
            nextServer = manager.findFirstAvailServer();
            if(nextServer == null){
                //All queues are full and customer can leave
                resultEvents.add(new EventLeave(this.time, this.customer, this.manager)); 
            } else {
                //have to WAIT
                this.customer.setServer(nextServer);
                resultEvents.add(new EventWait(time, customer, manager));
            }
        } else {
            //can straight away get SERVED
            this.customer.setServer(nextServer);
            nextServer.setServerNext(this.time);
            resultEvents.add(new EventServed(time, customer, manager));
        }
        */
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
        return EventArrive.state_id;
    }

}