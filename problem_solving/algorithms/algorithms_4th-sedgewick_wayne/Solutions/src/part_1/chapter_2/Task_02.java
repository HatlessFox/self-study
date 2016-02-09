package part_1.chapter_2;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Task_02 {

    private static Interval1D readInterval() {
        StdOut.print("Enter intrval bounds (left and right): ");
        return new Interval1D(StdIn.readInt(), StdIn.readInt());
    }
    
    public static void main(String[] args) {
        if (args.length < 1) {
            StdOut.println("Args: <number of intervals>");
            System.exit(-1);
        }
        int interval_cnt = Integer.parseInt(args[0]);
        Interval1D[] intervals = new Interval1D[interval_cnt];
        for (int i = 0; i < interval_cnt; i++) {
            intervals[i] = readInterval();
        }
        
        for (Interval1D interval : intervals) {
            StdOut.println(interval);
        }
    }
}
