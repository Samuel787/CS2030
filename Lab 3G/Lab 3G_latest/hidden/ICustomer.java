public interface ICustomer /*extends Comparable<ICustomer>*/ {
  public int getCustID();
  public double getWaitTime();
  public double getArrivalTime();
  public void setArrivalTime(double arrivalTime);
  public void setServedTime(double time);
  public IServer getServer();
  public void setServer(IServer server);
  //public void doServed(double time);
  // doArrives, doLeaves, doDone, doWait
  // either involves Server or do nothing
}