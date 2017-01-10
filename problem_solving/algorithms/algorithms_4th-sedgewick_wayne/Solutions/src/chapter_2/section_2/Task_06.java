package chapter_2.section_2;

import chapter_2.SortingUtils;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import utils.LangUtils;

/*
 * Task 2.2.6
 * 
 * Write a program to compute the exact value of the number of array accesses used by top-down
 * mergesort and by bottom-up mergesort. Use your program to plot the values for N from 1 to 512,
 * and to compare the exact values with the upper bound 6N logN.
 */
public class Task_06 {

    public static class MergeSort {
        public int arr_access_nm;
        private int[] aux;
        
        private void prepare_aux(int[] arr) {
            aux = new int[arr.length];
        }
        
        private void merge(int[] arr, int lo, int mid, int hi) {
            if (hi <= lo) return;
            System.arraycopy(arr, lo, aux, lo, hi - lo + 1);
            arr_access_nm += hi - lo + 1;
            
            int lo_i = lo, hi_i = mid + 1, dst_i = lo;
            while (dst_i <= hi) {
                boolean use_hi = hi_i <= hi && (mid < lo_i || aux[hi_i] < aux[lo_i]);
                arr[dst_i++] = aux[use_hi ? hi_i++ : lo_i++];
                arr_access_nm += 2;
            }
        }
        
        private void top_down_sort(int[] arr, int lo, int hi) {
            if (hi <= lo) return;
            
            int mid = lo + (hi - lo) / 2;
            top_down_sort(arr, lo, mid);
            top_down_sort(arr, mid + 1, hi);
            merge(arr, lo, mid, hi);
        }
        
        public void top_down_sort(int[] arr) {
            prepare_aux(arr);
            top_down_sort(arr, 0, arr.length - 1);
        }
        
        public void bottom_up_sort(int[] arr) {
            prepare_aux(arr);
            
            int n = arr.length;
            for (int sz = 1; sz < n; sz *= 2) {
                for (int lo = 0; lo < n - sz; lo += sz + sz) {
                    merge(arr, lo, lo + sz - 1, Math.min(n - 1, lo + sz + sz - 1));
                }
            }
        }
    }
    
    public static double expected_nm_of_arr_acc(int n) {
        return 6 * n * Math.log(n);
    }
    
    private static final int N_TRIALS = 768;
    private static final int HEIGHT = 480;
    private static final int WIDTH = 640;
    private static final int RADIUS = 3;
    
    public static void main(String[] args) {
        int[] td_arr_acc_nm = new int[N_TRIALS + 1];
        int[] bu_arr_acc_nm = new int[N_TRIALS + 1];
        int[] exp_arr_acc_nm = new int[N_TRIALS + 1];
        int y_max = 0;
        
        // generate data
        for (int n = 1; n <= N_TRIALS; ++n) {
            int[] arr_td = LangUtils.rnd_ints(n);
            MergeSort ms_td = new MergeSort();
            ms_td.top_down_sort(arr_td);
            SortingUtils.is_sorted(arr_td);
            td_arr_acc_nm[n] = ms_td.arr_access_nm;
            y_max = Math.max(y_max, td_arr_acc_nm[n]);
            
            int[] arr_bu = LangUtils.rnd_ints(n); 
            MergeSort ms_bu = new MergeSort();
            ms_bu.bottom_up_sort(arr_bu);
            SortingUtils.is_sorted(arr_bu);
            bu_arr_acc_nm[n] = ms_bu.arr_access_nm;
            y_max = Math.max(y_max, bu_arr_acc_nm[n]);
            
            exp_arr_acc_nm[n] = (int) expected_nm_of_arr_acc(n);
            y_max = Math.max(y_max, exp_arr_acc_nm[n]);
        }
        
        // plot data
        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.setPenRadius(0.005);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        
        double x_step = 1.0 * WIDTH / N_TRIALS;
        double y_step = 1.0 * HEIGHT / y_max;
        for (int i = 1; i < N_TRIALS; i++) {
            double x = x_step * i;
            
            StdDraw.setPenColor(StdDraw.GREEN);
            StdDraw.filledCircle(x, y_step * exp_arr_acc_nm[i], RADIUS);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(x, y_step * td_arr_acc_nm[i], RADIUS);
            StdDraw.setPenColor(StdDraw.YELLOW);
            StdDraw.filledCircle(x, y_step * bu_arr_acc_nm[i], RADIUS);
        }
        
        StdOut.println("Done.");
    }
}
