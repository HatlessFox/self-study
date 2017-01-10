package chapter_2.section_5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import utils.LangUtils;

/*
 * Task 2.5.6
 * 
 * Implement a recursive version of select().
 */
public class Task_06 {

    private static int partition(int[] data, int lo, int hi) {
        if (hi <= lo) return lo;
        
        int ins_i = lo + 1;
        for (int i = lo; i <= hi; ++i) {
            if (data[i] < data[lo]) {
                LangUtils.exch(data, ins_i++, i);
            }
        }
        int mid = ins_i - 1;
        LangUtils.exch(data, lo, mid);
        
        return mid;
    }
    
    private static int select(int[] data, int lo, int k, int hi) {
        if (hi <= lo) return data[k];
        int m = partition(data, lo, hi);
        if      (m < k) { lo = m + 1; }
        else if (k < m) { hi = m - 1; }
        else { return data[k]; }
        
        return select(data, lo, k, hi);
    }
    
    public static int select(int[] data, int k) {
        return select(data, 0, k, data.length - 1);
    }
    
    // Tests
    
    public static final int N = 10;
    
    public static void main(String[] args) {
        int[] data = new int[N];
        for (int i = 0; i < N; i++) { data[i] = i; }
        
        for (int i = 0; i < N; i++) {
            StdRandom.shuffle(data);
            if (select(data, i) != i) {
                StdOut.printf("Failed: %d.\n", i);
                System.exit(-1);
            }
        }
        StdOut.println("OK.");
    }
}
