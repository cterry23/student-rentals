package Menus;
import java.util.Scanner;
import Users.User;

public class RoomsMenu implements MenuBase {

    //Rooms menu options
    public void displayMenu() {
        System.out.println("Room Options");
        System.out.println("1. search available rooms");
        System.out.println("0. exit");
        System.out.println();
    }


    // Rooms Menu results, uses integer input to return corresponding enum type
    public MenuResults userChoice(int choice, User user, Scanner scan) {
        switch (choice) {
            case 1:
                return MenuResults.ROOM_SEARCH;
            case 0:
                return MenuResults.LOGOUT;
            default:
                return MenuResults.CONTINUE;   
        }
    }
}
