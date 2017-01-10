package chapter_1.section_1;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

public class Task_15 {

    /* Implementation */
    
    private static int[] histogram(int[] a, int m) {
        if (m <= 0) return new int[0];
        
        int[] stat = new int[m];
        for (int e : a) {
            if (e < 0 || m <= e) continue;
            stat[e]++;
        }
        return stat;
    }
    
    /* Tests */
    
    private static int __test_i = 0;
    
    private static void __runTest(int[] a, int m, int[] expected) {
        StdOut.printf("%d. %s\n", ++__test_i,
                Arrays.equals(histogram(a, m), expected) ? "OK" : "FAIL");
    }
    
    public static void main(String[] args) {
        __runTest(new int[]{0}, 0, new int[]{});
        __runTest(new int[]{0}, 1, new int[]{1});
        __runTest(new int[]{0, 1, 3, 5}, 4, new int[]{1, 1, 0, 1});
        __runTest(new int[]{0, -1, 3, 5}, 4, new int[]{1, 0, 0, 1});
        __runTest(new int[]{0, 1, 3, 5}, -4, new int[]{});
    }
}
