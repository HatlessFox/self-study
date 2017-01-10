package chapter_1.section_1;

import java.util.Random;

import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.1.36: Empirical shuffle check
 * 
 * Run computational experiments to check that our shuffling code works as advertised. Write a 
 * program that takes command-line arguments M and N, does N shuffles of an array of size M that is 
 * initialized with a[i] = i before each shuffle, and prints an M-by-M table such that row i gives
 * the number of times i would up in position j for all j.
 * All entries in the table should be close to N/M.
 */
public class Task_36 {

    private static Random rnd = new Random();
    private static int N = 1000;
    private static int M = 10;
    
    private static int[] generateArray(int arr_sz) {
        int[] arr = new int[arr_sz];
        for (int i = 0; i < arr.length; i++) arr[i] = i;
        return arr;
    }
    
    private static void shuffle(int[] a) {
        for (int pos = 0; pos < a.length; pos++) {
            int next_element_i = pos + rnd.nextInt(a.length - pos);
            if (pos != next_element_i) {
                int tmp = a[pos];
                a[pos] = a[next_element_i];
                a[next_element_i] = tmp;
            }
        }
    }
    
    private static int[][] generateStat(int trials_cnt, int arr_sz) {
        int[][] stat = new int[arr_sz][arr_sz];
        for (int i = 0; i < trials_cnt; i++) {
            int[] arr = generateArray(arr_sz);
            shuffle(arr);
            
            for (int ind = 0; ind < arr.length; ind++) {
                stat[arr[ind]][ind]++;
            }
        }
        return stat;
    }
    
    private static void printMtx(int[][] mtx) {
        for (int[] row : mtx) {
            for (int e : row) { StdOut.printf("%7d ", e); }
            StdOut.println();
        }
    }
    
    private static void readArgs(String[] args) {
        if (args.length < 2) {
            StdOut.println("Args: N (trial), M (array size)");
            System.exit(-1);
        }
        N = Integer.parseInt(args[0]);
        M = Integer.parseInt(args[1]);
    }
    
    public static void main(String[] args) {
        readArgs(args);
        StdOut.printf("N/M = %.3f\n", 1.0*N / M);
        printMtx(generateStat(N, M));
    }
}
