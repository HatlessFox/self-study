package part_1.chapter_3;

import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.3.3
 * 
 * Suppose that a client performs an intermixed sequence of (stack) push and pop operations. The 
 * push operations put the integers 0 through 9 in order onto the stack; the pop operations print 
 * out the return values. Which of the following sequences(s) could not occur? (see tests)
 */
public class Task_03 {

    private static boolean checkValidity(int[] vals) {
        int max_extracted = -1, last = 10; // assuming vals in [0..9]
        for (int e : vals) {
            if (last < e && e < max_extracted) { return false; }
            last = e;
            max_extracted = Math.max(e, max_extracted);
        }
        return true;
    }
    
    private static void testSequence(String code, int[] vals) {
        StdOut.printf("%s) %s\n", code, checkValidity(vals) ? "VALID" : "INVALID");
    }
    
    public static void main(String[] args) {
        testSequence("a", new int[] {4, 3, 2, 1, 0, 9, 8, 7, 6, 5});
        testSequence("b", new int[] {4, 6, 8, 7, 5, 3, 2, 9, 0, 1});
        testSequence("c", new int[] {2, 5, 6, 7, 4, 8, 9, 3, 1, 0});
        testSequence("d", new int[] {4, 3, 2, 1, 0, 5, 6, 7, 8, 9});
        testSequence("e", new int[] {1, 2, 3, 4, 5, 6, 9, 8, 7, 0});
        testSequence("f", new int[] {0, 4, 6, 5, 3, 8, 1, 7, 2, 9});
        testSequence("g", new int[] {1, 4, 7, 9, 8, 6, 5, 3, 0, 2});
        testSequence("h", new int[] {2, 1, 4, 3, 6, 5, 8, 7, 9, 0});
    }
}
