package chapter_3.section_1;

import chapter_3.common.FrequencyCounter;
import chapter_3.common.ST;
import edu.princeton.cs.algs4.StdOut;
import utils.LangUtils;

/*
 * Task 3.1.7
 * 
 * What is the average number of distinct keys that FrequencyCounter will find among N random
 * nonnegative integers less than 1,000, for N=10, 10**2, 10**3, 10**4, 10**5, and 10**6?
 */
public class Task_07 {

    private static final int INT_LIM = 1000;
    
    public static void main(String[] args) {
        for (int p : LangUtils.ints2arr(1, 2, 3, 4, 5, 6)) {
            int n = (int)Math.pow(10, p);
            int[] data = LangUtils.rnd_ints(n, INT_LIM);
            
            ST<String, Integer> st = new Task_02.ArrayST<>(n);
            FrequencyCounter fc = new FrequencyCounter(st, 0);
            for (int e : data) {
                fc.addWord(Integer.toString(e));
            }
            StdOut.printf("%d: %d\n", n, st.size());
        }
        StdOut.println("Done");
    }
}
