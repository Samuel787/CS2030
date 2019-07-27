public class EventLeave extends Event {
  /* Constructor */
  public EventLeave(double time, IServer server, ICustomer customer) {
    super(IEvent.STATE.LEAVES, time, server, customer);
  }


  /* Event Handler */
  public IQueue<IEvent> process(Shop shop, Log log) {
    log.logLeaveEvent(this.getServer(), this.getCustomer(), this.getTime());
    IQueue<IEvent> nextEvents = new SQueue<IEvent>(IEvent.MAX_EVENTS);
    
    this.getCustomer().leaving()
        .ifPresent((customer) -> nextEvents.add(new EventArrive(customer.getArrivalTime(), null, customer)));

    return nextEvents;
  }
}