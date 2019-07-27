public class EventBack extends Event {
  /* Constructor */
  public EventBack(double time, IServer server, ICustomer customer) {
    super(IEvent.STATE.BACK, time, server, customer);
  }


  /* Event Handler */
  public IQueue<IEvent> process(Shop shop, Log log) {
    log.logBackEvent(this.getServer(), this.getCustomer(), this.getTime());
    IQueue<IEvent> nextEvents = new SQueue<IEvent>(IEvent.MAX_EVENTS);
    
    this.getServer().back(this.getCustomer(), this.getTime());
    this.getServer().canServeNext()
        .ifPresent((server) -> nextEvents.add(new EventServe(this.getTime(), server, server.getFrontCustomer())));

    return nextEvents;
  }
}