package chapter_1.section_1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Task_22 {
    
    /* Implementation */
    
    private static boolean __TRACING_IS_ENABLED = true;
    
    private static int rank(int k, int[] a, int lo, int hi, int depth) {
        if (__TRACING_IS_ENABLED) {
            String fmt = (depth == 0) ? "%s[%d, %d]\n" : String.format("%%%ds[%%d, %%d]\n", depth);
            StdOut.printf(fmt, "", lo, hi);
        }
        if (hi < lo) return -1;
        int mid = lo + (hi - lo) / 2;
        if (a[mid] == k)     return mid;
        else if (a[mid] < k) return rank(k, a, mid + 1, hi, depth + 1);
        else                 return rank(k, a, lo, hi - 1, depth + 1);
    }
    
    private static int rank(int k, int[] a) {
        return rank(k, a, 0, a.length - 1, 0);
    }
    
    /* Testing */
    
    private static boolean __RUN_TESTS = false;
    private static int __test_i = 0;
    
    private static void __runTest(int[] a, int k, int expected) {
        StdOut.printf("%d. %s\n", ++__test_i, (rank(k, a) == expected) ? "OK" : "FAIL");
    }
    
    private static void __runTests() {
        int[] a = new int[]{1, 2, 3, 5, 6, 7, 9, 10, 11};
        __runTest(a, 0, -1);
        __runTest(a, 1, 0);
        __runTest(a, 2, 1);
        __runTest(a, 3, 2);
        __runTest(a, 4, -1);
        __runTest(a, 5, 3);
        __runTest(a, 6, 4);
        __runTest(a, 7, 5);
        __runTest(a, 8, -1);
        __runTest(a, 9, 6);
        __runTest(a, 10, 7);
        __runTest(a, 11, 8);
        __runTest(a, 12, -1);
    }
    
    private static void __cli() {
        StdOut.print("Enter array size: ");
        int n = StdIn.readInt();
        
        StdOut.print("Enter array: ");
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdIn.readInt();
        }
        
        StdOut.print("Enter key: ");
        int k = StdIn.readInt();
        
        int i = rank(k, a, 0, n - 1, 0);
        if (i == -1) {
            StdOut.println("The key is not found");
        } else {
            StdOut.println("Rank is " + i);
        }
    }
    
    public static void main(String[] args) {
        if (__RUN_TESTS) __runTests(); else __cli();
    }
}
