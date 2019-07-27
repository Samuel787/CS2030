import java.util.function.*;

public interface IQueue<T> {
  /* Accessors */
  public int size();
  public int count(Predicate<T> check);
  public T peek();

  /* Predicates */
  public boolean isEmpty();
  public boolean isFull();

  /* Mutators */
  public void add(T elem);
  default public void merge(IQueue<T> list) {
    while(!list.isEmpty()) {
      this.add(list.get());
    }
  }
  public T get();
}