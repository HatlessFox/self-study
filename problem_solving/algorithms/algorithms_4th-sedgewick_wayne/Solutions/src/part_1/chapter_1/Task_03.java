package part_1.chapter_1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Task_03 {

    public static void main(String[] args) {
        StdOut.print("Enter 3 ints: ");
        int a = StdIn.readInt(), b = StdIn.readInt(), c = StdIn.readInt();
        // ints are transitive ove equals
        StdOut.println("They are " + ((a == b) && (b == c) ? "" : "not ") + "equal");
        if (a > b) c = 0;
    }
}
