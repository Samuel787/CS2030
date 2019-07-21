import java.util.ArrayList;
public interface IEvent extends Comparable<IEvent> {
  public double getTime();
  public IServer getServer();
  public ICustomer getCustomer();
  public int getStateID();
  public ArrayList<IEvent> getNextEvent();

  @Override
  default int compareTo(IEvent event) {
    if(this.getTime() < event.getTime()){
      return -1; //this is prioritized
    } else if(this.getTime() == event.getTime() && this.getStateID() >= event.getStateID()){
      return -1;
    } else {
      return 0;
    }
  }
}   