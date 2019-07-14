import java.util.ArrayList;
//import java.util.PriorityQueue;
public class Manager{
    
    //private PriorityQueue<IEvent> events;
    private ArrayList<IEvent> events;
    private ArrayList<ICustomer> customers;
    private ArrayList<IServer> servers;
    private Log log;

    public Manager(ArrayList<IEvent> events, ArrayList<IServer> servers, ArrayList<ICustomer> customers){
        this.events = events;
        this.customers = customers;
        this.servers = servers;
        log = new Log();
    };

    //Manager will start operating the shop
    public String start(){
        while(events.size() > 0){
            //retrieve the earliest event
            IEvent event = events.get(0);
            events.remove(0);
            IEvent nextEvent = event.getNextEvent();
            if(nextEvent != null){
                events.add(nextEvent);
                //reorganize the events
                arrangeEvents(events);
            }
        }
        return log.toString();
    }

    public void arrangeEvents(ArrayList<IEvent> events){
        for(int i=events.size()-1; i>0; i--){
            if(events.get(i).getTime() < events.get(i-1).getTime() ||
                (events.get(i).getTime() == events.get(i-1).getTime() &&
                events.get(i).getStateID() >= events.get(i-1).getStateID())
            ){
                IEvent temp = events.get(i);
                events.set(i, events.get(i-1));
                events.set(i-1, temp);
            }
        }
    }

    public IServer findFirstFreeServer(){
        for(int i=0; i<servers.size(); i++){
            if(!servers.get(i).isBusy())
                return servers.get(i); //this server is free
        }
        return null; //all servers are busy
    }

    public IServer findFirstAvailServer(){
        for(int i=0; i < servers.size(); i++){
            if(!servers.get(i).isQueueFull())
                return servers.get(i); 
        }
        return null; //all servers have full queue
    }

    public IServer findShortestQueueServer(){
        if(servers == null || servers.size() == 0) return null;
        int shortest_id = 0; //id of the server with the shortest queue
        int shortest_length = servers.get(0).getCurrQueueLength();        
        for(int i=1; i < servers.size(); i++){
            if(servers.get(i).getCurrQueueLength() < shortest_length){
                shortest_length = servers.get(i).getCurrQueueLength();
                shortest_id = i;
            }
        }
        return servers.get(shortest_id);
    }

    //to toString method can be accessed via this
    public Log getLog(){
        return this.log;
    }

    
}