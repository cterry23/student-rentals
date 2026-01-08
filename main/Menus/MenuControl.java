package Menus;

import java.util.Scanner;
import java.util.ArrayList;

import Users.User;
import Rooms.Room;
import Rooms.RoomHandler;
import Bookings.BookingHandler;
import Users.UserHandler;
import Extras.InputValidation;

public class MenuControl {

    // holds room tree to prevent wasting memory, rather than building a new tree every time rooms are looked at
    private ArrayList<Room> roomList;
    private RoomHandler roomHandler;
    private InputValidation validator;

    public MenuControl() {
        this.roomHandler = new RoomHandler();
        this.validator = new InputValidation();
    }

    // controls all menu calls and transitions from one place to avoid function calls within menus alltogether
    public void runMenu(MenuBase menu, User user, Scanner scan) {
        boolean running = true;

        // recursive so that user can return to previous menus instead of restarting the program entirely
        while (running) {
            menu.displayMenu();
            int choice = validator.validInt(scan);
            MenuResults result = menu.userChoice(choice, user, scan);

            // menu transition logic, using enum values returned by variable choice
            switch (result) {
                
                // in case of bad input, loops menu call again
                case CONTINUE:
                    break;

                // exits current menu, returns to previous or exits program if main menu
                case LOGOUT:
                    running = false;
                    break;
                
                // Event used to transition to RoomMenu
                case ROOMS:
                    RoomsMenu roomsMenu = new RoomsMenu();
                    runMenu(roomsMenu, user, scan);
                    break;

                // Calls room searching algorithm based on rent range
                case ROOM_SEARCH:
                    this.roomList = roomHandler.filterRooms(scan);
                    roomHandler.displayRoomList(roomList);

                    if (roomList != null) {

                        System.out.println("Enter a room number to book (or enter 0 to exit)");
                        choice = validator.intWithinRange(scan, roomList.size());
                        switch (choice) {
                            case 0:
                                System.out.println("Exiting room search.");
                                break;
                            default:
                                Room selectedRoom = roomList.get(choice - 1);
                                BookingHandler.createBooking(selectedRoom, user);
                        }

                        result = MenuResults.LOGOUT;

                    } else {
                        System.out.println("No rooms found based on current search paramaters, please try again");
                        result = MenuResults.CONTINUE;
                    }
                    break;

                // Allows user to display their pending applications from the main menu, 
                case BOOKINGS:
                    BookingHandler.viewBookings(user);
                    result = MenuResults.LOGOUT;
                    break;
                
                // Displays current user's account information, called from UserMenu
                case DISPLAY_PROFILE:
                    UserHandler.displayUserInfo(user);
                    result = MenuResults.LOGOUT;
                    break;
            }
        }
    }    
}

