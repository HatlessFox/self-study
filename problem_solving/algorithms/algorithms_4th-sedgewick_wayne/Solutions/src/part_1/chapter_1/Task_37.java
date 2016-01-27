package part_1.chapter_1;

import java.util.Random;

import edu.princeton.cs.algs4.StdOut;

/* 
 * Task 1.1.37: Bad Shuffling
 * 
 * Suppose that you choose a random integer between 0 and N-1 in our shuffling code instead of one
 * between i and N-1. Show that the resulting order is _not_ equally likely to be one of the N!
 * possibilities. Run the test of previous exercise for this version. 
 */
public class Task_37 {

    private static Random rnd = new Random();
    
    private static void shuffle(int[] a) {
        for (int pos = 0; pos < a.length; pos++) {
            int next_element_i = rnd.nextInt(a.length);
            if (pos != next_element_i) {
                int tmp = a[pos];
                a[pos] = a[next_element_i];
                a[next_element_i] = tmp;
            }
        }
    }

    private static int[] generateTestArray(int arr_sz) {
        int[] arr = new int[arr_sz];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        return arr;
    }
    
    private static int[][] generateStat(int trials_cnt, int arr_sz) {
        int[][] occur = new int[arr_sz][arr_sz];
        for (int i = 0; i < trials_cnt; i++) {
            int[] array = generateTestArray(arr_sz);
            shuffle(array);
            
            for (int j = 0; j < array.length; j++) {
                occur[array[j]][j]++;
            }
        }
        return occur;
    }
    
    private static void displayResults(int[][] occurreces, int trials, int arr_sz) {
        double expected_occur = 1.0*trials / arr_sz;
        StdOut.printf("N/M = %.3f\n", expected_occur);

        // display raw data
        StdOut.println("== Raw Data ==");
        String fmt_string = String.format("%%%dd ",
                Integer.toString((int)expected_occur).length() + 1);
        for (int[] row : occurreces) {
            for (int e : row) {
                StdOut.printf(fmt_string, e);
            }
            StdOut.println();
        }
        
        // display relative error
        StdOut.println("== Discrepancy ==");
        for (int[] row : occurreces) {
            for (int e : row) {
                long rel_error = Math.round(Math.abs(e - expected_occur) / expected_occur * 100);
                StdOut.printf("%4d%% ", rel_error);
            }
            StdOut.println();
        }
    }
    
    public static void main(String[] args) {
        if (args.length < 2) {
            StdOut.println("Args: N - trials, M - size of array to be shuffled");
            System.exit(-1);
        }
        int n = Integer.parseInt(args[0]), m = Integer.parseInt(args[1]);
        displayResults(generateStat(n, m), n, m);
    }
}
