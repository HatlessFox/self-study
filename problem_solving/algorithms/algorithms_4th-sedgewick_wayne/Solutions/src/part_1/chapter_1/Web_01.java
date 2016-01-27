package part_1.chapter_1;

import java.util.Random;

import edu.princeton.cs.algs4.StdOut;

/*
 * Web Exercise 1: Sattolo's algorithm
 * 
 * Write a program that generates a uniformly distributed cycle of length N using
 * Sattolo's algorithm.
 */
public class Web_01 {

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
    private static void permutateWithSattolo(int[] arr) {
        Random rnd = new Random();
        for (int i = arr.length - 1; 0 < i; i--) {
            int new_i = rnd.nextInt(i + 1);
            swap(arr, i, new_i);
        }
    }
    
    private static int[] generateTestArray(int arr_sz) {
        int[] arr = new int[arr_sz];
        for (int i = 0; i < arr_sz; i++) {
            arr[i] = i;
        }
        return arr;
    }
    
    private static double[][] generateStatistics(int arr_sz, int trials) {
        double[][] stat = new double[arr_sz][arr_sz];
        for (int t = 0; t < trials; t++) {
            int[] arr = generateTestArray(arr_sz);
            permutateWithSattolo(arr);
            //update statistics
            for (int i = 0; i < arr.length; i++) {
                stat[arr[i]][i]++;
            }
        }
        
        // normalize statistics
        for (double[] row : stat) {
            for (int i = 0; i < row.length; i++) {
                row[i] /= trials;
            }
        }
        return stat;
    }
    
    private static void printStatistics(double[][] stat) {
        for (double[] row : stat) {
            for (double e : row) {
                StdOut.printf("%.2f ", e);
            }
            StdOut.println();
        }
    }
    
    public static void main(String[] args) {
        int arr_sz = 10, trials = 10000;
        printStatistics(generateStatistics(arr_sz, trials));
    }
}
