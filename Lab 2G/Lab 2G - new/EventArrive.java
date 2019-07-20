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
    
    public IEvent getNextEvent(){
        IServer nextServer;
        //update logs
        this.manager.getLog().logArrives(null, this.customer, this.time);
        this.manager.getLog().addToTotal(1);
        
        //ArrayList<IEvent> result = new ArrayList<>();

        //try to join a queue
        nextServer = manager.findFirstFreeServer();
        if(nextServer == null){
            nextServer = manager.findFirstAvailServer();
            if(nextServer == null){
                //All queues are full and customer can leave
                /**
                 * Elderly customers will leave for real and will not return
                 * Whereas, General customers will leave and return again at a later time
                 */
                //check customer type
                if(this.customer instanceof GeneralCustomer){
                    //the general customer will come back at a later time
                    GeneralCustomer generalCustomer = (GeneralCustomer)(this.customer);
                    double eventTime = generalCustomer.getReArrivalTime()+ generalCustomer.getArrivalTime();
                    if(eventTime == this.time){
                        //still no servers, so LEAVE
                        return new EventLeave(this.time, this.customer, this.manager);
                    } else {
                        //He can come back later
                        this.customer.setArrivalTime(generalCustomer.getReArrivalTime() + generalCustomer.getArrivalTime());
                        //he leaves and comes back later
                        return new EventLeave(this.time, this.customer, this.manager);
                        //return new EventArrive(generalCustomer.getArrivalTime(), this.customer, manager);
                    }
                    
                } else if(this.customer instanceof ElderlyCustomer){
                    //the elderly customer will leave
                    return new EventLeave(this.time, this.customer, this.manager);
                } else {
                    //if the input is guaranteed to be correct, this part should never get executed
                    return null; 
                }
                
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
        //return result;
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