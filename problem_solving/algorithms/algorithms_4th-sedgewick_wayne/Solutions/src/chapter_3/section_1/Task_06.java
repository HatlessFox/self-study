package chapter_3.section_1;

import chapter_3.common.FrequencyCounter;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
 * Task 3.1.6
 * 
 * Give the number of calls to put() and get() issued by FrequencyCounter,
 * as a function of the number W of words and the number D of distinct words in the input.
 */
public class Task_06 {

    public static class AccountingST<Key, Value> extends Task_02.ArrayST<Key, Value> {
        public int putOpsNm, getOpsNm;
        
        public AccountingST(int cap) {
            super(cap);
        }
        
        @Override
        public void put(Key k, Value v) {
            putOpsNm++;
            super.put(k, v);
        }
        
        @Override
        public Value get(Key k) {
            getOpsNm++;
            return super.get(k);
        }
    }
    
    public static final int W = 500;
    public static final int D = 17;
    
    private static void initFC(FrequencyCounter fc) {
        Integer[] unique_elems = new Integer[D];
        for (int i = 0; i < unique_elems.length; ++i) {
            unique_elems[i] = i;
        }
        for (int w_i = 0; w_i < W; ++w_i) {
            fc.addWord(unique_elems[StdRandom.uniform(unique_elems.length)].toString());
        }
    }
    
    public static void main(String[] args) {
        AccountingST<String, Integer> st = new AccountingST<>(10);
        FrequencyCounter fc = new FrequencyCounter(st, 0);
        initFC(fc);
        StdOut.printf("Most freq: %s\n", fc.mostFrequentWord());
        
        StdOut.printf("Puts: %d (actual) vs %d (expected)\n", st.putOpsNm,
                                                              (W) + (1));
        StdOut.printf("Gets: %d (actual) vs %d (expected)\n", st.getOpsNm,
                                                              (W + W - D) + (2*(D + 1)));
    }
}
