package Rooms;

import java.util.ArrayList;

/* 
class for storing all rooms on the system within a tree because room objects are used a lot and 
its more efficient to store them once rather than checking the file every time you want to view rooms,
also helps with potential expandability.
*/


// Node class containing an arraylist of rooms, as some rooms may have the same rent and binary trees hate duplicate values
class Node {
    ArrayList<Room> rooms = new ArrayList<Room>();
    Node left;
    Node right;

    public Node (Room rooms) {
        this.rooms.add(rooms);
        right = null;
        left = null;
    }

    // setter for updating room arraylist, called when a room charging the same amount of rent is loaded
    public void appendNode(Room room) {
        this.rooms.add(room);
    }
}

public class RoomTree {

    Node root;

    // method for creating nodes and adding them to the tree, called recursively
    public void addNode(Room room) {
        root = addNodeRec(root, room);
    }

    // recursive part
    private Node addNodeRec(Node current, Room room) {

        if (current == null) {
            return new Node(room);
        }

        // compares rent value of current node and the room to be added
        if (room.getRent() < current.rooms.get(0).getRent()) {
            current.left = addNodeRec(current.left, room);
        }

        else if (room.getRent() > current.rooms.get(0).getRent()) {
            current.right = addNodeRec(current.right, room);
        }

        // if the room's rent is the same as the rent stored by the node, the room is added to that node.
        else if (room.getRent() == current.rooms.get(0).getRent()) {
            current.appendNode(room);
        }

        return current;
    }

    /* 
    Depth first tree search (or filtering?) that collects all room objects from within the nodes, 
    including duplicates, returns them within an arraylist sorted by rent in ascending order. Returned
    list used for display and booking creation.
    */
    public ArrayList<Room> rangeSearch(int rangeLower, int rangeUpper) {
        ArrayList<Room> searchResults = rangeSearchRec(root, rangeLower, rangeUpper);
        RoomSort.mergeSort(searchResults);
        return searchResults;
    }

    // actual function containing current node for recursive calling
    private ArrayList<Room> rangeSearchRec(Node node, int rangeLower, int rangeUpper) {
        ArrayList<Room> result = new ArrayList<Room>();

        // edited search logic to allow the function to temporarily leave the search range to reach otherwise inaccessible nodes
        if (node != null) {
            if (node.rooms.get(0).getRent() >= rangeLower && node.rooms.get(0).getRent() <= rangeUpper) {

                result.addAll(node.rooms);
                result.addAll(rangeSearchRec(node.left, rangeLower, rangeUpper));
                result.addAll(rangeSearchRec(node.right, rangeLower, rangeUpper));

            } else if (node.rooms.get(0).getRent() > rangeUpper) {
                result.addAll(rangeSearchRec(node.left, rangeLower, rangeUpper));

            } else if (node.rooms.get(0).getRent() < rangeLower) {
                result.addAll(rangeSearchRec(node.right, rangeLower, rangeUpper));
            }
        }

        return result;
    }
}
