package part_1.chapter_2;

import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.2.6
 * 
 * A string s in a circular shift (or circular rotation) of a string t if it matches when the
 * characters are circularly shifted by any number of positions; e.g., ACTGACG is a circular shift
 * of TGACGAC, and vice versa. Detecting this condition is important in the study of genomic
 * sequences. Write a program that checks whether two given strings s and t are circular shifts of 
 * one another. Hint: The solution is a one-liner with indexof(), length(), and string
 * concatenation.
 */
public class Task_06 {

    /* Implementation */
    
    private static boolean isCircularShift(String base, String val) {
        return base.length() == val.length() && (base + base).indexOf(val) != -1;
    }
    
    /* Tests */
    
    private static int __test_i = 0;
    private static void check(String base, String val, boolean expected) {
        StdOut.printf("%d. %s\n", ++__test_i,
                isCircularShift(base, val) == expected ? "OK" : "FAIL");
    }
    
    private static void runTests() {
        check("AAA", "AA", false);
        check("ACTGACG", "TGACGAC", true);
        check("ABCD", "EKFG", false);
    }
    
    public static void main(String[] args) {
        runTests();
    }
}
