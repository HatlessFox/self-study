package chapter_3.common;

import edu.princeton.cs.algs4.StdOut;

public class Utils {

    public static <K, V> void dumpST(ST<K, V> st, String sep) {
        for (K k : st.keys()) {
            StdOut.printf("%s: %s%s", k, st.get(k), sep);
        }
        StdOut.println();
    }
}
