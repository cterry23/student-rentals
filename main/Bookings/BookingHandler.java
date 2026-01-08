package Bookings;

import Rooms.Room;
import Users.User;
import Users.Student;
import Users.Homeowner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BookingHandler {

    // takes booking object and appends it to bookings.txt for storage
    private static boolean writeBooking(Booking booking) {
        try {
            FileWriter writer = new FileWriter("resources/bookings.txt", true);
            writer.write(booking.getBookingString() + "\n" );
            writer.close();
            return true;
        } catch (IOException e) {
            //only here so vscode doesnt yell at me, pls run from src
            System.out.println("Failed to write booking to file, please ensure that cwd is /src/");
            return false;
        }
    }

    // Method to prevent students from applying for the same room multiple times
    private static boolean checkDuplicate(String address, String email) {
        boolean isDuplicate = false;
        
        try {
            BufferedReader reader = new BufferedReader(
                new FileReader(
                    "resources/bookings.txt"
                )
            );

            String line;
            String[] parts;

            // iterates through bookings file, returns true if duplicate found
            while (((line = reader.readLine()) != null) && isDuplicate == false) {
                parts = line.split(";");
                
                if (parts[0].equals(email) && parts[1].equals(address)) {
                    isDuplicate = true;
                    reader.close();
                    return isDuplicate;
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Failed to read booking file, please ensure that cwd is /src/");
        }

        return isDuplicate;
    }

    //parses room and user objects to call booking object constructor, didnt want to clog Booking.java with imports
    public static void createBooking(Room room, User user) {

        boolean duplicate = checkDuplicate(room.getAddress(), user.getEmail());
        
        if (!duplicate) {
            Booking newBooking = new Booking(
                user.getEmail(), 
                room.getAddress(), 
                room.getDuration(), 
                room.getRent(), 
                "Pending"
            );

            // New booking object written to bookings.txt
            if (writeBooking(newBooking)) {
                System.out.println("Booking successfully created");
            } else {
                System.out.println("An error occured.");
            }
        } else {
            System.out.println("You have already applied for this room");
        }
    }

    /* 
    outputs all bookings for a user by comparing email for students or address for homeowners
    didn't implement user registration (i left this too late) and all my premade room addresses match 1:1 with homeowner addresses
    so for now address is basically a primary key for both rooms and homeowners
    */
    public static void viewBookings(User user) {
        try {
            BufferedReader reader = new BufferedReader(
                new FileReader(
                    "resources/bookings.txt"
                )
            );

            // blank list of bookings, for output
            ArrayList<Booking> bookings = new ArrayList<Booking>();

            // iterates through each line of bookings.txt and returns matching strings
            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(";");
                boolean contains = false;

                // account for either user type
                // i know instanceof is bad practice but there are only two possible user types and im lazy
                if (user instanceof Student && parts[0].equals(user.getEmail())) {
                    contains = true;
                } else if (user instanceof Homeowner homeowner && parts[1].equals(homeowner.getAddress())) {
                    contains = true;
                }

                // adds matched booking to bookings arraylist
                if (contains) {
                    bookings.add(
                        new Booking(
                            parts[0], 
                            parts[1], 
                            Integer.parseInt(parts[2]), 
                            Integer.parseInt(parts[3]), 
                            parts[4]
                        )
                    );
                }
            }
            reader.close();

            // returned bookings output for display purposes
            System.out.println();
            System.out.println("Your bookings");
            for (int i = 0; i < bookings.size();i++) {
                bookings.get(i).displayBooking();
            }

        } catch (IOException e) {
            // urgh
            System.out.println("Something went wrong, ensure working directory is /src/");
        }
    }
}
