package part_1.chapter_1;

import java.util.Random;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.1.31: Random connections
 * 
 * Write a program that takes as command-line argument an integer N and a double value p
 * (between 0 and 1), plots N equally spaced dots of size .05 on the circumference of a circle,
 * and then, with probability p for each pair of points, draws a gray line connecting them.
 */
public class Task_31 {

    private static final boolean IS_DATA_SRC_CMD = true;
    private static final int DIM = 2048;
    private static final int RADIUS = DIM / 3;
    private static int N = 20;
    private static double P = .1;
    private static Random rnd = new Random();
    
    private static class Dot {
        public double x, y;
        public Dot(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
    
    private static Dot[] generateDots(int n) {
        int center_x = DIM/2, center_y = DIM/2;
        
        Dot[] dots = new Dot[n];
        for (int i = 0; i < n; i++) {
            dots[i] = new Dot(center_x + Math.cos(i*2*Math.PI/n) * RADIUS,
                              center_y + Math.sin(i*2*Math.PI/n) * RADIUS);
        }
        return dots;
    }
    
    private static void addLines(Dot[] dots) {
        for (int i = 0; i < dots.length; i++) {
            for (int j = 0; j < dots.length; j++) {
                if (i == j) continue;
                if (rnd.nextDouble() <= P) {
                    drawLine(dots[i], dots[j]);
                }
            }
        }
    }
    
    /* Client */
    
    private static void setupData(String[] args) {
        if (!IS_DATA_SRC_CMD) return;
        if (args.length < 2) {
            StdOut.println("Args: N and P are required");
            System.exit(-1);
        }
        N = Integer.parseInt(args[0]);
        P = Double.parseDouble(args[1]);
    }
    
    private static void setupScreen() {
       StdDraw.setCanvasSize(512, 512);
       StdDraw.setScale(0, DIM);
    }
    
    private static void drawDots(Dot[] dots) {
        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (Dot dot : dots) {
            StdDraw.point(dot.x, dot.y);
        }
    }
    
    private static void drawLine(Dot dot1, Dot dot2) {
        StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.line(dot1.x, dot1.y, dot2.x, dot2.y);
    }
    
    public static void main(String[] args) {
        setupData(args);
        setupScreen();
        Dot[] dots = generateDots(N);
        addLines(dots);
        drawDots(dots);
    }
}
