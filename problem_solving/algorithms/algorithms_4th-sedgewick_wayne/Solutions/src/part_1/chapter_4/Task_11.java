package part_1.chapter_4;

import java.util.function.Predicate;

import edu.princeton.cs.algs4.StdOut;
import utils.LangUtils;

/* 
 * Task 1.4.11
 * 
 * Add an instance method howMany() to StatciSETofInts (page 99) that finds the number of 
 * occurrences of a given key in time proportional to log N in the worst case.
 */
public class Task_11 {
    
    private static int bsTemplate(int data[], int k,
                                  Predicate<Integer> shouldGoLeft,
                                  Predicate<Integer> shouldGoRight) {
        int lo = 0, hi = data.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (shouldGoLeft.test(mid)) { hi = mid - 1; }
            else if (shouldGoRight.test(mid)) { lo = mid + 1; }
            else { return mid; } 
        }
        return -1;
    }
    
    private static int lowerBound(int data[], int k) {
        return bsTemplate(data, k,
                (mid) -> k < data[mid] || 
                         (data[mid] == k && (mid != 0) && data[mid - 1] == k),
                (mid) -> data[mid] < k);
    }
    
    private static int upperBound(int data[], int k) {
        return bsTemplate(data, k,
                (mid) -> k < data[mid],
                (mid) -> data[mid] < k ||
                         (data[mid] == k && (mid != data.length - 1) && data[mid + 1] == k));
    }
    
    private static int howMany(int data[], int k) {
        int upper_bnd = upperBound(data, k);
        if (upper_bnd == -1) {
            return 0;
        }
        return upper_bnd - lowerBound(data, k) + 1;
    }
    
    /* Tests */
    
    private static int __test_id = 0;
    
    private static void __runTest(int[] data, int k, int expected) {
        StdOut.printf("%d. %s\n", ++__test_id, howMany(data, k) == expected ? "OK" : "Failed");
    }
    
    private static void __runTests() {
        __runTest(LangUtils.ints2arr(1, 2, 3), 4, 0);
        __runTest(LangUtils.ints2arr(1, 1, 2, 2, 2, 3, 3), 2, 3);
        __runTest(LangUtils.ints2arr(1, 1, 1, 1, 1), 1, 5);
    }
    
    public static void main(String[] args) {
        __runTests();
    }
}
