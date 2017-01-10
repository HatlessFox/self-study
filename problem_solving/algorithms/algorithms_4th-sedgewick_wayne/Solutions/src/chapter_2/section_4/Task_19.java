package chapter_2.section_4;

import edu.princeton.cs.algs4.StdOut;
import utils.LangUtils;

/*
 * Task 2.4.19
 * 
 * Implement the constructor for MaxPQ that takes an array of items as argument, using the bottom-up
 * heap construction method described on page 323 in the text.
 */
public class Task_19 {
    
    public static int[] toMaxHeap(int[] arr) {
        for (int i = (arr.length - 1) / 2; 0 <= i; --i) {
            sink(arr, i);
        }
        return arr;
    }
    
    public static void sink(int[] arr, int i) {
        int lim = arr.length;
        while (true) {
            int ch_i = 2*i + 1;
            if (lim <= i || lim <= ch_i) { return; }
            if (ch_i + 1 < lim && arr[ch_i] < arr[ch_i + 1]) {
                ch_i++;
            }
            
            if (arr[ch_i] < arr[i]) { break; }
            LangUtils.exch(arr, i, ch_i);
            i = ch_i;
        }
    }
    
    public static boolean isMaxHeap(int[] arr) {
        return isMaxHeap(arr, 0);
    }

    private static boolean isMaxHeap(int[] arr, int k) {
        int lim = arr.length;
        int mx_child_i = 2*k + 1;
        if (lim < k || lim <= mx_child_i) { return true; }
        if (mx_child_i + 1 < arr.length && arr[mx_child_i] < arr[mx_child_i + 1]) {
            ++mx_child_i;
        }
        
        return arr[mx_child_i] <= arr[k] && isMaxHeap(arr, 2*k + 1) && isMaxHeap(arr, 2*k + 2);
    }

    public static int TRIALS_NM = 30;
    public static void main(String[] args) {
        for (int i = 0; i < TRIALS_NM; ++i) {
            int[] data = LangUtils.rnd_ints(100);
            if (!isMaxHeap(toMaxHeap(data))) {
                LangUtils.print_arr(data);
                StdOut.println("FAILED");
                return;
            }
        }
        StdOut.println("OK");
    }
}
