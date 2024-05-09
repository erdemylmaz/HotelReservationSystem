package HotelReservationSystem;

public class Reservation {
    private int ID;
    private String customerName;
    private String roomType;

    public Reservation(int ID, String customerName, String roomType) {
        this.ID = ID;
        this.customerName = customerName;
        this.roomType = roomType;
    }

    public int getID() {
        return this.ID;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public String getReservationType() {
        return this.roomType;
    }

}
