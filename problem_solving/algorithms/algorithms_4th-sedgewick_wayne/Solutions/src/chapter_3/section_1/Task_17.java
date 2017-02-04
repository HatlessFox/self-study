package chapter_3.section_1;

import chapter_3.section_1.Task_12.BinarySearchST;
import chapter_3.section_1.Task_12.FlightInfo;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 3.1.17
 * 
 * Implement the floor() method for BinarySerachSt.
 * 
 */
public class Task_17 {

    public static class BinarySearchSTWithDelete<Item extends Comparable<Item>>
    extends BinarySearchST<Item> {
    
        public BinarySearchSTWithDelete(int cap) { super(cap); }

        public Item floor(Item item) {
            int rank = rank(item);
            return select(rank - (containsByRank(rank, item) ? 0 : 1));
        }
    }
    
    
    public static void main(String[] args) {
        BinarySearchSTWithDelete<FlightInfo> bst = new BinarySearchSTWithDelete<>(3);
        Task_12.initStWithFlights(bst);

        StdOut.println("== DUMP");
        Task_12.dumpBinST(bst);

        StdOut.printf("floor(09:05:00): %s\n", bst.floor(new FlightInfo("", 9, 5, 0)));
        StdOut.printf("floor(08:05:00): %s\n", bst.floor(new FlightInfo("", 8, 5, 0)));
    }

}
