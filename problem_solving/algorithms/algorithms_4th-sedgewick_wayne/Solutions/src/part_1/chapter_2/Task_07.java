package part_1.chapter_2;

import edu.princeton.cs.algs4.StdOut;

/* 
 * Task 1.2.8
 * 
 * What does the following recursive function return?
 */
public class Task_07 {

    private static String mystery(String s) {
        int n = s.length();
        if (n <= 1) return s;
        String a = s.substring(0, n/2);
        String b = s.substring(n/2, n);
        return mystery(b) + mystery(a);
    }
    
    public static void main(String[] s) {
        final String TEST_DATA = "abcdef";
        StdOut.printf("mystery(\"%s\") = \"%s\"\n", TEST_DATA, mystery(TEST_DATA));
    }
}
