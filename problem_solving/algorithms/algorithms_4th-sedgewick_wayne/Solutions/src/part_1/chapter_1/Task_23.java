package part_1.chapter_1;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Task_23 {

    private static int rank(int k, int[] a) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            int pivot = a[mid];
            if      (pivot < k)  lo = mid + 1;
            else if (pivot == k) return mid;
            else                 hi = mid - 1;
        }
        return -1;
    }
    
    private static int[] readWhiteList(String fname) {
        int[] wl = new In(fname).readAllInts();
        Arrays.sort(wl);
        return wl;
    }
    
    public static void main(String[] args) {
        if (args.length != 2) {
            StdOut.println("Args:\n\t1) Whitelist file\n\t2) + - not in wl, - -- in wl\n");
            System.exit(-1);
        }
        
        int[] wl = readWhiteList(args[0]);
        boolean printClean = args[1].equals("+");
        while (!StdIn.isEmpty()) {
            int e = StdIn.readInt();
            int rank = rank(e, wl);
            boolean shouldBePrinted = !(printClean ^ (rank == -1));
            if (shouldBePrinted) StdOut.println(e);
        }
    }
    
}
