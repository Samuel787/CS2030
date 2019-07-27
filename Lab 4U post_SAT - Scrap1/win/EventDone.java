import java.util.ArrayList;
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

    public ArrayList<IEvent> getNextEvent(){
        ArrayList<IEvent> resultEvents = new ArrayList<>();

        //update server
        this.customer.getServer().removeServing();

        //Log information
        this.manager.getLog().logDone(this.customer.getServer(), this.customer, this.time);
        //this.manager.getLog().addToWait(this.customer.getWaitTime());

        IServer currentServer = this.customer.getServer();

        if(currentServer instanceof HumanServer){
            HumanServer currentHumanServer = (HumanServer)currentServer;
            if(currentHumanServer.getAboutToRest()){

                if(currentHumanServer.isStillServing()){
                    //Don't spawn any new served events
                } else {
                    //Spawn server rest event
                    //Let the server decide its resting time
                    currentHumanServer.generateRestTime();
                    resultEvents.add(new EventRest(this.time, currentHumanServer, this.manager));
                }
            } else {
                if(currentHumanServer.checkAboutToRest()){
                    //check if it is serving anymore customers
                    if(currentHumanServer.isStillServing()){
                        //Don't rest yet and don't accept any 
                    } else {
                        currentHumanServer.generateRestTime();
                        resultEvents.add(new EventRest(this.time, this.customer.getServer(), this.manager));
                    }  
                } else {
                    //Spwan the new event 
                    if(!currentHumanServer.isQueueEmpty()){
                        //Server's queue has people waiting
                        resultEvents.add(new EventServed(this.time, currentHumanServer.getFrontCustomer(), this.manager));
                    } else {
                        //No new event is spawned
                        //return null;
                    }
                }
            }

        } else if(currentServer instanceof MachineServer){
            //this will never spawn a RESTEVENT
            MachineServer currentMachineServer = (MachineServer)currentServer;
            if(!currentMachineServer.isQueueEmpty()){
                //Server's queue has people waiting
                resultEvents.add(new EventServed(this.time, currentMachineServer.getFrontCustomer(), this.manager));
            }
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
        return EventDone.state_id;
    }
}