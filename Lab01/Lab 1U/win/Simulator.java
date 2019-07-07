public class Simulator {
  // Constants
  public static final double SERVICE_TIME = 1.0;
  public static final int MAX_EVENTS = 1100; // 1000 + 100
  public static final int UNKNOWN = -1; // Multi-purpose
  
  // States
  public static final int ARRIVES = 0; // Customer arrives
  public static final int WAITS   = 1; // Customer waiting
  public static final int SERVED  = 2; // Customer served
  public static final int LEAVES  = 3; // Customer leaves
  public static final int DONE    = 4; // Customer done served
  
  public static String simulate(int[] custIDs, double[] custArriveTime, int numArrival) {
    /** Variables */
    // Server
    Server server = new Server(0, false, UNKNOWN, 0.0);

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
      customers[numEvents] = new Customer(numEvents, custArriveTime[numEvents], 0.0);
      //customers[numEvents].setCustServedTime(0.0);;
      //customers[numEvents].setCustArrivalTime(custArriveTime[numEvents]);
      //custServedTime[numEvents] = 0.0;
      events[numEvents] = new Event(ARRIVES, UNKNOWN, custIDs[numEvents], customers[numEvents].getCustArrivalTime());
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
          if(!server.getServerBusy()) {
            //server is not busy, serve:
            nextEvent = currEvent;
            nextEvent.setState(SERVED);
          } else if(server.getCustQueue() != UNKNOWN) {
            //queue is full, so leave
            nextEvent = currEvent;
            nextEvent.setState(LEAVES);
          } else {
            //queue got space, so wait in queue
            nextEvent = currEvent;
            nextEvent.setState(WAITS);
          }

          System.out.println(String.format("%.3f", currEvent.getTime()) + 
              " " + currEvent.getCustID() + " arrives");
          //logTotalCust++;
          infoLog.incrementTotalCust();

          if(nextEvent.getState() != UNKNOWN) {
            events[numEvents] = nextEvent;
            // Re-arrange
            /*
            for(int i=numEvents; i>0; i--) {
              if(events[i].getTime() < events[i-1].getTime() ||
                (events[i].getTime() == events[i-1].getTime() &&
                  events[i].getState() >= events[i].getState())){
                //swap events
                Event tempEvent = events[i];
                events[i] = events[i-1];
                events[i-1] = tempEvent;
              }
            }*/
            events = shuffleEvents(events, numEvents);
            numEvents++;
          }
          break;
        case SERVED:
          //update server
          server.setServerBusy(true);
          server.setServerNext(currEvent.getTime() + SERVICE_TIME);

          nextEvent = new Event(DONE, currEvent.getServerID(), currEvent.getCustID(), server.getServerNext());

          //custServedTime[currEvent.getCustID()] = currEvent.getTime();
          customers[currEvent.getCustID()].setCustServedTime(currEvent.getTime());
          System.out.println(String.format("%.3f", currEvent.getTime()) + 
              " " + currEvent.getCustID() + " served");
          //logServedCust++;
          infoLog.incrementServedCust();

          if(nextEvent.getState() != UNKNOWN) {
            events[numEvents] = nextEvent;
            // Re-arrange
            /*
            for(int i=numEvents; i>0; i--) {
              if(events[i].getTime() < events[i-1].getTime()||
                (events[i].getTime() == events[i-1].getTime() &&
                  events[i].getState() >= events[i-1].getState())){
                //swap
                Event tempEvent = events[i];
                events[i] = events[i-1];
                events[i-1] = tempEvent;
              }
            }*/
            events = shuffleEvents(events, numEvents);
            numEvents++;
          }
          break;
        case WAITS:
          server.setCustQueue(currEvent.getCustID());
          nextEvent = new Event(UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN);

          System.out.println(String.format("%.3f", currEvent.getTime()) + 
              " " + currEvent.getCustID() + " waits");

          if(nextEvent.getState() != UNKNOWN) {
            events[numEvents] = nextEvent;

            // Re-arrange
            /*
            for(int i=numEvents; i>0; i--) {
              if(events[i].getTime() < events[i-1].getTime() ||
                (events[i].getTime() == events[i-1].getTime() &&
                  events[i].getState() > events[i-1].getState())){
                //swap
                Event tempEvent = events[i];
                events[i] = events[i-1];
                events[i-1] = tempEvent;
              }
            }*/
            events = shuffleEvents(events, numEvents);
            numEvents++;
          }
          break;
        case LEAVES:
          nextEvent = new Event(UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN);

          System.out.println(String.format("%.3f", currEvent.getTime()) + 
              " " + currEvent.getCustID() + " leaves");

          if(nextEvent.getState() != UNKNOWN) {
            events[numEvents] = nextEvent;

            // Re-arrange
            /*
            for(int i=numEvents; i>0; i--) {
              if(events[i].getTime() < events[i-1].getTime() ||
                (events[i].getTime() == events[i-1].getTime() &&
                  events[i].getState() >= events[i-1].getState())){

                  Event tempEvent = events[i];
                  events[i] = events[i-1];
                  events[i-1] = tempEvent;
              }
            }*/
            events = shuffleEvents(events, numEvents);
            numEvents++;
          }
          break;
        case DONE:
          server.setServerBusy(false);
          if(server.getCustQueue() != UNKNOWN) {
            nextEvent = new Event(SERVED, currEvent.getServerID(), server.getCustQueue(), currEvent.getTime());
            server.setCustQueue(UNKNOWN);
          } else {
            nextEvent = new Event(UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN);
          }

          System.out.println(String.format("%.3f", currEvent.getTime()) + 
              " " + currEvent.getCustID() + " done");
          //logTotalWait += (custServedTime[currEvent.getCustID()] - 
            //               custArriveTime[currEvent.getCustID()]);
          Customer tempCust = customers[currEvent.getCustID()];
          //logTotalWait += tempCust.getCustServedTime() - tempCust.getCustArrivalTime();
          infoLog.addToTotalWait(tempCust.getCustServedTime() - tempCust.getCustArrivalTime());
          if(nextEvent.getState() != UNKNOWN) {
            events[numEvents] = nextEvent;

            // Re-arrange
            /*
            for(int i=numEvents; i>0; i--) {
              if(events[i].getTime() < events[i-1].getTime() ||
                (events[i].getTime() == events[i-1].getTime() &&
                  events[i].getState() >= events[i-1].getState())){
                Event tempEvent = events[i];
                events[i] = events[i-1];
                events[i-1] = tempEvent;
              }
            }
            */
            events = shuffleEvents(events, numEvents);
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

  private static Event[] shuffleEvents(Event[] eventQueue, int numEvents){
    for(int i=numEvents; i>0; i--){
      if(eventQueue[i].getTime() < eventQueue[i-1].getTime() ||
      eventQueue[i].getTime() == eventQueue[i-1].getTime() &&
      eventQueue[i].getState() >= eventQueue[i-1].getState()){
        Event tempEvent = eventQueue[i];
        eventQueue[i] = eventQueue[i-1];
        eventQueue[i-1] = tempEvent;
      }
    }
    return eventQueue;
  }
}

