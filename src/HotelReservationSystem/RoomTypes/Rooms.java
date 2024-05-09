package HotelReservationSystem.RoomTypes;

import HotelReservationSystem.ADTs.ArrayList;
import HotelReservationSystem.ADTs.ListInterface;
import HotelReservationSystem.ADTs.Stack;
import HotelReservationSystem.ADTs.StackInterface;
import HotelReservationSystem.ADTs.Queue;
import HotelReservationSystem.ADTs.QueueInterface;
import HotelReservationSystem.Reservation;

public class Rooms {
    private StackInterface<Room> rooms;
    private ListInterface<Room> bookedRooms; // this can be queue or stack idk i can change it;
    private QueueInterface<Reservation> waitingLine;
    private int roomCount;

    public Rooms(int roomCount) {
        this.roomCount = roomCount;
        rooms = new Stack<Room>(roomCount);
        bookedRooms = new ArrayList<Room>(roomCount);
        waitingLine = new Queue<Reservation>(roomCount);
    }

    public void addRoom(Room newRoom) {
        rooms.push(newRoom);
    }

    public void addToWaitingLine(Reservation newReservation) {
        waitingLine.enqueue(newReservation);
    }

    // removes from top
    public Room bookRoom(String customerName) {
        if(rooms.isEmpty()) {
//            System.out.println("No more available room.");
            return null;
        }

        Room bookingRoom = rooms.pop();
        bookingRoom.changeStatus(false);
        bookingRoom.changeCustomerName(customerName);
        bookedRooms.add(bookingRoom);
        return bookingRoom;

    }

    public boolean isHaveRooms() {
        return rooms.getEntryCount() != 0;
    }
    // prints available content
    public void printContent() {
        rooms.printContent();
    }

    public void printBookedRooms() {
        bookedRooms.printContent();
    }

    public void printWaitingLine() {
        waitingLine.printContent();
    }

    public QueueInterface<Reservation> getWaitingLine() {
        return this.waitingLine;
    }

    public ListInterface<Room> getBookedRooms() {
        return this.bookedRooms;
    }

    public StackInterface<Room> getRooms() {
        return this.rooms;
    }


}
