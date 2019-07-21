import java.util.ArrayList;

public class Shop{
    
    //Shop has a set of servers
    private ArrayList<IServer> servers;

    //Shop will have events happening
    private ArrayList<IEvent> events;

    //Shop has a Manager to handle the events
    private Manager manager;

    public Shop(ArrayList<ICustomer> customers,  int numArrival, int servMaxCust, int numServer, int servQueueSize){
        //creates all the servers
        servers = new ArrayList<>(numServer);
        for(int i = 0; i < numServer; i++){
            servers.add(new Server(0.0, i, servMaxCust, servQueueSize));
        }

        events = new ArrayList<>();
        manager = new Manager(events, servers, customers);
        for(int i = 0; i < numArrival; i++){
            events.add(new EventArrive(customers.get(i).getArrivalTime(), customers.get(i), manager));
        }
        
        
    }

    public String open(){
        return manager.start();
    }

}