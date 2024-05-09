package HotelReservationSystem.ADTs;

import HotelReservationSystem.RoomTypes.Room;

public class Stack<T> implements StackInterface<T> {
    private T[] stackArray;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = 100;
    private int entryCount;

    public Stack() {
        this(DEFAULT_CAPACITY);
    }

    public Stack(int capacity) {
        this.size = capacity;
        this.entryCount = 0;
        this.stackArray = (T[]) new Object[capacity];
    }


    public void push(T newEntry) {
        if (isFull()) {
//            make some operations;
            doubleCapacity();
        }
        stackArray[entryCount] = newEntry;
        entryCount++;
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }

//        no need to convert last element to null
        entryCount--;
        return stackArray[entryCount];
    }
    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }

        return stackArray[entryCount - 1];
    }
    public void clear() {
        this.entryCount = 0;
        this.stackArray = (T[]) new Object[size]; // actually we don't need this but
        System.out.println("Stack cleared.");
    }

    public int getEntryCount() {
        return entryCount;
    }

    public void printContent() {
//        print from top to bottom, since top is last, we need to start from last index;
        for(int i = entryCount - 1; i >= 0; i--) {
            Room currentRoom = (Room) stackArray[i];
            System.out.println("ID: " + currentRoom.getID() + ", Type: " + currentRoom.getType() + ", Is Available: " + currentRoom.getStatus());
        }
    }

    public boolean isEmpty() {
        return this.entryCount == 0;
    }
    private boolean isFull() {
        return this.entryCount == size;
    }

    private void doubleCapacity() {
        this.size *= 2;
        if(this.size > MAX_CAPACITY) {
            System.out.println("MAX CAPACITY EXCEED, OPERATION TERMINATED.");
        } else {
            T[] newArray = (T[]) new Object[this.size];
            for(int i = 0; i < this.entryCount; i++) {
                newArray[i] = stackArray[i];
            }
            this.stackArray = newArray;
        }
    }

}
