package chapter_2.section_3;

import edu.princeton.cs.algs4.StdOut;
import utils.LangUtils;

/*
 * Task 2.3.5
 * 
 * Give a code fragment that sorts an array that is known to consist of items having just two
 * distinct keys.
 */
public class Task_05 {

    public static void exch(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
    public static int[] sort_two_distinct(int[] arr) {
        int next_lt = 0, next_gt = arr.length - 1, unknown = 0;
        
        while (unknown <= next_gt) {
            int cmp = arr[unknown] - arr[next_lt];
            switch (cmp) {
            case -1: exch(arr, unknown++, next_lt++); break;
            case 0: unknown++; break;
            case 1: exch(arr, unknown, next_gt--); break;
            }
            //LangUtils.print_arr(arr);
        }
        return arr;
    }
    
    public static void main(String[] args) {
        StdOut.println("-----");
        LangUtils.print_arr(sort_two_distinct(LangUtils.ints2arr(0, 1, 0, 1, 0)));
        StdOut.println("-----");
        LangUtils.print_arr(sort_two_distinct(LangUtils.ints2arr(1, 0, 1, 0, 1)));
        StdOut.println("-----");
        LangUtils.print_arr(sort_two_distinct(LangUtils.ints2arr(1, 1, 1, 0, 0)));
        StdOut.println("-----");
        LangUtils.print_arr(sort_two_distinct(LangUtils.ints2arr(0, 0, 1, 1, 1)));
    }
}
