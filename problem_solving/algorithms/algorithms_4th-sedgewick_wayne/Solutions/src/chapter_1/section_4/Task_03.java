package chapter_1.section_4;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
 * Task 1.4.3
 * 
 * Modify DoublingTest to use StdDraw to produce plots like the standard and log-log plots in the
 * text, rescaling as necessary so that the plot always fills a substantial portion of the window. 
 */
public class Task_03 {
    public static double timeTrial(int n) {
        final int MAX = 1_000_000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
        
        for (int i : arr) {
            for (int j : arr) {
                for (int k : arr) {
                    if (i + j + k % 1_000_007 == 0)
                        break;
                }
            }
        }
        
        return timer.elapsedTime();
    }

    public interface Plotter2D {
        public void addEntry(double x, double y);
    }
    
    public static class StandardPlotter2D implements Plotter2D {
        private boolean refresh_is_required = false;
        private static final double DFLT_PT_SZ = 0.01;
        protected double x_max = 50.0;
        protected double y_max = 50.0;
        private Map<Double, Double> entries = new HashMap<>();
        
        {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(DFLT_PT_SZ);
        }
        
        protected void updateXMax(double new_val) {
            x_max = new_val;
        }
        
        protected void updateYMax(double new_val) {
            y_max = new_val;
        }
        
        @Override public void addEntry(double x, double y) {
            StdOut.printf("%.3f %.3f\n", x, y);
            entries.put(x, y);
            StdDraw.point(x, y);
            
            if (x_max < x) {
                updateXMax(x);
                refresh_is_required = true;
            }
            if (y_max < y) {
                updateYMax(y);
                refresh_is_required = true;
            }
            rescale();
        }
        
        private void rescale() {
            if (!refresh_is_required)
                return;
            
            StdOut.printf("X_SCALE: %f; Y_SCALE: %f.\n", x_max, y_max);
            StdDraw.clear();
            StdDraw.setXscale(0, x_max);
            StdDraw.setYscale(0, y_max);
            entries.forEach(StdDraw::point);
            refresh_is_required = false;
        }
    }
    
    public static class LogLogPlotter2D extends StandardPlotter2D{
        private double leastExceedingPower(double val, double limit) {
            while (val < limit) {
                val *= 10;
            }
            return val;
        }
        
        @Override
        protected void updateXMax(double new_value) {
            x_max = leastExceedingPower(x_max, new_value);
        }
        
        @Override
        protected void updateYMax(double new_value) {
            y_max = leastExceedingPower(y_max, new_value);
        }
    }
    
    public static class Range implements Iterator<Integer>{
        private int st, end, step;
        public Range(int st, int end, int step) {
            this.st = st;
            this.end = end;
            this.step = step;
        }
        
        @Override public boolean hasNext() { return st < end; }

        @Override
        public Integer next() {
            int curr = st;
            st += step;
            return curr;
        }
    }
    
    private static final boolean SHOW_STD = false;
    
    public static void main(String[] args) {
        Iterator<Integer> arg_iterator = new Range(100, Integer.MAX_VALUE, 100);
        Plotter2D plotter = SHOW_STD ? new StandardPlotter2D() : new LogLogPlotter2D();
        
        arg_iterator.forEachRemaining(e -> plotter.addEntry(e, 1000*timeTrial(e)));
    }
}
