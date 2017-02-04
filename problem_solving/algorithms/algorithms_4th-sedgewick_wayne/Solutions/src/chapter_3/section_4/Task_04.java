package chapter_3.section_4;

import edu.princeton.cs.algs4.StdOut;
import utils.LangUtils;

/*
 * Task 3.4.4
 * 
 * Write a program to find values of a and M, with M as small as possible,
 * such that the hash function (a * k) % M for transforming the kth letter of the alphabet
 * into a table index produces distinct values (no collisions) for the keys SERCHXMPL.
 * The result is known as a perfect hash function.
 */
public class Task_04 {

    private static class PerfectCollisionTester {
        private int a, m;
        private boolean hashes[];
        
        public PerfectCollisionTester(int a, int m) {
            this.a = a;
            this.m = m;
            hashes = new boolean[this.m];
        }
        
        private int hashCode(char ch) { return a * (ch - 'A') % hashes.length; }
        
        public boolean put(char ch) {
            int hash_code = hashCode(ch);
            if (hashes[hash_code]) { return true; }
            hashes[hash_code] = true;
            return false;
        }
    }
    
    private static boolean isPerfectHashParams(int a, int m, char[] chrs) {
        PerfectCollisionTester pct = new PerfectCollisionTester(a, m);
        for (char ch : chrs) {
            if (pct.put(ch)) {
                return false;
            }
        }
        return true;
    }
    
    private static final int M_LIM = 100, A_LIM = 32;
    private static final char[] CHRS = LangUtils.chrs2arr('S', 'E', 'A', 'R', 'C', 'H',
                                                          'X', 'M', 'P', 'L'); 
    public static void main(String[] args) {
        for (int m = 1; m < M_LIM; ++m) {
            for (int a = 2; a < A_LIM; ++a) {
                if (isPerfectHashParams(a, m, CHRS)) {
                    StdOut.printf("Perfect hash function: %d * k %% %d\n", a, m);
                    return;
                }
            }
        }
        StdOut.println("Unable to find perfect hash function.");
    }
}
