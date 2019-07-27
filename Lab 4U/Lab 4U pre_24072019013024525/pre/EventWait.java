public class EventWait extends Event {
  /* Constructor */
  public EventWait(double time, IServer server, ICustomer customer) {
    super(IEvent.STATE.WAITS, time, server, customer);
  }


  /* Event Handler */
  public IQueue<IEvent> process(Shop shop, Log log) {
    log.logWaitEvent(this.getServer(), this.getCustomer(), this.getTime());
    IQueue<IEvent> nextEvents = new SQueue<IEvent>(IEvent.MAX_EVENTS);
    
    this.getServer().wait(this.getCustomer(), this.getTime());

    return nextEvents;
  }
}