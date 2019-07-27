public class EventServe extends Event {
  /* Constructor */
  public EventServe(double time, IServer server, ICustomer customer) {
    super(IEvent.STATE.SERVED, time, server, customer);
  }


  /* Event Handler */
  public IQueue<IEvent> process(Shop shop, Log log) {
    log.logServeEvent(this.getServer(), this.getCustomer(), this.getTime());
    IQueue<IEvent> nextEvents = new SQueue<IEvent>(IEvent.MAX_EVENTS);
    
    this.getServer().serve(this.getCustomer(), this.getTime());
    nextEvents.add(new EventDone(this.getTime() + IServer.SERVE_TIME, this.getServer(), this.getCustomer()));

    return nextEvents;
  }
}