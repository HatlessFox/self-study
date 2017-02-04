package utils;

import edu.princeton.cs.algs4.StdOut;

public class TestUtils {
    public static void fail_if(boolean expr, String msg) {
        if (!expr) { return; }
        StdOut.println(msg);
        System.exit(-1);
    }
}
