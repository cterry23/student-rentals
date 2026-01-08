package Bookings;

public class Booking {
    private String email;
    private String address;
    private int duration;
    private int rent;
    private String bookingStatus;

    public Booking(
        String email,
        String address,
        int duration,
        int rent,
        String bookingStatus
    ) {
        this.email = email;
        this.address = address;
        this.duration = duration;
        this.rent = rent;
        this.bookingStatus = bookingStatus;
    }

    // Getters for booking attributes
    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    // Getter returns a formatted string containing all booking information for object initialisation
    public String getBookingString() {
        String bookingString = String.format(
            "%s;%s;%s;%s;%s",
            email,
            address,
            duration,
            rent,
            bookingStatus
        );

        return bookingString;
    }

    // Method outputs all relevant inforamation for a user to view a booking
    public void displayBooking() {
        System.out.println();
        System.out.println("- Application Info -");
        System.out.println(String.format("Email: %s", email));
        System.out.println(String.format("Address: %s", address));
        System.out.println(String.format("Booking Duration: %s", duration));
        System.out.println(String.format("Monthly Rent: %s", rent));
        System.out.println(String.format("Application Status: %s", bookingStatus));
        System.out.println();
    }

    //Setters
    //Updates booking based on user input, to be called by homeowner
    public void updateBookingStatus(int userInput) {
        switch (userInput) {
            case 1:
                this.bookingStatus = "Approved";
                break;
            case 2:
                this.bookingStatus = "Denied";
                break;
            default:
                System.out.println("Invalid input. Please try again.");
        }
    }
}
