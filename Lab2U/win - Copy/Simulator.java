import java.util.ArrayList;

public class Simulator {
  // Constants
  public static final double SERVICE_TIME = 1.0; //THIS IS ACTUALLY NOT FIXED
  public static final int MAX_EVENTS = 1100; // 1000 + 100
  public static final int UNKNOWN = -1; // Multi-purpose
  
  // States
  public static final int ARRIVES = 0; // Customer arrives
  public static final int WAITS   = 1; // Customer waiting
  public static final int SERVED  = 2; // Customer served
  public static final int LEAVES  = 3; // Customer leaves
  public static final int DONE    = 4; // Customer done served
  
  public static String simulate(int[] custIDs, double[] custArriveTime, int numArrival, int servMaxCust, int numServer, int servQueueSize) {
    /** Variables */
    // Server
    //Queue1 queue1 = new Queue1(servQueueSize); //maximum 1 people in the queue
    //Server1 server = new Server1(0, false, queue1, servMaxCust, SERVICE_TIME, 0, 0.0);
    //the servers are added into the arraylist in ascending order of server ids
    ArrayList<Server1> servers = new ArrayList<>();
    //initializing all the servers
    
    for(int i=0; i < numServer; i++){
      servers.add(new Server1(i, false, new Queue1(servQueueSize), servMaxCust, SERVICE_TIME, 0, 0.0));
    }
    
    //servers.add(server);

    // Customers
    Customer[] customers = new Customer[MAX_EVENTS];
    //double[] custServedTime = new double[MAX_EVENTS];
    
    // Events
    Event[] events = new Event[MAX_EVENTS];

    int numEvents = 0;

    // Log
    Log infoLog = new Log(0,0,0.0);

    /** Process */
    // Initialize
    for(numEvents=0; numEvents<numArrival; numEvents++) {
      customers[numEvents] = new Customer(numEvents, custArriveTime[numEvents], UNKNOWN); //check if cust served time is 0.0 or unknown
      events[numEvents] = new Event(ARRIVES, UNKNOWN, customers[numEvents], customers[numEvents].getCustArrivalTime());
    }

    // Run
    Event currEvent, nextEvent;

    while(numEvents > 0) {

      //get out the current state
      currEvent = events[0];

      //shifting all the events one position forward in the array
      for(int i=1; i<numEvents; i++) {
        events[i-1] = events[i];
      }
      numEvents--;

      switch(currEvent.getState()) {
        case ARRIVES:
          //get an available server
          if(findFreeServer(servers) == -1){
            if(findNextServer(servers) == -1){
              //all queues are full
              nextEvent = currEvent;
              nextEvent.setServerID(-1);
              nextEvent.getCustomer().setServerID(-1);
              nextEvent.setState(LEAVES);
            } else {
              //Wait
              //availServer = servers.get(findNextServer(servers));
              int serverID = findNextServer(servers);
              nextEvent = currEvent;
              nextEvent.setServerID(serverID);
              nextEvent.getCustomer().setServerID(serverID);
              nextEvent.setState(WAITS);     
            } 
          } else {
            //Get served at the free server
            int serverID = findFreeServer(servers);

            //availServer = servers.get(serverID);
            nextEvent = currEvent;
            nextEvent.setServerID(serverID);
            nextEvent.getCustomer().setServerID(serverID);
            nextEvent.setState(SERVED);
          }

          System.out.println(String.format("%.3f", currEvent.getTime()) + 
              " " + currEvent.getCustID() + " arrives");
          //logTotalCust++;
          infoLog.incrementTotalCust(1);

          if(nextEvent.getState() != UNKNOWN) {
            events[numEvents] = nextEvent;
            // Re-arrange
            events = arrangeEvents(events, numEvents);
            numEvents++;
          }
          break;
        case SERVED:
          if(!servers.get(currEvent.getServerID()).getServerBusy()){
            servers.get(currEvent.getServerID()).addPeopleServing();
          }
          servers.get(currEvent.getServerID()).setServerNext(currEvent.getTime() + SERVICE_TIME);
          nextEvent = new Event(DONE, currEvent.getServerID(), currEvent.getCustomer(), servers.get(currEvent.getServerID()).getServerNext());

          //custServedTime[currEvent.getCustID()] = currEvent.getTime();
          customers[currEvent.getCustomer().getCustID()].setCustServedTime(currEvent.getTime());
          System.out.println(String.format("%.3f", currEvent.getTime()) + 
              " " + currEvent.getCustID() + " served by "+currEvent.getCustomer().getServerID());
          //logServedCust++;
          infoLog.incrementServedCust(1);

          if(nextEvent.getState() != UNKNOWN) {
            events[numEvents] = nextEvent;
            // Re-arrange
            events = arrangeEvents(events, numEvents);
            numEvents++;
          }
          break;
        case WAITS:
          servers.get(currEvent.getServerID()).getCustQueue1().addToQueue(currEvent.getCustomer());
          nextEvent = new Event(UNKNOWN, UNKNOWN, null, UNKNOWN); //previously null used to be UNKNOWN

          System.out.println(String.format("%.3f", currEvent.getTime()) + 
              " " + currEvent.getCustID() + " waits for "+currEvent.getCustomer().getServerID());

          if(nextEvent.getState() != UNKNOWN) {
            events[numEvents] = nextEvent;

            // Re-arrange
            events = arrangeEvents(events, numEvents);
            numEvents++;
          }
          break;
        case LEAVES:
          nextEvent = new Event(UNKNOWN, UNKNOWN, null, UNKNOWN); //previously null used to be UNKNOWN

          System.out.println(String.format("%.3f", currEvent.getTime()) + 
              " " + currEvent.getCustID() + " leaves");

          if(nextEvent.getState() != UNKNOWN) {
            events[numEvents] = nextEvent;

            // Re-arrange
            events = arrangeEvents(events, numEvents);
            numEvents++;
          }
          break;
        case DONE:
          servers.get(currEvent.getServerID()).reducePeopleServing();
          servers.get(currEvent.getServerID()).setServerBusy(false);
          if(!servers.get(currEvent.getServerID()).getCustQueue1().isQueueEmpty()) {
            Customer nextCustomer = servers.get(currEvent.getServerID()).getCustQueue1().getNextCustomer();
            nextEvent = new Event(SERVED, currEvent.getServerID(), nextCustomer, currEvent.getTime());
            //server.setCustQueue(UNKNOWN);
            servers.get(nextCustomer.getServerID()).getCustQueue1().removeFromQueue(nextCustomer);
          } else {
            nextEvent = new Event(UNKNOWN, UNKNOWN, null, UNKNOWN); //previously null used to be UNKNOWN
          }
          System.out.println(String.format("%.3f", currEvent.getTime()) + 
              " " + currEvent.getCustID() + " done with "+currEvent.getCustomer().getServerID());
          Customer tempCust = customers[currEvent.getCustID()];
          infoLog.addToTotalWait(tempCust.getCustServedTime() - tempCust.getCustArrivalTime());
          if(nextEvent.getState() != UNKNOWN) {
            events[numEvents] = nextEvent;

            // Re-arrange
            events = arrangeEvents(events, numEvents);
            numEvents++;
          }
          break;
      }
    }

    //return String.format("%.3f", logTotalWait / logServedCust) + ", " +
    //logServedCust + ", " + (logTotalCust - logServedCust);
    return String.format("%.3f", infoLog.getLogTotalWait() / infoLog.getLogServedCust()) + ", " +
    infoLog.getLogServedCust() + ", " + (infoLog.getLogTotalCust() - infoLog.getLogServedCust());
  }

  private static Event[] arrangeEvents(Event[] eventQueue, int numEvents){
    for(int i=numEvents; i>0; i--){
      if(eventQueue[i].getTime() < eventQueue[i-1].getTime() ||
      (eventQueue[i].getTime() == eventQueue[i-1].getTime() &&
      eventQueue[i].getState() >= eventQueue[i-1].getState())){
        Event tempEvent = eventQueue[i];
        eventQueue[i] = eventQueue[i-1];
        eventQueue[i-1] = tempEvent;
      }
    }
    return eventQueue;
  }

  //in increasing serverID number, the first available server
  //will be returned
  private static int findFreeServer(ArrayList<Server1> servers){
    for(int i=0; i<servers.size(); i++){
      if(!servers.get(i).getServerBusy()){
        return servers.get(i).getServerID();
      }
    }
    return -1;
  }

  //in increasing serverID number, the first server with non-full
  //queue will be returned
  private static int findNextServer(ArrayList<Server1> servers){
    for(int i=0; i< servers.size(); i++){
      if(servers.get(i).isQueueAvail()) return servers.get(i).getServerID();
    }
    return -1;
  }
}

