package chapter_1.section_1;

import edu.princeton.cs.algs4.StdDraw;

/*
 * Web Exercise 4: Bouncing ball
 * 
 * Write a program that illustrates the motion of a bouncing ball.
 */
public class Web_04 {

    private static final int WIDTH = 640;
    private static final int HEIGHT = 480; 
    
    private static abstract class SimulationObject {
        private double x;
        private double y;
        private double x_sz;
        private double y_sz;
        private double vel_x;
        private double vel_y;
        
        public double getX() { return x; }
        public void setX(double x) { this.x = x; }
        
        public double getY() { return y; }
        public void setY(double y) { this.y = y; }
        
        public double getXSz() { return x_sz; }
        public void setXSz(double x_sz) { this.x_sz = x_sz; }
        
        public double getYSz() { return y_sz; }
        public void setYSz(double y_sz) { this.y_sz = y_sz; }
        
        
        public double getVelX() { return vel_x; }
        public void setVelX(double vel_x) { this.vel_x = vel_x; }
        
        public double getVelY() { return vel_y; }
        public void setVelY(double vel_y) { this.vel_y = vel_y; }
        
        public abstract void update(double time_delta);
        public abstract void draw();
    }
    
    private static class Ball2D extends SimulationObject{
        private static final double RADIUS = 10;
        
        public Ball2D(double x, double y, double vel_x, double vel_y) {
            setX(x); setY(y);
            setXSz(RADIUS); setYSz(RADIUS);
            setVelX(vel_x); setVelY(vel_y);
        }

        @Override
        public void update(double time_delta) {
            setX(getX() + getVelX() * time_delta);
            setY(getY() + getVelY() * time_delta);
        }
        
        @Override
        public void draw() {
            StdDraw.filledCircle(getX(), getY(), RADIUS);
        }
    }
    
    private static void setupDisplay() {
        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.setPenRadius(0.005);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
    }
    
    private static void runSimulation() {
        setupDisplay();
        SimulationObject[] objs = new SimulationObject[] {
                new Ball2D(100, 100, 1, 1)};
        
        while (true) {
            for (SimulationObject so : objs) {
                so.update(1);
            }
            handleCollisions(objs);
            
            StdDraw.clear(StdDraw.LIGHT_GRAY);
            for (SimulationObject so : objs) {
                so.draw();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }
    
    private static void handleCollisions(SimulationObject[] objs) {
        // handle frame collisions
        for (SimulationObject so : objs) {
            if (so.getX() - so.getXSz() < 0) {
                so.setX(so.getXSz());
                so.setVelX(-1*so.getVelX());
            }
            if (so.getY() - so.getYSz() < 0) {
                so.setY(so.getYSz());
                so.setVelY(-1*so.getVelY());
            }
            if (WIDTH < so.getX() + so.getXSz()) {
                so.setX(WIDTH - so.getXSz());
                so.setVelX(-1*so.getVelX());
            }
            if (HEIGHT < so.getY() + so.getXSz()) {
                so.setY(HEIGHT - so.getYSz());
                so.setVelY(-1*so.getVelY());
            }
        }
    }
    
    public static void main(String[] args) {
        runSimulation();
    }
}
