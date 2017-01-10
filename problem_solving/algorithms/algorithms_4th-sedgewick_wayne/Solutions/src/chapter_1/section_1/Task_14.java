package chapter_1.section_1;

import edu.princeton.cs.algs4.StdOut;

public class Task_14 {

    /* Implementation */
    
    private static int log(int x) {
        while (true) {
            int tmp = x & (x - 1);
            if (tmp == 0) break;
            x = tmp;
        }
        
        int log = 0;
        while (1 < x) {
            log++;
            x >>= 1;
        }
        return log;
    }
    
    /* Tests */
    
    private static int __test_i = 0;
    
    private static void __runTest(int x, int expected) {
        StdOut.printf("%d. %s\n", ++__test_i, (log(x) == expected) ? "OK" : "FAILED");
    }
    
    public static void main(String[] args) {
        __runTest(1, 0);
        __runTest(10, 3);
        __runTest(1024, 10);
    }
}
