package chapter_3.section_2;

import edu.princeton.cs.algs4.StdOut;

/*
 * Task 3.6.8
 * 
 * Write a static method optCompares() that takes an integer argument N and computes the number of
 * compares required by a random search hit in an optimal (perfectly balanced) BST with N nodes,
 * where all the null links are on the same level if the number of links is a power of 2 or on one
 * of two levels otherwise.
 */
public class Task_08 {

    public static int optCompares(int n) {
        if (n < 0) { return 0; }
        if (n < 2) { return n; }
        int left_sz = (n - 1) / 2;
        return n + optCompares(left_sz) + optCompares(n - 1 - left_sz);
    }
    
    public static double log2(double x) {
        return Math.log10(x) / Math.log10(2);
    }
    
    public static void main(String[] args) {
        StdOut.printf("%d: %s\n", 15, optCompares(15));
        StdOut.printf("%f vs %f\n", optCompares(15) / 15.0, log2(15));
    }
}
