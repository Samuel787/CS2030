import java.util.ArrayList;

public class Shop{
    
    //Shop has a set of servers
    private ArrayList<IServer> servers;

    //Shop has a set of customers coming in
    private ArrayList<ICustomer> customers;

    //Shop will have events happening
    //private PriorityQueue<IEvent> events;
    private ArrayList<IEvent> events;
    //Shop has a Manager to handle the events
    private Manager manager;

    //Shop has a clock. See if this is needed
    double currClock = 0.0;

    public Shop(int[] custIDs, int[] custType, double[] custReArrivalTime, double[] custArrivalTime, int numArrival, int servMaxCust, int numServer, int servQueueSize){
        //creates all the servers
        servers = new ArrayList<>(numServer);
        for(int i = 0; i < numServer; i++){
            servers.add(new Server(0.0, i, servMaxCust, servQueueSize));
        }

        //creates all the customers
        customers = new ArrayList<>(numArrival);
        for(int i = 0; i < numArrival; i++){
            //LAB 2G - differentiate between General and Elderly customers
            if(custType[i] == 1){
                //Elderly customer
                customers.add(new ElderlyCustomer(custIDs[i], custArrivalTime[i], null));
            } else if(custType[i] == 2){
                //General customer
                customers.add(new GeneralCustomer(custIDs[i], custArrivalTime[i], custReArrivalTime[i], null));
            }

        }
        
        //creates all the arrival events and adds them to Priority Queue
        //events = new PriorityQueue<>();
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