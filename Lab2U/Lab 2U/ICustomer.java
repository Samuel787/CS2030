public interface ICustomer extends Comparable<ICustomer> {
  public int getID();
  public double getWaitTime();
  public double getArrivalTime();
  public void doServed(double time);
  // doArrives, doLeaves, doDone, doWait
  // either involves Server or do nothing
}