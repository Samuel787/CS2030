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
    double[] custServedTime = new double[MAX_EVENTS];

    
    // Events
    Event[] events = new Event[MAX_EVENTS];

    int numEvents = 0;

    // Log
    int logServedCust = 0;
    int logTotalCust = 0;
    double logTotalWait = 0.0;

    /** Process */
    // Initialize
    for(numEvents=0; numEvents<numArrival; numEvents++) {
      custServedTime[numEvents] = 0.0;
      events[numEvents] = new Event(ARRIVES, UNKNOWN, custIDs[numEvents], custArriveTime[numEvents]);
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
            nextEvent = currEvent;
            nextEvent.setState(SERVED);
          } else if(server.getCustQueue() != UNKNOWN) {
            nextEvent = currEvent;
            nextEvent.setState(LEAVES);
          } else {
            nextEvent = currEvent;
            nextEvent.setState(WAITS);
          }

          System.out.println(String.format("%.3f", currEvent.getTime()) + 
              " " + currEvent.getCustID() + " arrives");
          logTotalCust++;

          if(nextEvent.getState() != UNKNOWN) {
            events[numEvents] = nextEvent;
            // Re-arrange
            for(int i=numEvents; i>0; i--) {
              if(events[i].getTime() < events[i-1].getTime() ||
                (events[i].getTime() == events[i-1].getTime() &&
                  events[i].getState() >= events[i].getState())){
                //swap events
                Event tempEvent = events[i];
                events[i] = events[i-1];
                events[i-1] = tempEvent;
              }
            }
            numEvents++;
          }
          break;
        case SERVED:
          //update server
          server.setServerBusy(true);
          server.setServerNext(currEvent.getTime() + SERVICE_TIME);

          nextEvent = new Event(DONE, currEvent.getServerID(), currEvent.getCustID(), server.getServerNext());

          custServedTime[currEvent.getCustID()] = currEvent.getTime();

          System.out.println(String.format("%.3f", currEvent.getTime()) + 
              " " + currEvent.getCustID() + " served");
          logServedCust++;

          if(nextEvent.getState() != UNKNOWN) {
            events[numEvents] = nextEvent;
            // Re-arrange
            for(int i=numEvents; i>0; i--) {
              if(events[i].getTime() < events[i-1].getTime()||
                (events[i].getTime() == events[i-1].getTime() &&
                  events[i].getState() >= events[i-1].getState())){
                //swap
                Event tempEvent = events[i];
                events[i] = events[i-1];
                events[i-1] = tempEvent;
              }
            }
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
            for(int i=numEvents; i>0; i--) {
              if(events[i].getTime() < events[i-1].getTime() ||
                (events[i].getTime() == events[i-1].getTime() &&
                  events[i].getState() > events[i-1].getState())){
                //swap
                Event tempEvent = events[i];
                events[i] = events[i-1];
                events[i-1] = tempEvent;
              }
            }
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
            for(int i=numEvents; i>0; i--) {
              if(events[i].getTime() < events[i-1].getTime() ||
                (events[i].getTime() == events[i-1].getTime() &&
                  events[i].getState() >= events[i-1].getState())){

                  Event tempEvent = events[i];
                  events[i] = events[i-1];
                  events[i-1] = tempEvent;
              }
            }
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
          logTotalWait += (custServedTime[currEvent.getCustID()] - 
                           custArriveTime[currEvent.getCustID()]);

          if(nextEvent.getState() != UNKNOWN) {
            events[numEvents] = nextEvent;

            // Re-arrange
            for(int i=numEvents; i>0; i--) {
              if(events[i].getTime() < events[i-1].getTime() ||
                (events[i].getTime() == events[i-1].getTime() &&
                  events[i].getState() >= events[i-1].getState())){
                Event tempEvent = events[i];
                events[i] = events[i-1];
                events[i-1] = tempEvent;
              }
            }
            numEvents++;
          }
          break;
      }
    }

    return String.format("%.3f", logTotalWait / logServedCust) + ", " +
    logServedCust + ", " + (logTotalCust - logServedCust);
  }
}