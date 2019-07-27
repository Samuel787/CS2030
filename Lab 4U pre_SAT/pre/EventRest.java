public class EventRest extends Event {
  /* Constructor */
  public EventRest(double time, IServer server, ICustomer customer) {
    super(IEvent.STATE.REST, time, server, customer);
  }


  /* Event Handler */
  public IQueue<IEvent> process(Shop shop, Log log) {
    log.logRestEvent(this.getServer(), this.getCustomer(), this.getTime());
    IQueue<IEvent> nextEvents = new SQueue<IEvent>(IEvent.MAX_EVENTS);
    
    this.getServer().rest(this.getCustomer(), this.getTime());
    nextEvents.add(new EventBack(this.getTime() + this.getServer().getRestTime(), this.getServer(), null));

    return nextEvents;
  }
}