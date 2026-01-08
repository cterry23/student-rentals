package Menus;
import java.util.Scanner;
import Users.User;

public class StudentMenu implements MenuBase {

    // Student specific menu options
    public void displayMenu() {
        System.out.println("Select Option");
        System.out.println("1. search available rooms");
        System.out.println("2. view pending applications");
        System.out.println("3. view my information");
        System.out.println("0. logout");
        System.out.println();
    }

    // Student specific menu results, uses integer input to return corresponding enum type
    public MenuResults userChoice(int choice, User user, Scanner scan) {
        switch (choice) {
            case 1:
                return MenuResults.ROOMS;
            case 2:
                return MenuResults.BOOKINGS;
            case 3:
                return MenuResults.DISPLAY_PROFILE;
            case 0:
                return MenuResults.LOGOUT;
            default:
                return MenuResults.CONTINUE;
        }
    }
}
