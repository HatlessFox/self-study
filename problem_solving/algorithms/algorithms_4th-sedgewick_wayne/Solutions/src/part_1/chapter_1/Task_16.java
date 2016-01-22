package part_1.chapter_1;

import edu.princeton.cs.algs4.StdOut;

public class Task_16 {

    private static String exR1(int n) {
        if (n <= 0) return "";
        return exR1(n-3) + n + exR1(n-2) + n;
    }
    
    public static void main(String[] args) {
        StdOut.println(exR1(6));
    }
}
