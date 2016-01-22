package part_1.chapter_1;

import edu.princeton.cs.algs4.StdOut;

public class Task_07 {

    private static void doSectionA() {
        double t = 9.0;
        while (0.001 < Math.abs(t - 9.0/t)) {
            t = (9.0/t + t) / 2.0;
        }
        StdOut.printf("%.5f\n", t);
    }
    
    private static void doSectionB() {
        int sum = 0;
        for (int i = 1; i < 1000; i++) {
            for (int j = 0; j < i; j++) {
                sum++;
            }
        }
        StdOut.println(sum);
    }
    
    private static void doSectionC() {
        int sum = 0;
        for (int i = 1; i < 1000; i *= 2) {
            for (int j = 0; j < 1000; j++) {
                sum++;
            }
        }
        StdOut.println(sum);
    }
    
    public static void main(String[] args) {
        doSectionA();
        doSectionB();
        doSectionC();
    }
}
