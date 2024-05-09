package HotelReservationSystem;

import HotelReservationSystem.RoomTypes.*;
import HotelReservationSystem.ADTs.*;

import java.io.FileNotFoundException;

public class HotelReservationSystem {
    private static int currentID = 0;
    private static SingleRooms singleRooms = new SingleRooms();
    private static DoubleRooms doubleRooms = new DoubleRooms();
    private static SuiteRooms suiteRooms = new SuiteRooms();
    private static DeluxeRooms deluxeRooms = new DeluxeRooms();
    private static Rooms[] categorizedRooms = {singleRooms, doubleRooms, suiteRooms, deluxeRooms};
    private static String[] categories = {"Single", "Double", "Suite", "Deluxe"};

    private static ListInterface<Room> unavailableRooms = new ArrayList<Room>();
    private static ListInterface<Room> availableRooms = new ArrayList<Room>();

    public static void main(String[] args) throws FileNotFoundException {
        // initialize inital state
        System.out.println("INITIALIZED!");
        for(int i = 0; i < categories.length; i++) {
            Rooms categoryRooms = getCategoryRooms(categories[i]);
            initializeRooms(categoryRooms, categories[i]);
        }

        // get reservations from file
        QueueInterface<Reservation> reservations = new Queue<Reservation>();

        FileIO MyFileReader = new FileIO("reservations.txt");
        MyFileReader.readFile(reservations);

        // process given reservations
        processReservations(reservations);

        // print content
        System.out.println("RESERVATIONS ARE MADE!");
        for(int i = 0; i < categories.length; i++) {
            printDetails(categorizedRooms[i], categories[i]);
        }

        // make all odd numbered rooms available
        refactorRooms();
        System.out.println("ODD NUMBERED ROOMS ARE BECOME AVAILABLE!");

        for(int i = 0; i < categories.length; i++) {
            printDetails(categorizedRooms[i], categories[i]);
        }

        // process waiting lines;
        processWaitingLines();
        System.out.println();
        System.out.println("WAITING LINES ARE PROCEEDED!");

        for(int i = 0; i < categories.length; i++) {
            printDetails(categorizedRooms[i], categories[i]);
        }

        // categorize rooms according to their availability status
        for(int i = 0; i < categorizedRooms.length; i++) {
            Rooms categoryRoom = categorizedRooms[i];
            // add unavailable rooms
            ListInterface<Room> categoryBookedRooms = categoryRoom.getBookedRooms();
            for(int j = 0; j < categoryBookedRooms.getEntryCount(); j++) {
                unavailableRooms.add(categoryBookedRooms.getEntry(j));
            }

            // add available rooms
            StackInterface<Room> categoryAvailableRooms = categoryRoom.getRooms();
            while(!categoryAvailableRooms.isEmpty()) {
                availableRooms.add(categoryAvailableRooms.pop());
            }
        }

        // print unavailable rooms
        System.out.println();
        System.out.println("UNAVAILABLE ROOMS (" + unavailableRooms.getEntryCount() + ")");
        unavailableRooms.printContent();

        // print available rooms
        System.out.println();
        System.out.println("AVAILABLE ROOMS (" + availableRooms.getEntryCount() + ")");
        availableRooms.printContent();
    }

    private static void refactorRooms() {
        for(int i = 0; i < categories.length; i++) {
            Rooms categoryRooms = categorizedRooms[i];

            ListInterface<Room> bookedRooms = categoryRooms.getBookedRooms();
            // start from end because least numbered needs to be on top of stack;
            for(int j = bookedRooms.getEntryCount() - 1; j >= 0; j--) {
                Room room = bookedRooms.getEntry(j);

                // if is even, make it available;
                if(room.getID() % 2 == 1) {
                    room.changeCustomerName("");
                    room.changeStatus(true);
                    categoryRooms.getBookedRooms().remove(j);
                    categoryRooms.addRoom(room);
                }
            }
        }
    }

    private static Rooms getCategoryRooms(String type) {
        for(int i = 0; i < categories.length; i++) {
            if(type.equals(categories[i])) {
                return categorizedRooms[i];
            }
        }
        return null;
    }

    private static void printDetails(Rooms categoryRooms, String roomType) {
        System.out.println("# # # # # # " + roomType + " # # # # #");
        System.out.println("Available Rooms of " + roomType + " Rooms");
        categoryRooms.printContent();
        System.out.println();

        System.out.println("Booked Rooms of " + roomType + " Rooms");
        categoryRooms.printBookedRooms();
        System.out.println();

        System.out.println("Waiting Line of " + roomType + " Rooms");
        categoryRooms.printWaitingLine();
        System.out.println();
        System.out.println("# # # # # # # # # # # # # # #");
    }

    private static void processReservations(QueueInterface reservations) {
        while(!reservations.isEmpty()) {
            Reservation currentReservation = (Reservation) reservations.dequeue();
            Rooms rooms = getCategoryRooms(currentReservation.getReservationType());
            if(rooms.isHaveRooms()) {
                rooms.bookRoom(currentReservation.getCustomerName());
            } else {
                rooms.addToWaitingLine(currentReservation);
            }
        }
    }

    private static void processWaitingLines() {
        for(int i = 0; i < categorizedRooms.length; i++) {
            Rooms categoryRooms = categorizedRooms[i];
            QueueInterface<Reservation> waitingLine = categoryRooms.getWaitingLine();
            while(categoryRooms.isHaveRooms() && !waitingLine.isEmpty()) {
                Reservation currentReservation = waitingLine.dequeue();
                categoryRooms.bookRoom(currentReservation.getCustomerName());
            }
        }
    }

    private static void initializeRooms(Rooms categoryRooms, String type) {
        currentID += 5;
        for(int i = 0; i < 5; i++) {
            Room newRoom = new Room(currentID - i, type, true);
            categoryRooms.addRoom(newRoom);
        }

        System.out.println(type + " pile of room:");
        categoryRooms.printContent();
        System.out.println();
    }

}
