public class EventArrive extends Event {
  /* Constructor */
  public EventArrive(double time, IServer server, ICustomer customer) {
    super(IEvent.STATE.ARRIVES, time, server, customer);
  }


  /* Event Handler */
  public IQueue<IEvent> process(Shop shop, Log log) {
    log.logArriveEvent(this.getServer(), this.getCustomer(), this.getTime());
    IQueue<IEvent> nextEvents = new SQueue<IEvent>(IEvent.MAX_EVENTS);

    shop.serveStrategy(this.getCustomer())
        .ifPresentOrElse((server) -> nextEvents.add(new EventServe(this.getTime(), server, this.getCustomer())),
                         () -> shop.waitStrategy(this.getCustomer())
                                   .ifPresentOrElse((server) -> nextEvents.add(new EventWait(this.getTime(), server, this.getCustomer())),
                                                    () -> nextEvents.add(new EventLeave(this.getTime(), null, this.getCustomer()))));

    return nextEvents;
  }
}