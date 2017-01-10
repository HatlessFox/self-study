package chapter_1.section_1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Task_05 {

    /* Implementation */
    
    private static boolean isInsideInterval(double x, double l_bnd, double r_bnd) {
        return l_bnd <= x && x <= r_bnd;
    }
    
    private static boolean isInsideInterval(double x) {
        return isInsideInterval(x, 0.0, 1.0);
    }
    
    public static boolean bothInInterval(double a, double b) {
        return isInsideInterval(a) && isInsideInterval(b);
    }
    
    /* Scaffolding for Testing*/
    
    private static boolean __RUN_TESTS = false;
    
    private static void __runTest(double a, double b, boolean expected) {
        boolean result = bothInInterval(a, b);
        StdOut.printf("a=%.3f, b=%.3f ==> %b -- %s\n", a, b, result,
                (result == expected) ? "OK" : "FAIL");
    }
    
    private static void __runTests() {
        __runTest(-3, -3, false);
        __runTest(0, 0, true);
        __runTest(0.5, 0.5, true);
        __runTest(0, 1, true);
        __runTest(0.3, 3, false);
        __runTest(3, 0.3, false);
        __runTest(3, 3, false);
    }
    
    private static void __cli() {
        StdOut.print("Enter two doubles: ");
        double a = StdIn.readDouble(), b = StdIn.readDouble();
        StdOut.println(bothInInterval(a, b));
    }
    
    public static void main(String[] args) {
        if (__RUN_TESTS) { __runTests(); } else { __cli(); }
    }
}
