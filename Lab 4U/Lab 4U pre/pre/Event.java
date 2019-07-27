public abstract class Event implements IEvent {
  /* Attributes */
  private final IEvent.STATE state;
  private final double time;
  private final IServer server;
  private final ICustomer customer;

  

  /* Constructor */
  public Event(IEvent.STATE state, double time, IServer server, ICustomer customer) {
    this.state = state;
    this.time = time;
    this.server = server;
    this.customer = customer;
  }

  

  /* Accessors */
  @Override public IEvent.STATE getState() { return this.state;    }
  @Override public double getTime()        { return this.time;     }
  @Override public IServer getServer()     { return this.server;   }
  @Override public ICustomer getCustomer() { return this.customer; }



  /* IUnique */
  @Override public boolean diff(IEvent event) {
    return !this.getState().equals(event.getState()) || this.getTime() != event.getTime();
  }



  /* Comparable */
  @Override public int compareTo(IEvent event) {
    int doubleCompare = Double.compare(this.getTime(), event.getTime());
    int stateCompare = this.getState().compareTo(event.getState());
    return doubleCompare != 0 ? doubleCompare : stateCompare;
  }
}