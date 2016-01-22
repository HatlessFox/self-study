package part_1.chapter_1;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Task_32 {

    private static final int DIM = 2048;
    private static int n;
    private static double lo, hi;
    
    private static void updateHistogram(int[] histogram, double value) {
        if (value < lo || hi <= value) return;
        
        int bucket = (int) Math.floor((value - lo) / ((hi - lo) / n));
        histogram[bucket]++;
    }
    
    private static int[] readHistogram(int n, double lo, double hi) {
        // init state
        Task_32.n = n;
        Task_32.lo = lo;
        Task_32.hi = hi;
        int[] hist = new int[n];
        
        // read values
        while (!StdIn.isEmpty()) {
            updateHistogram(hist, StdIn.readDouble());
        }
        return hist;
    }
    
    /* Draw histograms */
    
    private static int max(int[] hist) {
        int mx = Integer.MIN_VALUE;
        for (int e : hist) {
            if (mx < e) mx = e;
        }
        return mx;
    }
    
    private static void drawHistogram(int[] hist) {
        // setup
        StdDraw.setCanvasSize(512, 512);
        StdDraw.setScale(0, DIM);
        
        // draw bars
        double max_height = max(hist) * 1.3;
        double bar_width = DIM / (hist.length + 1);
        for (int i = 0; i < n; i++) {
            // i + 1 = i + 0.5 (offset) + 0.5 (half)
            double bar_height = hist[i]/max_height*DIM;
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledRectangle((i+1)*bar_width, bar_height/2, bar_width/2, bar_height/2);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledRectangle((i+1)*bar_width, 0.1*DIM, bar_width/8, 0.1*DIM);
        }
    }
    
    private static void printHistogram(int[] hist) {
        for (int e:hist) StdOut.printf("%d ", e);
    }
    
    public static void main(String[] args) {
        if (args.length < 3) {
            StdOut.println("Args: n, lo, hi");
            System.exit(-1);
        }
        
        int[] hist = readHistogram(Integer.parseInt(args[0]),
                                   Double.parseDouble(args[1]),
                                   Double.parseDouble(args[2]));
        drawHistogram(hist);
        printHistogram(hist);
    }
}
