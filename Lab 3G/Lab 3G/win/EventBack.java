public class EventBack implements IEvent{

    //very important -> shows the order in which the events are executed
    private static int state_id = 6; //State id shows the priority of the event
   
    private double time; //time of this event
    private IServer server; //the server experiencing the event
    private Manager manager; //shop manager as he will be managing the events

    public EventBack(double time, IServer server, Manager manager){
        this.time = time;
        this.server = server;
        this.manager = manager;
    }

    /**
     * All it does is mark the server as no longer resting
     * It does not spawn any new events
     */
    public ArrayList<IEvent> getNextEvent(){
        ArrayList<IEvent> resultEvents = new ArrayList<>();

        //currently all servers are instances of the same server type
        if(this.server instanceof Server){
            ((Server) this.server).stopResting();
        }
        return resultEvents;
    }

    @Override
    public ICustomer getCustomer(){
        return null; //we are not returning any customers here
    }

    @Override
    public IServer getServer(){
        return this.server;
    }

    @Override
    public double getTime(){
        return this.time;
    }

    @Override
    public int getStateID(){
        return EventBack.state_id;
    }



}