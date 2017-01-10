package chapter_2.section_2;

import chapter_2.SortingUtils;
import edu.princeton.cs.algs4.StdOut;
import utils.LangUtils;

/*
 * Task 2.2.9
 * 
 * Use of a static array like aux[] is inadvisable in library software because multiple clients
 * might use the class concurrently. Give an implementation of Merge that does not use a static
 * array. Do _not_ make aux[] local to merge() (see the Q&A for this section).
 * 
 * _Hint_: Pass the auxiliary array as an argument to the recursive sort().
 */
public class Task_09 {

    private static void merge(int[] arr, int[] aux, int lo, int mid, int hi) {
        if (hi <= lo) return;
        System.arraycopy(arr, lo, aux, lo, hi - lo + 1);
        
        int lo_i = lo, hi_i = mid + 1, dst_i = lo;
        while (dst_i <= hi) {
            boolean use_hi = hi_i <= hi && (mid < lo_i || aux[hi_i] < aux[lo_i]);
            arr[dst_i++] = aux[use_hi ? hi_i++ : lo_i++];
        }
    }
    
    private static void bu_merge_sort(int[] arr, int[] aux) {
        int n = arr.length;
        for (int sz = 1; sz < n; sz *= 2) {
            for (int lo = 0; lo < n - sz; lo += sz * 2) {
                merge(arr, aux, lo, lo + sz - 1, Math.min(n - 1, lo + sz + sz - 1));
            }
        }
    }
    
    private static void sort(int[] arr) {
        bu_merge_sort(arr, new int[arr.length]);
    }
    
    public static void main(String[] args) {
        for (int i = 1; i < 512; i++) {
            int[] arr = LangUtils.rnd_ints(i);
            sort(arr);
            SortingUtils.is_sorted(arr);
        }
        StdOut.println("Done");
    }
}
