import java.util.*;
import java.util.function.*;

public class SQueue<T extends IUnique<T>> implements IQueue<T> {
  /* Attributes */
  private int maxSize;
  private PriorityQueue<T> queue;

  

  /* Constructor */
  public SQueue(int maxSize) {
    this.maxSize = maxSize;
    this.queue = new PriorityQueue<T>();
  }


  
  /* Accessors */
  @Override public int size() {
    return this.queue.size();
  }
  @Override public int count(Predicate<T> check) {
    return Util.count(this.queue, check);
  }
  @Override public T peek() {
    assert !this.queue.isEmpty();
    return this.queue.peek();
  }

  /* Predicates */
  @Override public boolean isEmpty() {
    return this.queue.isEmpty();
  }
  @Override public boolean isFull() {
    return this.queue.size() == this.maxSize;
  }

  /* Mutators */
  @Override public void add(T elem) {
    assert !this.isFull();
    if(elem != null) {
      this.queue.offer(elem);
    }
    assert this.unique();
  }
  @Override public T get() {
    assert !this.isEmpty();
    return this.queue.poll();
  }



  /* Helper */
  private boolean unique() {
    return Util.forall(this.queue, 
                       (t1) -> (Util.forall(this.queue, 
                                            (t2) -> (t1 == t2 || t1.diff(t2)))));
  }
}