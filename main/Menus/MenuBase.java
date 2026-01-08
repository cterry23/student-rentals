package Menus;
import java.util.Scanner;
import Users.User;

// Used an interface for all menus to maintain comformity
public interface MenuBase {

    //Outputs menu options
    void displayMenu();

    //Parses user input using MenuResults enum file
    MenuResults userChoice(int choice, User user, Scanner scanner);
}
