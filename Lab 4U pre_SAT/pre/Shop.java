import java.util.*;

public class Shop {
  /* Attributes */
  private IQueue<IEvent> eventQueue;
  private IServer[] allServers;
  private Log log;

  public Shop(int numOfServer, int maxCustomer, int queueSize, ServerRandomizer rand) {
    this.allServers = new IServer[numOfServer];
    this.eventQueue = new SQueue<IEvent>(IEvent.MAX_EVENTS);
    this.log = new Log();

    // Initialization
    for(int i=0; i<numOfServer; i++) {
      this.allServers[i] = new Server(i, maxCustomer, rand);
    }
  }

  
  /* Predicates */
  public boolean hasNextEvent() {
    assert this.eventQueue != null;
    return !this.eventQueue.isEmpty(); 
  }


  /* Accessors */
  public double getNextTime() {
    assert this.eventQueue != null;
    return this.eventQueue.isEmpty() ? Double.MAX_VALUE : this.eventQueue.peek().getTime(); 
  }
  public String getStatistics() {
    return log.getStatistic();
  }


  /* Event Handler */
  public void run() {
    assert this.eventQueue != null;
    assert this.eventQueue.peek() != null;
    this.eventQueue.merge(this.eventQueue.get().process(this, log));
  }
  public void run(IEvent event) {
    this.eventQueue.merge(event.process(this, log));
  }

  public Optional<IServer> serveStrategy(ICustomer customer) {
    return customer.chooseServe(this.allServers);
  }
  public Optional<IServer> waitStrategy(ICustomer customer) {
    return customer.chooseWaits(this.allServers);
  }
}