package Menus;
import java.util.Scanner;
import Users.User;

public class HomeownerMenu implements MenuBase {

    //Homeowner menu option display
    public void displayMenu() {
        System.out.println("Options");
        System.out.println("1. view my applications");
        System.out.println("2. view my information");
        System.out.println("0. logout");
        System.out.println();
    }

    /* 
    Homeowner menu inputs, mostly unfinished due to lack of time
    Time of writing: January 7th, 8:40pm
    Uses integer input to return corresponding enum type
     */
    public MenuResults userChoice(int choice, User user, Scanner scan) {
        switch (choice) {
            case 1:
                return MenuResults.BOOKINGS;
            case 2:
                return MenuResults.DISPLAY_PROFILE;
            case 0:
                return MenuResults.LOGOUT;
            default:
                return MenuResults.CONTINUE;
        }
    }
}
