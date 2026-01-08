package Extras;
import java.util.Scanner;

public class InputValidation {

    /* 
    Functions to ensure that user input is an integer
    Only way to interact with the program past login (which has its own input validation) is via integer input 
    */
    public int validInt(Scanner scan) {
        int returnValue = 0;
        boolean valid = false;
        String input = "";

        while (!valid) {
            input = scan.nextLine();
            try {
                returnValue = Integer.parseInt(input);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.print("Input invalid, please try again: ");
            }
        }
        return returnValue;
    }

    // ensures user input is within the range of a list, mostly for selecting a room at RoomBooking
    public int intWithinRange(Scanner scan, int size) {
        boolean valid = false;
        int output = 0;

        while (!valid) {
            output = validInt(scan);
            if (output >= 0 && output <= size) {
                valid = true;
            }
        }
        return output;
    }
}
