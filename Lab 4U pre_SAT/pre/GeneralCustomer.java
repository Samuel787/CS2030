import java.util.*;

public class GeneralCustomer extends Customer {
  /* Attributes */
  private double rearrivalDuration;

  public GeneralCustomer(int id, double arriveTime, double rearrivalDuration) {
    super(id, arriveTime);
    this.rearrivalDuration = rearrivalDuration;
  }

  @Override public Optional<ICustomer> leaving() {
    return Optional.of(new GeneralCustomer(this.getID(), this.getArrivalTime() + this.rearrivalDuration, this.rearrivalDuration));
  }

  @Override public Optional<IServer> chooseWaits(IServer[] allServers) {
    return Arrays.stream(allServers)
                 .filter((s1) -> !s1.isQueueFull())
                 .min((server1,server2) -> {
                    int comparison1 = server1.countQueue((customer1) -> (true))
                                    - server2.countQueue((customer2) -> (true));
                    int comparison2 = server1.countQueue((customer1) -> (customer1 instanceof ElderlyCustomer))
                                    - server2.countQueue((customer2) -> (customer2 instanceof ElderlyCustomer));
                    int comparison3 = server1.compareTo(server2);
                    
                    return comparison1 != 0 ? comparison1 : comparison2 != 0 ? comparison2 : comparison3;
                });
  }
}