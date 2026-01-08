package Rooms;

public class Room {
    private String address;
    private int rent;
    private int duration;
    private Boolean available;

    public Room(
        String address,
        int rent,
        int duration
    ) {
        this.address = address;
        this.rent = rent;
        this.duration = duration;
        this.available = true;
    }

    // getters for specific attributes

    public String getAddress() {
        return address;
    }

    public int getRent() {
        return rent;
    }

    public int getDuration() {
        return duration;
    }

    public Boolean isAvailable() {
        return available;
    }

    // Function to print all room info for the user, used for display
    public void displayRoomInfo(int i) {
        System.out.println();
        System.out.println(String.format("- Room %s: Information -", i + 1));
        System.out.println(String.format("Address: %s", address));
        System.out.println(String.format("Monthly rent: %s", rent));
        System.out.println(String.format("Lease duration (months): %s", duration));
        
        if (available) {
            System.out.println("Availability: This room is currently available");
        } else {
            System.out.println("Availability: This room is currently unavailable");
        }

        System.out.println();
    }

    // Setters for updating the listing
    public void updateAvailable() {
        if (available) {
            this.available = false;
        } else {
            this.available = true;
        }
    }

    public void updateRent(int rent) {
        this.rent = rent;
    }

    public void updateDuration(int duration) {
        this.duration = duration;
    }
}
