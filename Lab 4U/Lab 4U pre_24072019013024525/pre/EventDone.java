public class EventDone extends Event {
  /* Constructor */
  public EventDone(double time, IServer server, ICustomer customer) {
    super(IEvent.STATE.DONE, time, server, customer);
  }


  /* Event Handler */
  public IQueue<IEvent> process(Shop shop, Log log) {
    log.logDoneEvent(this.getServer(), this.getCustomer(), this.getTime());
    IQueue<IEvent> nextEvents = new SQueue<IEvent>(IEvent.MAX_EVENTS);
    
    this.getServer().done(this.getCustomer(), this.getTime())
                    .ifPresentOrElse(
                      (server) -> server.isServing()
                                        .ifPresent((currServer) -> {
                                          currServer.setRestTime();
                                          nextEvents.add(new EventRest(this.getTime(), this.getServer(), null));
                                        }),
                      () -> this.getServer().canServeNext().ifPresent((server) -> nextEvents.add(new EventServe(this.getTime(), server, server.getFrontCustomer())))
                    );

    return nextEvents;
  }
}