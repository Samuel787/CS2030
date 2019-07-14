import java.util.ArrayList;

public class CustQueue implements IQueue<ICustomer>{
    
    //list containing the customers in order, representing the queue
    private ArrayList<ICustomer> customers;

    //Server to which the queue belongs to
    private int serverID; /*CHECK IF U REALLY NEED THIS*/

    //Maximum length of the queue
    private int maxQueueLength;

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
            return true;
        }   
        return false;
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
}