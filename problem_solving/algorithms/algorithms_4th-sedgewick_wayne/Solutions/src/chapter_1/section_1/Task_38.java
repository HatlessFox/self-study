package chapter_1.section_1;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.1.38: Binary search versus brute-force search
 * 
 * Write a program BruteForceSearch that uses the brute-force search method given on page 48 and 
 * compares its running time on your computer that of BinarySearch for largeW.txt and largeT.txt.
 */
public class Task_38 {
    
    private static abstract class Search {
        
        protected int[] data;
        private double elapsed_ms = 0;
        
        public Search(int[] data) {
            this.data = Arrays.copyOf(data, data.length);
        }
        
        protected abstract int determineRank(int k);
        
        public boolean isAlreadyStored(int k) {
            long st_time = System.nanoTime();
            int rank = determineRank(k);
            elapsed_ms += (System.nanoTime() - st_time);
            return rank != -1;
        }
        
        public double elapsedMilliSeconds() {
            return elapsed_ms / 1_000_000.0;
        }
        
    }
    
    private static class BinarySearch extends Search {
        
        public BinarySearch(int[] data) {
            super(data);
            Arrays.sort(this.data);
        }
        
        @Override
        protected int determineRank(int k) {
            int lo = 0, hi = data.length - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if      (data[mid] < k)   lo = mid + 1;
                else if (data[mid] == k)  return mid;
                else  /* k < data[mid] */ hi = mid - 1;
            }
            return -1;
        }
        
        @Override
        public String toString() { return "Binary Search"; }
    }
    
    private static class BruteForceSearch extends Search {

        public BruteForceSearch(int[] data) {
            super(data);
        }

        @Override
        public int determineRank(int k) {
            for (int i = 0; i < data.length; i++) {
                if (data[i] != k) continue;
                return i;
            }
            return -1;
        }
        
        @Override
        public String toString() { return "Brute Force Search"; }
    }

    private static int[] readWhitelist(String fname) {
        In in = new In(fname);
        return in.readAllInts();
    }
    
    private static void verifySameResults(boolean[] results) {
        boolean value = results[0];
        for (boolean e : results) {
            if (e == value) continue;
            StdOut.println("Implementation error: rank values don't match");
            System.exit(-1);
        }
    }
    
    private static Search[] compareSearches(int[] wl) {
        Search[] srch_engines = new Search[]{new BruteForceSearch(wl), new BinarySearch(wl)};
        boolean[] lookup_results = new boolean[srch_engines.length];
        
        while (!StdIn.isEmpty()) {
            int e = StdIn.readInt();
            for (int i = 0; i < srch_engines.length; i++) {
                lookup_results[i] = srch_engines[i].isAlreadyStored(e);
            }
            verifySameResults(lookup_results);
            if (lookup_results[0]) continue;
            //StdOut.println(e);
        }
        
        return srch_engines;
    }
    
    private static void displayResults(Search[] srches) {
        StdOut.println("== Elapsed time ==");
        for (Search srch : srches) {
            StdOut.printf("%s -- %.2f seconds\n", srch, 1000*srch.elapsedMilliSeconds());
        }
    }
    
    public static void main(String[] args) {
        if (args.length < 1) {
            StdOut.println("Args: <whitelist file name>");
            System.exit(-1);
        }
        
        displayResults(compareSearches(readWhitelist(args[0])));
    }
}
