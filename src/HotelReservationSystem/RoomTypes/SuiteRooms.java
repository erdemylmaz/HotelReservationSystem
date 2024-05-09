package HotelReservationSystem.RoomTypes;

public class SuiteRooms extends Rooms {
    private static final int DEFAULT_ROOM_COUNT = 5;
    public SuiteRooms() {
        super(DEFAULT_ROOM_COUNT);
    }

    public SuiteRooms(int roomCount) {
        super(roomCount);
    }
}
