package utils;

import java.util.Random;

import edu.princeton.cs.algs4.StdOut;

public class LangUtils {

    public static int[] ints2arr(int... ints) { return ints; }
    public static double[] dbls2arr(double... dbls) { return dbls; }
    
    public static void print_arr(int[] arr) {
        for (int i : arr) {
            StdOut.printf("%d ", i);
        }
        StdOut.println();
    }
    
    public static double[] rndDoubles(int size) {
        Random rnd = new Random();
        double[] result = new double[size];

        for (int i = 0; i < result.length; i++) {
            result[i] = rnd.nextDouble();
        }

        return result;
    }
    
    public static int[] rnd_ints(int size) {
        return rnd_ints(size, 1000);
    }
    
    public static int[] rnd_ints(int size, int max) {
        Random rnd = new Random();
        int[] result = new int[size];
        
        for (int i = 0; i < result.length; i++) {
            result[i] = rnd.nextInt(max);
        }
        
        return result;
    }
    
    public static void exch(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
