package part_1.chapter_2;

import java.util.Random;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.2.1
 * 
 * Write a Point2D client that takes an integer value N from the command line, generates N random 
 * points in the unit square, and computes the distance separating the closest pair of points.
 */
public class Task_01 {

    private static Point2D[] generatePoints(int n) {
        Random rnd_gen = new Random();
        Point2D[] points = new Point2D[n];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point2D(rnd_gen.nextDouble(), rnd_gen.nextDouble());
        }
        return points;
    }
    
    private static double findMinDistance(Point2D[] pts) {
        if (pts.length == 1) return 0;
        
        double min_dist_sq = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i+1; j < pts.length; j++) {
                double dist_sq = pts[i].distanceSquaredTo(pts[j]);
                min_dist_sq = Math.min(min_dist_sq, dist_sq);
            }
        }
        return Math.sqrt(min_dist_sq);
    }
    
    public static void main(String[] args) {
        if (args.length < 1) {
            StdOut.println("Args: <number of points>");
            System.exit(-1);
        }
        StdOut.println(findMinDistance(generatePoints(Integer.parseInt(args[0]))));
    }
    
}
