package HotelReservationSystem.RoomTypes;

public class DeluxeRooms extends Rooms {
    private static final int DEFAULT_ROOM_COUNT = 5;

    public DeluxeRooms() {
        super(DEFAULT_ROOM_COUNT);
    }

    public DeluxeRooms(int roomCount) {
        super(roomCount);
    }
}
