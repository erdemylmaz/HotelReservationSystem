package HotelReservationSystem.RoomTypes;

public class Room {
    private int ID;
    private String type;
    private String customerName;
    private boolean isAvailable;

    public Room(int ID, String type) {
        this.ID = ID;
        this.type = type;
        this.isAvailable = true;
    }

    public Room(int ID, String type, boolean isAvailable) {
        this.ID = ID;
        this.type = type;
        this.isAvailable = isAvailable;
    }

    public boolean getStatus() {
        return isAvailable;
    }

    public int getID() {
        return this.ID;
    }

    public String getType() {
        return this.type;
    }

    public void changeStatus(boolean newStatus) {
        this.isAvailable = newStatus;
    }

    public void changeCustomerName(String newCustomerName) {
        this.customerName = newCustomerName;
    }
    public String getCustomerName() {
        return this.customerName;
    }

}
