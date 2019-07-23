import java.util.ArrayList;

public class Shop{
    
    //Shop has a set of servers
    private ArrayList<IServer> servers;

    //Shop will have events happening
    private ArrayList<IEvent> events;

    //Shop has a Manager to handle the events
    private Manager manager;

    //Shop has a server randomizer which aids in the creation of the server
    private ServerRandomizer serverRandomizer;

    public Shop(ArrayList<ICustomer> customers,  int numArrival, int servMaxCust, int numServer, int servQueueSize, double probRest, double restRate, int randomSeed){
        
        serverRandomizer = new ServerRandomizer(randomSeed, restRate);
        
        //creates all the servers
        servers = new ArrayList<>(numServer);
        for(int i = 0; i < numServer; i++){
            servers.add(new Server(0.0, i, servMaxCust, servQueueSize, probRest, serverRandomizer));
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