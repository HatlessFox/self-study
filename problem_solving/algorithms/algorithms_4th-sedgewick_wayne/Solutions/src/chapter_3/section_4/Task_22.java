package chapter_3.section_4;

import edu.princeton.cs.algs4.StdOut;

/*
 * Task 3.4.22
 * 
 * Implement hashCode() for various types: Point2D, Interval, Interval2D.
 */
public class Task_22 {
    
    public static final int M = 2147483647;
    
    public static class Point2D {
        private double x, y;
        
        public Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int hashCode() {
            return (int)((Double.doubleToLongBits(x) * 11 + Double.doubleToLongBits(y)) % M);
        }
    }
    
    public static class Interval {
        private double l, r;
        
        public Interval(double left, double right) {
            l = left;
            r = right;
        }
        
        @Override
        public int hashCode() {
            return (int)((Double.doubleToLongBits(l) * 11 + Double.doubleToLongBits(r)) % M);
        }
    }
    
    public static class Interval2D {
        private Interval h, w;
        
        public Interval2D(Interval height, Interval width) {
            h = height;
            w = width;
        }
        
        @Override
        public int hashCode() {
            return (h.hashCode() * 11 + w.hashCode()) % M;
        }
    }
    
    public static void main(String[] args) {
        Point2D pnt = new Point2D(2, 6);
        Interval i1 = new Interval(2, 8), i2 = new Interval(6, 4);
        Interval2D i2d = new Interval2D(i1, i2);
        
        StdOut.printf("%s %d\n", pnt.getClass(), pnt.hashCode());
        StdOut.printf("%s %d\n", i1.getClass(), i1.hashCode());
        StdOut.printf("%s %d\n", i2.getClass(), i2.hashCode());
        StdOut.printf("%s %d\n", i2d.getClass(), i2d.hashCode());
    }
}
