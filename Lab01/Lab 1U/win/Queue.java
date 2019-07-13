import java.util.*;

public abstract class Queue{

    private ArrayList<Customer> custQueue = new ArrayList<>();

    public boolean addToQueue(Customer c){
        custQueue.add(c);
        return true;
    }

    public boolean removeFromQueue(Customer c){
        for(int i = 0; i < custQueue.size(); i++){
            if(custQueue.get(i).getCustID() == c.getCustID()){
                custQueue.remove(i);
                return true;
            }
        }
        return false;
    }
    //returns the current length of the queue
    public int getQueueLength(){
        return this.custQueue.size();
    }
}