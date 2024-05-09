package HotelReservationSystem.RoomTypes;

public class SingleRooms extends Rooms {
    private static final int DEFAULT_ROOM_COUNT = 5;
    public SingleRooms() {
        super(DEFAULT_ROOM_COUNT);
    }
    public SingleRooms(int roomCount) {
        super(roomCount);
    }
}