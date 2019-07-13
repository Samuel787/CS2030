public class Log {
  private int served;
  private int total;
  private double wait;

  public Log() {
    this.served = 0;
    this.total = 0;
    this.wait = 0.0;
  }

  public void logArrives(IServer serv, ICustomer cust, double time) {
    System.out.println(String.format("%.3f", time) + " " + cust + " arrives");
    this.total++;
  }
  public void logWaits(IServer serv, ICustomer cust, double time) {
    System.out.println(String.format("%.3f", time) + " " + cust + " waits");
  }
  public void logServed(IServer serv, ICustomer cust, double time) {
    System.out.println(String.format("%.3f", time) + " " + cust + " served");
    this.served++;
  }
  public void logLeaves(IServer serv, ICustomer cust, double time) {
    System.out.println(String.format("%.3f", time) + " " + cust + " leaves");
  }
  public void logDone(IServer serv, ICustomer cust, double time) {
    System.out.println(String.format("%.3f", time) + " " + cust + " done");
    this.wait += cust.getWaitTime();
  }

  @Override
  public String toString() {
    return String.format("%.3f", this.wait / this.served) + ", " +
        this.served + ", " + (this.total - this.served);
  }
}