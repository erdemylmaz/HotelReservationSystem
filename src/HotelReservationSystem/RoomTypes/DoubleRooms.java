package HotelReservationSystem.RoomTypes;

public class DoubleRooms extends Rooms {
    private static final int DEFAULT_ROOM_COUNT = 5;
    public DoubleRooms() {
        super(DEFAULT_ROOM_COUNT);
    }

    public DoubleRooms(int roomCount) {
        super(roomCount);
    }
}
