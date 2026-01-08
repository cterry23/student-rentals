package Rooms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Extras.InputValidation;

/* Basically a container class for RoomTree, holds a Room Tree and input validator object for input handling */
public class RoomHandler {

    private RoomTree tree;
    private InputValidation validator;
    
    public RoomHandler() {
        this.tree = buildTree();
        this.validator = new InputValidation();
    }

    // creates an empty tree to store Room objects, then populates tree using the rooms.txt file
    public static RoomTree buildTree() {
        RoomTree tree = new RoomTree();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("resources/rooms.txt")); 

            // iterates through file and adds each room to tree as node
            String line;
            String[] parts;
            while ((line = reader.readLine()) != null) {

                parts = line.split(";");

                tree.addNode(
                    new Room(
                        parts[0], 
                        Integer.parseInt(parts[1]), 
                        Integer.parseInt(parts[2])
                    )
                );
            }
            reader.close();
        } catch (IOException e) {
            // another current working directory check
            System.out.println("Error reading file. Please ensure cwd is /src/");
        }
        return tree;
    }

    // function to call a search on the tree within a specified rent range
    public ArrayList<Room> filterRooms(Scanner scan) {
        System.out.println("Please enter a minimum monthly rent");
        int lower = validator.validInt(scan);
        System.out.println("Please enter a maximum monthly rent");
        int upper = validator.validInt(scan);

        // swaps ranges around if lower is greater than upper
        if (lower > upper) {
            System.out.println("Invalid range, swapping");
            upper = upper + lower;
            lower = upper - lower;
            upper = upper - lower;
        }

        // returns the arraylist of rooms, sorted in ascending order by monthly rent
        ArrayList<Room> returnedRooms = tree.rangeSearch(lower, upper);
        return returnedRooms;
    }

    // outputs room information for all rooms within an array list
    public void displayRoomList(ArrayList<Room> rooms) {
        for (int i = 0; i < rooms.size(); i++) {
            rooms.get(i).displayRoomInfo(i);
        }
    }
}
