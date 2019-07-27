public interface IEvent extends IUnique<IEvent>, Comparable<IEvent> {
  /* Constants */
  public static enum STATE { ARRIVES, WAITS, SERVED, LEAVES, DONE, REST, BACK; }
  public static final int MAX_EVENTS = 100;

  /* Accessors */
  public IEvent.STATE getState();
  public double getTime();
  public IServer getServer();
  public ICustomer getCustomer();

  /* Mutators */
  public IQueue<IEvent> process(Shop shop, Log log);
}