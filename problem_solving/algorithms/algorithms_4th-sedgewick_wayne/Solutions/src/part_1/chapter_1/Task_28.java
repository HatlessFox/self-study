package part_1.chapter_1;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Task_28 {
    
    private static int rank(int k, int[] a) {
        int lo = 0, hi = a.length-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            if      (a[mid] < k)   lo = mid + 1;
            else if (a[mid] == k)  return mid;
            else /*  k < a[mid] */ hi = mid - 1;
        }
        return -1;
    }
    
    private static int[] removeDuplicates(int[] sorted_a) {
        int unique_cnt = 1;
        for (int i = 1; i < sorted_a.length; i++) {
            if (sorted_a[i-1] == sorted_a[i]) continue;
            sorted_a[unique_cnt++] = sorted_a[i];
        }
        return Arrays.copyOfRange(sorted_a, 0, unique_cnt);
    }
    
    public static void main(String[] args) {
        if (args.length < 1) {
            StdOut.println("Args: <whitelist file>");
            System.exit(-1);
        }
        
        int[] wl = new In(args[0]).readAllInts();
        Arrays.sort(wl);
        wl = removeDuplicates(wl);
        
        while (!StdIn.isEmpty()) {
            int e = StdIn.readInt();
            if (rank(e, wl) != -1) continue;
            StdOut.println(e);
        }
    }
}
