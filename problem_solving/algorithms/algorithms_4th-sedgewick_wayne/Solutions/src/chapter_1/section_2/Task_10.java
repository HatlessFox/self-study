package chapter_1.section_2;

import java.util.Random;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.2.10
 * 
 * Develop a class VisualCounter that allows both increment and decrement operations. Take two 
 * arguments N and max in the constructor, where N specifies the maximum number of operations and 
 * max specifies the maximum absolute value for the counter. AS a side effect, create a plot 
 * showing the value of the counter each time its tally changes.
 */
public class Task_10 {

    private static class VisualCounter {
        private static final int MAX_TALLY = 100;
        
        private int n, max, tally, ops;
        
        public VisualCounter(int n, int max) {
            this.n = n;
            this.max = max;
        }
        
        public void increment(int value) {
            if (n <= ops) return;
            if (max < Math.abs(value)) return;
            
            ops++;
            tally += value;
            draw();
        }
        
        public void decrement(int value) {
            increment(-value);
        }
        
        private void draw() {
            StdDraw.filledCircle(0.01 + 0.9*ops / n,
                                 0.5 + 0.5*tally / MAX_TALLY,
                                 0.01);
        }
    }
    
    private static void setupDisplay() {
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setPenRadius(0.001);
        StdDraw.setScale(0, 1);
    }
    
    /* Tests */
    
    private static final boolean RUN_CLI = false;
    private static final double AUTO_INC_PROB = .3;
    
    private static void __cli() {
        StdOut.print("Enter <number of ops> and <max value> for the counter: ");
        VisualCounter vc = new VisualCounter(StdIn.readInt(), StdIn.readInt());
        while (true) {
            StdOut.print("Enter command (i/d <value>): ");
            switch (StdIn.readString()) {
            case "i":
                vc.increment(StdIn.readInt());
                break;
            case "d":
                vc.decrement(StdIn.readInt());
                break;
            default:
                StdOut.println("Unknown command");
                break;
            }
        }
    }
    
    private static void __runAuto() {
        Random rnd = new Random();
        
        int n = rnd.nextInt(30) + 10;
        int max = rnd.nextInt(10) + 3;
        VisualCounter vc = new VisualCounter(n, max);

        for (int i = 0; i < n; i++) {
            if (rnd.nextDouble() < AUTO_INC_PROB) {
                vc.increment(rnd.nextInt(max) + 1);
            } else {
                vc.decrement(rnd.nextInt(max) + 1);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }
    
    public static void main(String[] args) {
        setupDisplay();
        
        if (RUN_CLI) __cli(); else __runAuto();
    }
}
