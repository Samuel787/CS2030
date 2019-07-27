/* Logger: print and maintain statistics */
public class Log {
  /* Constants */
  private static final int UNUSED = -1;

  /* Attributes */
  private int customerServed;
  private int customerTotal;
  private double totalWaitTime;

  /* Constructor */
  public Log() {
    this.customerServed = 0;
    this.customerTotal = 0;
    this.totalWaitTime = 0.0;
  }

  /* Logging Procedures */
  public void logArriveEvent(IServer server, ICustomer customer, double currentTime) {
    this.logCustomer(Log.UNUSED, customer.getID(), currentTime, "arrives");
    this.customerTotal++;
  }
  public void logWaitEvent(IServer server, ICustomer customer, double currentTime) {
    this.logCustomerServer(server.getID(), customer.getID(), currentTime, "waits for");
  }
  public void logServeEvent(IServer server, ICustomer customer, double currentTime) {
    this.logCustomerServer(server.getID(), customer.getID(), currentTime, "served by");
    this.customerServed++;
  }
  public void logLeaveEvent(IServer server, ICustomer customer, double currentTime) {
    this.logCustomer(Log.UNUSED, customer.getID(), currentTime, "leaves");
  }
  public void logDoneEvent(IServer server, ICustomer customer, double currentTime) {
    this.logCustomerServer(server.getID(), customer.getID(), currentTime, "done with");
    this.totalWaitTime += customer.getWaitTime();
  }
  public void logRestEvent(IServer server, ICustomer customer, double currentTime) {
    this.logServer(server.getID(), Log.UNUSED, currentTime, "is resting");
  }
  public void logBackEvent(IServer server, ICustomer customer, double currentTime) {
    this.logServer(server.getID(), Log.UNUSED, currentTime, "is back");
  }



  public String getStatistic() {
    return Util.str(this.totalWaitTime / this.customerServed) + ", " + this.customerServed + ", " + (this.customerTotal - this.customerServed);
  }



  /* Helper Procedures */
  private void logCustomer(int serverID, int customerID, double currentTime, String event) {
    assert serverID == Log.UNUSED;
    assert customerID >= 0;
    assert currentTime >= 0.0;
    assert event != null && !event.equals("");

    System.out.println(Util.str(currentTime) + " " + customerID + " " + event);
  }
  private void logServer(int serverID, int customerID, double currentTime, String event) {
    assert serverID >= 0;
    assert customerID == Log.UNUSED;
    assert currentTime >= 0.0;
    assert event != null && !event.equals("");

    System.out.println(Util.str(currentTime) + " " + serverID + " " + event);
  }
  private void logCustomerServer(int serverID, int customerID, double currentTime, String event) {
    assert serverID >= 0;
    assert customerID >= 0;
    assert currentTime >= 0.0;
    assert event != null && !event.equals("");

    System.out.println(Util.str(currentTime) + " " + customerID + " " + event + " " + serverID);
  }
}