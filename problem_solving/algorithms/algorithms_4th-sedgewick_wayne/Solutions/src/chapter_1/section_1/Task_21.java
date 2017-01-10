package chapter_1.section_1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Task_21 {
    
    public static void main(String[] args) {
        while (StdIn.hasNextLine()) {
            String name = StdIn.readString();
            int a = StdIn.readInt(), b = StdIn.readInt();
            
            StdOut.printf("%s: %d %d %.3f\n", name, a, b, 1.0*a / b);
        }
    }
}
