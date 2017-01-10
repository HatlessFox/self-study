package chapter_2.section_4;

import edu.princeton.cs.algs4.StdOut;

/*
 * Task 2.4.15
 * 
 * Design a linear-time certification algorithm to check whether an array pq[] is
 * a min-oriented heap.
 */
public class Task_15 {

    public static boolean isMinHeap(char[] arr) {
        for (int k = (arr.length - 1)/ 2; 1 <= k; k--) {
            char l_ch = arr[2*k];
            char r_ch = 2*k + 1 < arr.length ? arr[2*k + 1] : Character.MAX_VALUE;
            if (!(arr[k] < l_ch && arr[k] < r_ch)) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        char arr1[] = { '\0', 'A', 'B', 'C', 'D', 'F', 'E', 'G', 'E', 'I', 'H'};
        StdOut.printf("OK vs %s\n", isMinHeap(arr1) ? "OK" : "FAIL");
        char arr2[] = { '\0', 'T', 'S', 'A', 'P', 'N', 'O', 'A', 'E', 'I', 'H', 'G'};
        StdOut.printf("FAIL vs %s\n", isMinHeap(arr2) ? "OK" : "FAIL");
    }
}
