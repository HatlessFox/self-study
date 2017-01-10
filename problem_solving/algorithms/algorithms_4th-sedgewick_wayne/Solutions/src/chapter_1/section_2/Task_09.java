package chapter_1.section_2;

import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.StdOut;

/* Task 1.2.9
 * 
 * Instrument BinarySearch (page 47) to use a Counter to count the total number of keys examined
 * during all searches and then print the total after all searches are complete.
 * Hint: Create a Counter in main() and pass it as an argument to rank().
 */
public class Task_09 {

    private static int rank(int key, int[] data, Counter cntr) {
        int lo = 0, hi = data.length - 1;
        while (lo <= hi) {
            cntr.increment();
            int mid = lo + (hi - lo)/2;
            if (data[mid] < key) lo = mid + 1;
            else if (key < data[mid]) hi = mid - 1;
            else return mid;
        }
        return -1;
    }
    
    static final int[] TEST_DATA = new int[]{0, 1, 2, 3, 5, 6, 7, 8};
    
    private static void runTest(int key) {
        Counter cntr = new Counter("BS couter");
        int rank = rank(key, TEST_DATA, cntr);
        StdOut.printf("Key %d -- Rank: %d; Counter: %s\n", key, rank, cntr.tally()); 
    }
    
    public static void main(String[] args) {
        runTest(4);
        runTest(8);
        runTest(2);
    }
}
