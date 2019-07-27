public interface IQueue<T> {
  public boolean insert(T elem);
  public T remove();
  public boolean isEmpty();
  public boolean isFull();
  public int size();  
}