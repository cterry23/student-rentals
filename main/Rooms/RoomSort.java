package Rooms;
import java.util.ArrayList;

/* 
Sorts an arraylist of rooms into ascending order by rent for output readability
*/

public class RoomSort {

    // initial function calls recursive version for readability in other files
    public static void mergeSort(ArrayList<Room> rooms) {
        mergeSortRec(rooms, rooms.size());
    }

    // its a merge sort
    private static void mergeSortRec(ArrayList<Room> a, int n) {
        if (n < 2) {
            return;
        }

        int mid = n/2;

        ArrayList<Room> l = new ArrayList<Room>();
        ArrayList<Room> r = new ArrayList<Room>();

        for (int i = 0; i < mid; i++) {
            l.add(a.get(i));
        }

        for (int i = mid; i < n; i++) {
            r.add(a.get(i));
        }

        mergeSortRec(l, mid);
        mergeSortRec(r, n - mid);

        merge(a, l, r, mid, n-mid);
    }

    private static void merge(ArrayList<Room> a, ArrayList<Room> l, ArrayList<Room> r, int left, int right) {
        int i = 0, j = 0, k = 0;

        while (i < left && j < right) {
            if (l.get(i).getRent() <= r.get(j).getRent()) {
                a.set(k++, l.get(i++));
            } else {
                a.set(k++, r.get(j++));
            }
        }

        while (i < left) {
            a.set(k++, l.get(i++));
        }

        while (j < right) {
            a.set(k++, r.get(j++));
        }
    }
}
