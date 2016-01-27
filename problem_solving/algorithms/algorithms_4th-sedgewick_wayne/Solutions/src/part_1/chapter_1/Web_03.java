package part_1.chapter_1;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;

/*
 * Web Exercise 3: Right triangle
 * 
 * Write a client that draws a right triangle and a circumscribing circle.
 */
public class Web_03 {

    private static int RADIUS = 30;
    private static int CENTER_X = 50;
    private static int CENTER_Y = 50;
    
    private static double toRadians(double degrees) {
        return Math.PI / 180 * degrees;
    }
    
    private static Point2D createPointOnCircle(double angle_deg) {
        return new Point2D(
                CENTER_X + RADIUS * Math.cos(toRadians(angle_deg)),
                CENTER_Y + RADIUS * Math.sin(toRadians(angle_deg)));
    }
    
    private static void setupDisplay() {
        StdDraw.setCanvasSize(640, 640);
        StdDraw.setScale(0, 100);
        StdDraw.setPenRadius(0.005);
    }
    
    private static void drawTriangle() {
        // generate triangle points
        Point2D[] pnts = new Point2D[4];
        int angle = 135;
        for (int i = 0; i < 3; i++) {
            pnts[i] = createPointOnCircle(angle);
            angle += 90;
        }
        pnts[3] = pnts[0];
        
        // draw triangle
        for (int i = 0; i < 3; i++) {
            StdDraw.line(pnts[i].x(), pnts[i].y(), pnts[i+1].x(), pnts[i+1].y());
        }
        
        // draw circle
        StdDraw.circle(CENTER_X, CENTER_Y, RADIUS);
    }
    
    public static void main(String[] args) {
        setupDisplay();
        drawTriangle();
    }
}
