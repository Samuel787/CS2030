import java.util.ArrayList;

public class EventRest implements IEvent{

    //very important -> shows the order in which the events are executed
    private static int state_id = 5; //State id shows the priority of the event
   
    private double time; //time of this event
    private IServer server; //the server experiencing the event
    private Manager manager; //shop manager as he will be managing the events

    public EventRest(double time, IServer server, Manager manager){
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
        this.manager.getLog().logRest(this.server, null, this.time);

        //currently all servers are instances of the same server type
        if(this.server instanceof Server){
            ((Server) this.server).startResting();

            //spawns a new eventBack
            resultEvents.add(new EventBack(this.time + ((Server) this.server).getRestTime(), this.server, this.manager));
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
        return EventRest.state_id;
    }



}