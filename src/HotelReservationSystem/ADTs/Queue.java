package HotelReservationSystem.ADTs;

import HotelReservationSystem.Reservation;

public class Queue<T> implements QueueInterface<T> {
    private T[] queue;
    private int size;
    private int frontIndex;
    private int backIndex;
    private static final int DEFAULT_SIZE = 10;
    private static final int MAX_CAPACITY = 100;

    public Queue() {
        this(DEFAULT_SIZE);
    }

    public Queue(int size) {
        this.size = size;
        this.frontIndex = 0;
        this.backIndex = -1;
        this.queue = (T[]) new Object[size];
    }

    public void enqueue(T newEntry) {
        ensureCapacity();
        backIndex = (backIndex + 1) % size;
        queue[backIndex] = newEntry;
    }

    public T dequeue() {
        if(isEmpty()) {
            System.out.println("Queue is Empty.");
            return null;
        }
        T removingEntry = queue[frontIndex];
        queue[frontIndex] = null;
        frontIndex = (frontIndex + 1) % size;
        return removingEntry;
    }

    public T peek() {
        if(isEmpty()) {
            System.out.println("Queue is Empty.");
            return null;
        }

        return queue[frontIndex];
    }

    public boolean isEmpty() {
        return (this.backIndex + 1) % size == this.frontIndex;
    }

    public void clear() {
        queue = (T[]) new Object[size];
        frontIndex = 0;
        backIndex = -1;
    }

    private boolean isFull() {
        return (this.backIndex + 2) % size == this.frontIndex;
    }

    private void ensureCapacity() {
        if(isFull()) {
            T[] newQueue = (T[]) new Object[size * 2];
            for (int i = 0; i < size - 1; i++) {
                newQueue[i] = queue[frontIndex];
                frontIndex = (frontIndex + 1) % size;
            }
            queue = newQueue;
            backIndex = size - 2;
            this.size *= 2;
            frontIndex = 0;
        }
    }

    public void printContent() {
        for(int i = 0; i < size; i++) {
            if(queue[(frontIndex + i) % size] == null) {
                break;
            }
            Reservation currentReservation = (Reservation) queue[(frontIndex + i) % size];
            System.out.println("Reservation ID: " + currentReservation.getID() + " Customer Name: " + currentReservation.getCustomerName());
        }
    }
}
