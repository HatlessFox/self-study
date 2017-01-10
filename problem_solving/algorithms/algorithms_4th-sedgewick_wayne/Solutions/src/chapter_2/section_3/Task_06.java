package chapter_2.section_3;

import chapter_2.SortingUtils;
import edu.princeton.cs.algs4.StdOut;
import utils.LangUtils;

/*
 * Task 2.3.6
 * 
 * Write a program to compute the exact value of C_N, and compare the exact value with the approx.
 * 2N ln N, for N = 100, 1000 and 10000.
 */
public class Task_06 {
    
    public static int c_n = 0;
    
    public static boolean less(int arr[], int i, int j) {
        c_n++;
        return arr[i] < arr[j];
    }
    
    public static int partition(int arr[], int lo, int hi) {
        int lo_i = lo + 1, hi_i = hi;
        
        while (true) {
            while (less(arr, lo_i, lo) && lo_i != hi) {
                lo_i++;
            }
            while (less(arr, lo, hi_i) && lo != hi_i) {
                hi_i--;
            }
            if (hi_i <= lo_i) break;
            SortingUtils.exch(arr, lo_i++, hi_i--);
        }
        SortingUtils.exch(arr, lo, hi_i);
        return hi_i;
    }
    
    public static int[] qsort(int arr[], int lo, int hi) {
        if (hi <= lo) return arr;
        
        int mid = partition(arr, lo, hi);
        qsort(arr, lo, mid - 1);
        qsort(arr, mid + 1, hi);
        return arr;
    }
    
    public static int[] qsort(int arr[]) {
        return qsort(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        for (int n : LangUtils.ints2arr(100, 1000, 10000)) {
            c_n = 0;
            SortingUtils.is_sorted(qsort(LangUtils.rnd_ints(n)));
            StdOut.printf("%.2f %d\n", 2 * n * Math.log(n), c_n);
        }
        StdOut.println("DONE");
    }
}
