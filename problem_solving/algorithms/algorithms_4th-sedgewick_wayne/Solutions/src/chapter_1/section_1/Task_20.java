package chapter_1.section_1;

import edu.princeton.cs.algs4.StdOut;

public class Task_20 {

    private static double factLn_conservative(int n) {
        long prod = 1;
        while (0 < n) {
            prod *= n--;
        }
        return Math.log(prod);
    }
    
    private static double factLn(int n) {
        if (n < 2) return 0;
        else return Math.log(n) + factLn(n - 1);
    }
    
    private static boolean doublesEq(double a, double b) {
        return Math.abs(a - b) < 0.00001;
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            if (doublesEq(factLn(i), factLn_conservative(i))) continue;
            StdOut.printf("Error: %d\n", i);
            System.exit(-1);
        }
        StdOut.println("OK");
    }
}
