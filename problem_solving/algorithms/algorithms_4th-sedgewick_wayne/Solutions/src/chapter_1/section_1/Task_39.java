package chapter_1.section_1;

import java.util.Arrays;
import java.util.Random;

import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.1.39: Random matches
 * 
 * Write a BinarySearch client that takes n int value T as command-line argument and runs T trials
 * of the following experiment for N = 10**[3, 4, 5, 6]: generate two arrays of N randomly generated
 * positive six-digit int values and find the number of values that appear in both arrays. Print
 * a table giving the average value of this quantity over the T trials for each value of N.
 */
public class Task_39 {

    private static Random rnd = new Random();
    
    private static class Search {
        private int[] data;
        
        public Search(int[] data) {
            this.data = data;
            Arrays.sort(this.data);
        }
        
        private int bsRank(int k) {
            int lo = 0, hi = data.length - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if      (data[mid] < k)  lo = mid + 1;
                else if (data[mid] == k) return mid;
                else  /* k < data[mid] */ hi = mid - 1;
            }
            return -1;
        }
        
        public boolean doesExists(int k) {
            return bsRank(k) != -1;
        }
    }
    
    private static int[] generateTestArray(int arr_sz) {
        int[] data = new int[arr_sz];
        for (int i = 0; i < arr_sz; i++) {
            data[i] = 100_000 + rnd.nextInt(900_000);
        }
        return data;
    }
    
    private static int runTrial(int n) {
        int[] a1 = generateTestArray(n), a2 = generateTestArray(n);
        Search srch = new Search(a1);
        int matches = 0;
        for (int e : a2) {
            if (srch.doesExists(e)) matches++;
        }
        return matches;
    }
    
    private static double runExperiment(int trials, int n) {
        long total_matches = 0;
        for (int i = 0; i < trials; i++) {
            total_matches += runTrial(n);
        }
        return 1.0*total_matches / trials;
    }
    
    public static void main(String[] args) {
        if (args.length < 1) {
            StdOut.println("Args: <trials>");
            System.exit(-1);
        }
        int trials = Integer.parseInt(args[0]);
        for (int pow : new int[]{3, 4, 5, 6}) {
            int n = (int)Math.pow(10, pow);
            StdOut.printf("N = 10**%d => avg = %.2f\n", pow, runExperiment(trials, n));
        }
    }
    
}
