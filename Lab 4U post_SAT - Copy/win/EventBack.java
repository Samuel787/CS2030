import java.util.ArrayList;

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
        this.manager.getLog().logBack(this.server, null, this.time);
        //currently all servers are instances of the same server type
        if(this.server instanceof HumanServer){
            HumanServer currentHumanServer = (HumanServer) this.server;
            currentHumanServer.stopResting();

            //now the server can only server one person at a time so we have to update the server serving capacity
            //((HumanServer) this.server).setNumCustPerTime(1);

            //At this point, server is not serving anyone so it can serve up to its maximum capacity
            int servMaxCapacity = currentHumanServer.getNumCustPerTime();
            for(int i=0; i < servMaxCapacity; i++){
                if(!currentHumanServer.isQueueEmpty()){
                    //Server's queue has people waiting
                    resultEvents.add(new EventServed(this.time, currentHumanServer.getFrontCustomer(), this.manager));
                    this.time += 0.001;
                } else {
                    //queue is empty already
                    break;
                }
            }

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