import Users.User;
import Users.Student;
import Users.UserHandler;
import java.util.Scanner;

import Menus.*;

public class Main {
    public static void main(String[] args) {

        // scanner generated here to be passed throughout the program, rather than have a new one created at every input
        Scanner scan = new Scanner(System.in);

        // stores the user of the program, required for the rest of the program to function
        User currentUser = UserHandler.login(scan);

        // Loads different menu based on user type
        MenuBase menu;
        if (currentUser instanceof Student) {
            menu = new StudentMenu();
        } else {
            menu = new HomeownerMenu();
        }

        // menu controller initialised here because it basically calls the rest of the program
        MenuControl menuController = new MenuControl();

        menuController.runMenu(menu, currentUser, scan);

        // good practice
        scan.close();
    }
}
