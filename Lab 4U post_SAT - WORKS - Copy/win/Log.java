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
    /*
    if(cust instanceof GeneralCustomer){
      System.out.println(String.format("%.3f", time) + " General Customer: " + cust.getCustID() + " arrives");  
    } else {
      System.out.println(String.format("%.3f", time) + " Elderly Customer: " + cust.getCustID() + " arrives");
    }
    */
    System.out.println(String.format("%.3f", time) + " " + cust.getCustID() + " arrives");
    this.total++;
  }
  public void logWaits(IServer serv, ICustomer cust, double time) {
    System.out.println(String.format("%.3f", time) + " " + cust.getCustID() + " waits for " + serv.getID());
  }
  public void logServed(IServer serv, ICustomer cust, double time) {
    System.out.println(String.format("%.3f", time) + " " + cust.getCustID() + " served by " + serv.getID());
    this.served++;
  }
  public void logLeaves(IServer serv, ICustomer cust, double time) {
    System.out.println(String.format("%.3f", time) + " " + cust.getCustID() + " leaves");
  }
  public void logDone(IServer serv, ICustomer cust, double time) {
    System.out.println(String.format("%.3f", time) + " " + cust.getCustID() + " done with " + serv.getID());
    this.wait += cust.getWaitTime();
  }
  public void logRest(IServer serv, ICustomer cust, double time){
    System.out.println(String.format("%.3f", time) + " " + serv.getID() + " is resting");
  }

  public void logBack(IServer serv, ICustomer cust, double time){
    System.out.println(String.format("%.3f", time) + " " + serv.getID() + " is back");
  }

  @Override
  public String toString() {
    return String.format("%.3f", this.wait / this.served) + ", " +
        this.served + ", " + (this.total - this.served);
  }


}