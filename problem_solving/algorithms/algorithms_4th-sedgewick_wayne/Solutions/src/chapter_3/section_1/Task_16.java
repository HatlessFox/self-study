package chapter_3.section_1;

import chapter_3.section_1.Task_12.BinarySearchST;
import chapter_3.section_1.Task_12.FlightInfo;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 3.1.16
 * 
 * Implement the delete() method for BinarySearchST.
 * 
 */
public class Task_16 {

    public static class BinarySearchSTWithDelete<Item extends Comparable<Item>>
        extends BinarySearchST<Item> {
        
        public BinarySearchSTWithDelete(int cap) { super(cap); }
        
        public void delete(Item item) {
            int rank = rank(item);
            if (!containsByRank(rank, item)) { return; }

            System.arraycopy(items, rank + 1, items, rank, sz - rank);
            sz -= 1;
            items[sz] = null;
        }
    }
    
    public static void main(String[] args) {
        BinarySearchSTWithDelete<FlightInfo> bst = new BinarySearchSTWithDelete<>(3);
        Task_12.initStWithFlights(bst);

        StdOut.println("== DUMP Init");
        Task_12.dumpBinST(bst);

        StdOut.println("== DUMP Del 09:00:13");
        bst.delete(new FlightInfo("", 9, 0, 13));
        Task_12.dumpBinST(bst);

        StdOut.println("== DUMP Del 09:15:00");
        bst.delete(new FlightInfo("", 9, 15, 0));
        Task_12.dumpBinST(bst);
    }
}
