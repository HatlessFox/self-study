package part_1.chapter_4;

import java.util.function.UnaryOperator;

import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.4.6
 * 
 * Give the order of growth (as a function of N) of the running times of each of the following code
 * fragments <page 208>.
 */
public class Task_06 {

    public static int runFragmentA(int n) {
        int sum = 0;
        for (int i = n; 0 < i; i /= 2) {
            for (int j = 0; j < i; j++) {
                sum++;
            }
        }
        return sum;
    }
    
    public static int runFragmentB(int n) {
        int sum = 0;
        for (int i = 1; i < n; i *= 2) {
            for (int j = 0; j < i; j++) {
                sum++;
            }
        }
        return sum;
    }
    
    public static int runFragmentC(int n) {
        int sum = 0;
        for (int i = 1; i < n; i *= 2) {
            for (int j = 0; j < n; j++) {
                sum++;
            }
        }
        return sum;
    }
    
    /* Tests */
    
    public static boolean testFragment(UnaryOperator<Integer> fragment,
                                UnaryOperator<Integer> expected_supplier) {
        for (int n = 1; n < 1_000_000; n *= 16) {
            int actual = fragment.apply(n), expected = expected_supplier.apply(n);
            if (actual != expected) {
                return false;
            }
        }
        return true;
    }
    
    private static double log2(int n) {
        return Math.log(n) / Math.log(2); 
    }
    
    public static void runTests() {
        StdOut.printf("A. %s\n", 
                testFragment(Task_06::runFragmentA, (n -> 2 * n - 1)) ? "OK" : "FAIL");
        StdOut.printf("B. %s\n", 
                testFragment(Task_06::runFragmentB, (n -> n - 1)) ? "OK" : "FAIL");
        StdOut.printf("C. %s\n",
                testFragment(Task_06::runFragmentC, (n -> (int)(n * log2(n)))) ? "OK" : "FAIL");
    }
    
    public static void main(String[] args) {
        runTests();
    }
}
