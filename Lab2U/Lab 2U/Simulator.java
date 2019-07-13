public class Simulator {
  public static final int MAX_EVENTS = 1100; // 1000 + 100

  public static String simulate(int[] custIDs, double[] custArriveTime, int numArrival, int servMaxCust) {
    /** Local Variables */
    IServer server = null;
    IQueue<IEvent> eventQueue = null;
    Log log = null;

    /** Initialization  */
    // server = new Server(0, servMaxCust);
    // eventQueue = new SortedQueue<IEvent>(Simulator.MAX_EVENTS);
    log = new Log();
    for(int i=0; i<numArrival; i++) {
      eventQueue.insert(new Event(State.ARRIVES, custArriveTime[i], null, new Customer(i, custArriveTime[i])));
    }

    // RUN
    while(!eventQueue.isEmpty()) {
      IEvent next = eventQueue.remove();

      switch(next.getState()) {
        case ARRIVES:
          eventQueue.insert(arriveEvt(next.getServer(), next.getCustomer(), next.getTime(), server, log));
          break;
        case WAITS:
          eventQueue.insert(waitEvt(next.getServer(), next.getCustomer(), next.getTime(), server, log));
          break;
        case SERVED:
          eventQueue.insert(servedEvt(next.getServer(), next.getCustomer(), next.getTime(), server, log));
          break;
        case LEAVES:
          eventQueue.insert(leavesEvt(next.getServer(), next.getCustomer(), next.getTime(), server, log));
          break;
        case DONE:
          eventQueue.insert(doneEvt(next.getServer(), next.getCustomer(), next.getTime(), server, log));
          break;
      }
    }
    
    return log.toString();
  }

  /** Event Handler */
  private static IEvent arriveEvt(IServer serv, ICustomer cust, double time, IServer allServer, Log log) {
    log.logArrives(serv, cust, time);
    if(!allServer.isBusy()) {
      return new Event(State.SERVED, time, allServer, cust);
    } else if(allServer.isQueueFull()) {
      return new Event(State.LEAVES, time, null, cust);
    } else if(allServer.isQueueEmpty()) {
      return new Event(State.WAITS, time, allServer, cust);
    } else {
      return null; // should not come to this
    }
  }

  private static IEvent waitEvt(IServer serv, ICustomer cust, double time, IServer allServer, Log log) {
    log.logWaits(serv, cust, time);
    serv.doWaits(cust, time);
    return null;
  }

  private static IEvent servedEvt(IServer serv, ICustomer cust, double time, IServer allServer, Log log) {
    log.logServed(serv, cust, time);
    serv.doServed(cust, time);
    return new Event(State.DONE, serv.getDoneTime(), serv, cust);
  }
  
  private static IEvent leavesEvt(IServer serv, ICustomer cust, double time, IServer allServer, Log log) {
    log.logLeaves(serv, cust, time);
    return null;
  }

  private static IEvent doneEvt(IServer serv, ICustomer cust, double time, IServer allServer, Log log) {
    log.logDone(serv, cust, time);
    serv.doDone(cust, time);
    if(!serv.isQueueEmpty()) {
      return new Event(State.SERVED, time, serv, serv.getFrontCustomer());
    } else {
      return null;
    }}
  /******************/
}