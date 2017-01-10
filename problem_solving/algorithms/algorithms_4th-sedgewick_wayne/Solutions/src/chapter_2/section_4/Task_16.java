package chapter_2.section_4;

import edu.princeton.cs.algs4.StdOut;
import utils.LangUtils;

/*
 * Task 2.4.16
 * 
 * For N=32, give arrays of items that make heapsort use as _many_ and as _few_ compares as
 * possible.
 */
public class Task_16 {

    public static class HeapSort {
        int cmp_nm = 0;
        
        int cmpNumber() { return cmp_nm; }
        
        void sort(int[] arr) {
            int n = arr.length;
            
            // heapify
            for (int k = (n - 1) / 2; 0 <= k; --k) {
                sink(arr, k, n);
            }
            
            // sort
            while (0 < n) {
                LangUtils.exch(arr, 0, --n);
                sink(arr, 0, n);
            }
        }
        
        public void sink(int arr[], int k, int sz) {
            while (true) {
                int ch_i = 2 * k + 1;
                if (sz <= ch_i || sz <= k) break;
                if (ch_i + 1 < sz && less(arr, ch_i, ch_i + 1)) {
                    ch_i += 1;
                }
                if (arr[ch_i] < arr[k]) break;
                LangUtils.exch(arr, k, ch_i);
                k = ch_i;
            }
        }
        
        public boolean less(int arr[], int i, int j) {
            cmp_nm++;
            return arr[i] < arr[j];
        }
        
        public void reset() { cmp_nm = 0; }
    }
    
    public static int N = 32;
    public static int TRIALS_NM = 10;
    
    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] < arr[i-1]) { return false; }
        }
        return true;
    }
    
    public static void main(String[] args) {
        HeapSort hs = new HeapSort();
        
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) { arr[i] = 0; }
        hs.sort(arr);
        StdOut.printf("%b DUPS: %d\n", isSorted(arr), hs.cmpNumber());
        for (int i = 0; i < N; i++) { arr[i] = N - i - 1; }
        hs.reset();
        hs.sort(arr);
        StdOut.printf("%b DESC: %d\n", isSorted(arr), hs.cmpNumber());
        
        for (int trials = 0; trials < TRIALS_NM; ++trials) {
            int[] data = LangUtils.rnd_ints(N);
            hs.reset();
            hs.sort(data);
            StdOut.printf("%b RND: %d\n", isSorted(arr), hs.cmpNumber());
        }
    }
}
