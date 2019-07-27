import java.util.ArrayList;
import java.util.Collections;

public class CustQueue implements IQueue<ICustomer>{
    
    //list containing the customers in order, representing the queue
    private ArrayList<ICustomer> customers;

    //Server to which the queue belongs to
    private int serverID; /*CHECK IF U REALLY NEED THIS*/

    //Maximum length of the queue
    private int maxQueueLength;

    public int getNumElderly() {
        int numElderly = 0;
        for(ICustomer customer : customers){
            if(customer instanceof ElderlyCustomer){
                numElderly++;
            }
        }
        return numElderly;
    }

    /**
     * Constructor
     * @param serverID id of the server to which the queue belongs
     * @param maxQueueLength maximum length of the queue allowed
     */

    public CustQueue(int serverID, int maxQueueLength){
        this.customers = new ArrayList<>();
        this.serverID = serverID;
        this.maxQueueLength = maxQueueLength;
    }

    /**
     * @param customer is the customer to be added to the queue
     * @return whether the customer is successfully added to queue
     */
    @Override
    public boolean insert(ICustomer customer){
        if(!this.isFull()){
            this.customers.add(customer);
            //sort the customers to prioratise the elderly
            sortCustQueue();
            return true;
        }   
        return false;
    }

    /**
     * We may not even need this function
     */
    public int getServerID(){
        return this.serverID;
    }

    /**
     * If the queue is not empty, it will return the first customer
     */
    @Override
    public ICustomer remove(){
        if(!this.isEmpty()){
            ICustomer temp = customers.get(0);
            customers.remove(0);
            return temp;
        }
        return null;
    }

    @Override
    public boolean isEmpty(){
        if(customers.size() == 0) return true;
        return false;
    }

    @Override
    public boolean isFull(){
        if(customers.size() >= this.maxQueueLength) return true;
        return false;
    }

    @Override
    public int size(){
        return this.customers.size();
    }
    
    /**
     * This method sorts the customer queue to prioritise elderly customers 
     * It then prioratises the customers based on their arrival time
     */
    private void sortCustQueue(){
        //bubble sort by time first
        Collections.sort(customers, (cust1, cust2) -> {
            if(cust1 instanceof ElderlyCustomer && cust2 instanceof GeneralCustomer){
                return -1;
            } else if(cust1 instanceof ElderlyCustomer && cust2 instanceof ElderlyCustomer){
                if(cust1.getArrivalTime() < cust2.getArrivalTime()){
                    return -1;
                } else if (cust1.getArrivalTime() == cust2.getArrivalTime()){
                    return 0;
                } else {
                    return 1;
                }
            } else if(cust1 instanceof GeneralCustomer && cust2 instanceof ElderlyCustomer){
                return 1;
            } else {
                if(cust1.getArrivalTime() < cust2.getArrivalTime()){
                    return -1;
                } else if (cust1.getArrivalTime() == cust2.getArrivalTime()){
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
}