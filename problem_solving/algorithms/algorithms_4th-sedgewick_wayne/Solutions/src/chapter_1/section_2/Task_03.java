package chapter_1.section_2;

import java.util.Random;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.2.3
 * 
 * Write an Interval2D client that takes command-line arguments N, min and max and generates N
 * random 2D intervals whose width and height are uniformly distributed between min and max in the
 * unit square. Draw them on StdDrawand print the number of pairs of intervals that intersect and
 * the number of intervals that are contained in one another.
 */
public class Task_03 {
    
    private static final Random RND = new Random();
    private static final int MC_TRIALS = 10000;
    
    private static Interval1D generate1DInterval(double min, double max) {
        double start = min + RND.nextDouble()*(max - min);
        return new Interval1D(start, start + RND.nextDouble()*(max - start));
    }
    
    private static Interval2D[] generate2DIntervals(int n, double min, double max) {
        Interval2D[] intervals = new Interval2D[n];
        for (int i = 0; i < intervals.length; i++) {
            intervals[i] = new Interval2D(generate1DInterval(min, max),
                                          generate1DInterval(min, max));
        }
        return intervals;
    }
    
    private static void showIntervals(Interval2D[] data) {
        for (Interval2D intrvl : data) {
            intrvl.draw();
        }
    }
    
    private static int calculateIntersections(Interval2D[] data) {
        int intersections_cnt = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = i+1; j < data.length; j++) {
                intersections_cnt += (data[i].intersects(data[j])) ? 1 : 0;
            }
        }
        return intersections_cnt;
    }
    
    private static boolean monteCarloContains(Interval2D i1, Interval2D i2) {
        for (int i = 0; i < MC_TRIALS; i++) {
            Point2D pnt = new Point2D(RND.nextDouble(), RND.nextDouble());
            if (!i1.contains(pnt) && i2.contains(pnt)) return false;
        }
        return true;
    }
    
    private static int calculateParentChildPairs(Interval2D[] data) {
        int parent_child_cnt = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = i+1; j < data.length; j++) {
                parent_child_cnt += monteCarloContains(data[i], data[j]) ? 1 : 0;
            }
        }
        return parent_child_cnt;
    }
    
    public static void main(String[] args) {
        if (args.length < 3) {
            StdOut.println("Args: <number of 2D intervals> <min> <max>");
            System.exit(-1);
        }
        
        int n = Integer.parseInt(args[0]);
        double min = Double.parseDouble(args[1]), max = Double.parseDouble(args[2]);
        
        Interval2D[] intervals = generate2DIntervals(n, min, max);
        showIntervals(intervals);
        //print statistics
        StdOut.printf("Intersections: %d\n", calculateIntersections(intervals));
        StdOut.printf("Parent-Child pairs: %d\n", calculateParentChildPairs(intervals));
        
    }
}
