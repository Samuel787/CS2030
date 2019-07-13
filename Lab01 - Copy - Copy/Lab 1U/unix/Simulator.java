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
    int serverID = 0;
    boolean serverBusy = false;
    int custQueue = UNKNOWN;
    double serverNext = 0.0;

    // Customers
    double[] custServedTime = new double[MAX_EVENTS];

    // Events
    int[] eventState = new int[MAX_EVENTS];
    int[] eventServerID = new int[MAX_EVENTS];
    int[] eventCustID = new int[MAX_EVENTS];
    double[] eventTime = new double[MAX_EVENTS];
    int numEvents = 0;

    // Log
    int logServedCust = 0;
    int logTotalCust = 0;
    double logTotalWait = 0.0;

    /** Process */
    // Initialize
    for(numEvents=0; numEvents<numArrival; numEvents++) {
      custServedTime[numEvents] = 0.0;

      eventState[numEvents] = ARRIVES;
      eventTime[numEvents] = custArriveTime[numEvents];
      eventServerID[numEvents] = UNKNOWN;
      eventCustID[numEvents] = custIDs[numEvents];
    }

    // Run
    int currState;
    int currServerID;
    int currCustID;
    double currTime;

    int nextState;
    int nextServerID;
    int nextCustID;
    double nextTime;

    while(numEvents > 0) {
      currState = eventState[0];
      currTime = eventTime[0];
      currServerID = eventServerID[0];
      currCustID = eventCustID[0];

      for(int i=1; i<numEvents; i++) {
        eventState[i-1] = eventState[i];
        eventTime[i-1] = eventTime[i];
        eventServerID[i-1] = eventServerID[i];
        eventCustID[i-1] = eventCustID[i];
      }
      numEvents--;

      switch(currState) {
        case ARRIVES:
          if(!serverBusy) {
            nextState = SERVED;
            nextTime = currTime;
            nextServerID = serverID;
            nextCustID = currCustID;
          } else if(custQueue != UNKNOWN) {
            nextState = LEAVES;
            nextTime = currTime;
            nextServerID = serverID;
            nextCustID = currCustID;
          } else {
            nextState = WAITS;
            nextTime = currTime;
            nextServerID = serverID;
            nextCustID = currCustID;
          }

          System.out.println(String.format("%.3f", currTime) + 
              " " + currCustID + " arrives");
          logTotalCust++;

          if(nextState != UNKNOWN) {
            eventState[numEvents] = nextState;
            eventTime[numEvents] = nextTime;
            eventServerID[numEvents] = nextServerID;
            eventCustID[numEvents] = nextCustID;

            // Re-arrange
            for(int i=numEvents; i>0; i--) {
              if(eventTime[i] < eventTime[i-1] ||
                  (eventTime[i] == eventTime[i-1] &&
                   eventState[i] >= eventState[i-1])) {
                int tempState = eventState[i];
                eventState[i] = eventState[i-1];
                eventState[i-1] = tempState;
                
                double tempTime = eventTime[i];
                eventTime[i] = eventTime[i-1];
                eventTime[i-1] = tempTime;
                
                int tempServerID = eventServerID[i];
                eventServerID[i] = eventServerID[i-1];
                eventServerID[i-1] = tempServerID;
                
                int tempCustID = eventCustID[i];
                eventCustID[i] = eventCustID[i-1];
                eventCustID[i-1] = tempCustID;
              }
            }
            numEvents++;
          }
          break;
        case SERVED:
          serverBusy = true;
          serverNext = currTime + SERVICE_TIME;

          nextState = DONE;
          nextTime = serverNext;
          nextServerID = currServerID;
          nextCustID = currCustID;

          custServedTime[currCustID] = currTime;

          System.out.println(String.format("%.3f", currTime) + 
              " " + currCustID + " served");
          logServedCust++;

          if(nextState != UNKNOWN) {
            eventState[numEvents] = nextState;
            eventTime[numEvents] = nextTime;
            eventServerID[numEvents] = nextServerID;
            eventCustID[numEvents] = nextCustID;

            // Re-arrange
            for(int i=numEvents; i>0; i--) {
              if(eventTime[i] < eventTime[i-1] ||
                  (eventTime[i] == eventTime[i-1] &&
                  eventState[i] >= eventState[i-1])) {
                int tempState = eventState[i];
                eventState[i] = eventState[i-1];
                eventState[i-1] = tempState;
                
                double tempTime = eventTime[i];
                eventTime[i] = eventTime[i-1];
                eventTime[i-1] = tempTime;
                
                int tempServerID = eventServerID[i];
                eventServerID[i] = eventServerID[i-1];
                eventServerID[i-1] = tempServerID;
                
                int tempCustID = eventCustID[i];
                eventCustID[i] = eventCustID[i-1];
                eventCustID[i-1] = tempCustID;
              }
            }
            numEvents++;
          }
          break;
        case WAITS:
          custQueue = currCustID;

          nextState = UNKNOWN;
          nextTime = UNKNOWN;
          nextServerID = UNKNOWN;
          nextCustID = UNKNOWN;

          System.out.println(String.format("%.3f", currTime) + 
              " " + currCustID + " waits");

          if(nextState != UNKNOWN) {
            eventState[numEvents] = nextState;
            eventTime[numEvents] = nextTime;
            eventServerID[numEvents] = nextServerID;
            eventCustID[numEvents] = nextCustID;

            // Re-arrange
            for(int i=numEvents; i>0; i--) {
              if(eventTime[i] < eventTime[i-1] ||
                  (eventTime[i] == eventTime[i-1] &&
                  eventState[i] >= eventState[i-1])) {
                int tempState = eventState[i];
                eventState[i] = eventState[i-1];
                eventState[i-1] = tempState;
                
                double tempTime = eventTime[i];
                eventTime[i] = eventTime[i-1];
                eventTime[i-1] = tempTime;
                
                int tempServerID = eventServerID[i];
                eventServerID[i] = eventServerID[i-1];
                eventServerID[i-1] = tempServerID;
                
                int tempCustID = eventCustID[i];
                eventCustID[i] = eventCustID[i-1];
                eventCustID[i-1] = tempCustID;
              }
            }
            numEvents++;
          }
          break;
        case LEAVES:
          nextState = UNKNOWN;
          nextTime = UNKNOWN;
          nextServerID = UNKNOWN;
          nextCustID = UNKNOWN;

          System.out.println(String.format("%.3f", currTime) + 
              " " + currCustID + " leaves");

          if(nextState != UNKNOWN) {
            eventState[numEvents] = nextState;
            eventTime[numEvents] = nextTime;
            eventServerID[numEvents] = nextServerID;
            eventCustID[numEvents] = nextCustID;

            // Re-arrange
            for(int i=numEvents; i>0; i--) {
              if(eventTime[i] < eventTime[i-1] ||
                  (eventTime[i] == eventTime[i-1] &&
                  eventState[i] >= eventState[i-1])) {
                int tempState = eventState[i];
                eventState[i] = eventState[i-1];
                eventState[i-1] = tempState;
                
                double tempTime = eventTime[i];
                eventTime[i] = eventTime[i-1];
                eventTime[i-1] = tempTime;
                
                int tempServerID = eventServerID[i];
                eventServerID[i] = eventServerID[i-1];
                eventServerID[i-1] = tempServerID;
                
                int tempCustID = eventCustID[i];
                eventCustID[i] = eventCustID[i-1];
                eventCustID[i-1] = tempCustID;
              }
            }
            numEvents++;
          }
          break;
        case DONE:
          serverBusy = false;
          if(custQueue != UNKNOWN) {
            nextState = SERVED;
            nextTime = currTime;
            nextServerID = currServerID;
            nextCustID = custQueue;
            
            custQueue = UNKNOWN;
          } else {
            nextState = UNKNOWN;
            nextTime = UNKNOWN;
            nextServerID = UNKNOWN;
            nextCustID = UNKNOWN;
          }

          System.out.println(String.format("%.3f", currTime) + 
              " " + currCustID + " done");
          logTotalWait += (custServedTime[currCustID] - 
                           custArriveTime[currCustID]);

          if(nextState != UNKNOWN) {
            eventState[numEvents] = nextState;
            eventTime[numEvents] = nextTime;
            eventServerID[numEvents] = nextServerID;
            eventCustID[numEvents] = nextCustID;

            // Re-arrange
            for(int i=numEvents; i>0; i--) {
              if(eventTime[i] < eventTime[i-1] ||
                  (eventTime[i] == eventTime[i-1] &&
                  eventState[i] >= eventState[i-1])) {
                int tempState = eventState[i];
                eventState[i] = eventState[i-1];
                eventState[i-1] = tempState;
                
                double tempTime = eventTime[i];
                eventTime[i] = eventTime[i-1];
                eventTime[i-1] = tempTime;
                
                int tempServerID = eventServerID[i];
                eventServerID[i] = eventServerID[i-1];
                eventServerID[i-1] = tempServerID;
                
                int tempCustID = eventCustID[i];
                eventCustID[i] = eventCustID[i-1];
                eventCustID[i-1] = tempCustID;
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