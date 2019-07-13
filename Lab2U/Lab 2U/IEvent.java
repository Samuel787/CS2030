public interface IEvent extends Comparable<IEvent> {
  public State getState();
  public double getTime();
  public IServer getServer();
  public ICustomer getCustomer();
}