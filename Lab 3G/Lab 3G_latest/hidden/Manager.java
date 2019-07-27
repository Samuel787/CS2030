import java.util.ArrayList;
//import java.util.PriorityQueue;
public class Manager{
    
    //private PriorityQueue<IEvent> events;
    private ArrayList<IEvent> events;
    //private ArrayList<ICustomer> customers;
    private ArrayList<IServer> servers;
    private Log log;

    public Manager(ArrayList<IEvent> events, ArrayList<IServer> servers, ArrayList<ICustomer> customers){
        this.events = events;
       //this.customers = customers;
        this.servers = servers;
        log = new Log();
    };

    //Manager will start operating the shop
    public String start(){
        while(events.size() > 0){
            //retrieve the earliest event
            IEvent event = events.get(0);
            events.remove(0);
            ArrayList<IEvent> nextEvents = event.getNextEvent();
            for(int i=0; i< nextEvents.size(); i++){
                IEvent nextEvent = nextEvents.get(i);
                if(nextEvent != null){
                    events.add(nextEvent);
                    //reorganize the events
                    arrangeEvents(events);
                }
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
            if(!servers.get(i).isBusy() && !servers.get(i).getIsResting() && !servers.get(i).getAboutToRest())
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

    public IServer findServerForGeneralCustomer(){
        if(servers == null || servers.size() == 0) return null;
        /* 
            First find queue with shortest length
                If there are more than one such queue:
                    Find the queue with least number of elderly
                        If there are more than one such queue:
                            choose the one with the smallest ID
        */
        int shortest_length = servers.get(0).getCurrQueueLength();
        for(int i=1; i< servers.size(); i++){
            if(servers.get(i).getCurrQueueLength() < shortest_length){
                shortest_length = servers.get(i).getCurrQueueLength();
            }
        }

        //get all the queues with shortest_length
        ArrayList<IServer> shortestServers = new ArrayList<>();
        for(IServer i: servers){
            if(i.getCurrQueueLength() == shortest_length){
                shortestServers.add(i);
            }
        }

        if(shortestServers.size() == 1){
            return shortestServers.get(0);
        } else {
            //More than one server with the shortest length -> Find server with least elderly
            return findServerWithLeastElderly(shortestServers);
        }

    }

    public IServer findServerWithLeastElderly(ArrayList<IServer> servers){
        if(servers.size() == 0) return null;
        int least_elderly = servers.get(0).getNumElderly();
        for(int i=1; i<servers.size(); i++){
            if(servers.get(i).getNumElderly() < least_elderly){
                least_elderly = servers.get(i).getNumElderly(); 
            }
        }

        //return the first server with least_elderly
        for(int i=0; i<servers.size(); i++){
            if(servers.get(i).getNumElderly() == least_elderly){
                return servers.get(i);
            }
        }
        return null;
    }

    public IServer findShortestQueueServer(){
        if(servers == null || servers.size() == 0) return null;
        int shortest_id = 0; //id of the server with the shortest queue
        int shortest_length = servers.get(0).getCurrQueueLength(); 
        ArrayList<IServer> shortestServers = new ArrayList<>();       
        for(int i=1; i < servers.size(); i++){
            if(servers.get(i).getCurrQueueLength() < shortest_length){
                shortest_length = servers.get(i).getCurrQueueLength();
                shortest_id = i; //the servers are arranged in ArrayList in order of their IDs
            }
        }

        //get all the servers with that shortest server queue length
        for(int i=0; i < servers.size(); i++){
            if(servers.get(i).getCurrQueueLength() == shortest_length){
                shortestServers.add(servers.get(i));
            }
        }

        if(shortestServers.size() == 1){
            return shortestServers.get(0);
        }
        //get out the least number of elderly
        int least_elderly = shortestServers.get(0).getNumElderly();
        for(int i=1; i< shortestServers.size(); i++){
            if(shortestServers.get(i).getNumElderly() < least_elderly){
                least_elderly = shortestServers.get(i).getNumElderly();
            }
        }

        //loop through to find the first occurence
        for(int i=0; i<shortestServers.size(); i++){
            if(least_elderly == shortestServers.get(i).getNumElderly()){
                return shortestServers.get(i);
            }
        }
        return null;
        //return servers.get(shortest_id);
    }

    /*
    public IServer findFewestElderlyQueueServer(){
        int shortest_id = 0;
        int least_elderly = servers.get(0).getNumElderly();
        for(int i=1; i < servers.size(); i++){
            if(servers.get(i).getNumElderly() < least_elderly){
                shortest_id = i;
                least_elderly = servers.get(i).getNumElderly();
            }
        }
    }
    */
    
    //to toString method can be accessed via this
    public Log getLog(){
        return this.log;
    }

    
}