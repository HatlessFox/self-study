package chapter_1.section_4;

import edu.princeton.cs.algs4.StdOut;
import utils.LangUtils;

/*
 * Task 1.4.10
 * 
 * Modify binary search so that it always returns the element with the smallest index that matches 
 * the search element (and still guarantees logarithmic running time).
 */
public class Task_10 {

    private static int lowerBound(int[] data, int k) {
        int lo = 0, hi = data.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if      (k < data[mid]) {
                lo = mid + 1;
            } else if (data[mid] < k || (mid != 0 && data[mid - 1] == k)) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
    
    /* Tests */
    
    private static int __test_id = 0;
    
    private static void __runTest(int[] data, int k, int expected) {
        StdOut.printf("%d. %s\n", ++__test_id, lowerBound(data, k) == expected ? "OK" : "Failed");
    }
    
    private static void __runTests() {
        __runTest(LangUtils.ints2arr(1, 2, 3), 4, -1);
        __runTest(LangUtils.ints2arr(1, 1, 2, 2, 3, 3), 2, 2);
        __runTest(LangUtils.ints2arr(1, 1, 1, 1, 1), 1, 0);
    }
    
    public static void main(String[] args) {
        __runTests();
    }
}
