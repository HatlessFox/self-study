package part_1.chapter_1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Task_24 {

    public static int gcd(int a, int b) { return gcd(a, b, false); }
    
    private static int gcd(int a, int b, boolean tracing_is_enabled) {
        if (tracing_is_enabled) StdOut.printf("%10d, %10d;\n", a, b);
        if (b == 0) return a;
        else return gcd(b, a % b, tracing_is_enabled);
    }
    
    public static void main(String[] args) {
        StdOut.print("Enter a & b: ");
        int a = StdIn.readInt(), b = StdIn.readInt();
        StdOut.printf("GCD is %d\n", gcd(a, b, true));
    }
}
