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

        if(this.customer.getServer() instanceof Server){
            
            if(((Server)this.customer.getServer()).getAboutToRest()){
                
                if(((Server)this.customer.getServer()).isStillServing()){
                    //Don't spawn any new served events
                } else {
                    //Spawn server rest event
                    //Let the server decide its resting time
                    ((Server)this.customer.getServer()).generateRestTime();
                    resultEvents.add(new EventRest(this.time, this.customer.getServer(), this.manager));
                }
            } else {
                if(((Server)this.customer.getServer()).checkAboutToRest()){
                    //check if it is serving anymore customers
                    if(((Server)this.customer.getServer()).isStillServing()){
                        //Don't rest yet and don't accept any 
                    } else {
                        ((Server)this.customer.getServer()).generateRestTime();
                        resultEvents.add(new EventRest(this.time, this.customer.getServer(), this.manager));
                    }  
                } else {
                    //Spwan the new event 
                    if(!this.customer.getServer().isQueueEmpty()){
                        //Server's queue has people waiting
                        resultEvents.add(new EventServed(this.time, this.customer.getServer().getFrontCustomer(), this.manager));
                    } else {
                        //No new event is spawned
                        //return null;
                    }
                }
            }
            
            
            /*
            else if(((Server)this.customer.getServer()).checkAboutToRest()){
                    //check if it is serving anymore customers
                    if(((Server)this.customer.getServer()).isStillServing()){
                        //Don't rest yet and don't accept any 
                    } else {
                        ((Server)this.customer.getServer()).generateRestTime();
                        resultEvents.add(new EventRest(this.time, this.customer.getServer(), this.manager));
                    }  
            } else {
                    //Spwan the new event 
                    if(!this.customer.getServer().isQueueEmpty()){
                        //Server's queue has people waiting
                        resultEvents.add(new EventServed(this.time, this.customer.getServer().getFrontCustomer(), this.manager));
                    } else {
                        //No new event is spawned
                        //return null;
                    }
            }*/

/*
            if(((Server)this.customer.getServer()).getAboutToRest()){
                //The server has already been flagged that it is going to rest

                if(((Server)this.customer.getServer()).isStillServing()){
                    //Don't spawn any new events
                } else {
                    //Spawn server rest event
                    resultEvents.add(new EventRest(this.time, this.customer.getServer(), this.manager));
                }

            } else if(((Server)this.customer.getServer()).checkAboutToRest()){
                //After checking serverRandomizer, the server has decided that it is going to rest
                //But serve the remaining customers
                
                if(((Server)this.customer.getServer()).isStillServing()){
                    //Don't spawn any new events
                } else {
                    //Spawn server rest event
                    resultEvents.add(new EventRest(this.time, this.customer.getServer(), this.manager));
                }

            } else {
                    //Spwan the new event 
                    if(!this.customer.getServer().isQueueEmpty()){
                        //Server's queue has people waiting
                        resultEvents.add(new EventServed(this.time, this.customer.getServer().getFrontCustomer(), this.manager));
                    } else {
                        //No new event is spawned
                        //return null;
                    }
            }*/
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