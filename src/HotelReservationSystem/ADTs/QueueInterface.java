package HotelReservationSystem.ADTs;
public interface QueueInterface<T> {
    public void enqueue(T newEntry);
    public T dequeue();
    public T peek();
    public boolean isEmpty();
    public void clear();
    public void printContent();
}
