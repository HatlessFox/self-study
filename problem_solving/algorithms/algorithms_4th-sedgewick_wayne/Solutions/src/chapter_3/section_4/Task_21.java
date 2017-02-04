package chapter_3.section_4;

import java.util.Random;

import chapter_3.section_4.Task_10.LinearProbingHashST;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 3.4.21
 * 
 * Add a method to LinearProbingHashST that computes the averages cost of a search _miss_
 * in the table, assuming a random hash function. Note: You do not have to compute any hash function
 * to solve this problem.
 */
public class Task_21 {
    public static class AvgCostLPrHashST<Key, Value> extends LinearProbingHashST<Key, Value> {
        public AvgCostLPrHashST(int table_sz) { super(table_sz, false); }
        
        public double estimateAvgSearchMissCost() {
            final int keys_sz = keys.length;
            
            int lim = 0;
            while (keys[lim] != null) {
                lim++;
                if (lim == keys_sz) { return -1; } // the setup is invalid
            }
            
            double srch_miss_cost = 0;
            int claster_len = 0;
            boolean fst_run = true;
            for (int key_i = lim; key_i != lim || fst_run; key_i = (key_i + 1) % keys_sz) {
                fst_run = false;
                if (keys[key_i] == null) {
                    claster_len = 1;
                } else {
                    claster_len += 1;
                }
                //StdOut.printf("[%d]%s -> %d", key_i, keys[key_i], claster_len);
                srch_miss_cost += claster_len;
            }
            
            return srch_miss_cost / keys_sz;
        }
        
        private int last_srch_cost = 0;
        public int lastSearchCost() { return last_srch_cost; }
        
        @Override
        public Value get(Key k) {
            last_srch_cost = 1;
            
            for (int i = hashCode(k); keys[i] != null; i = (i + 1) % keys.length) {
                if (k.equals(keys[i])) {
                    return vals[i];
                }
                last_srch_cost += 1;
            }
            //StdOut.printf("%d -> %d\n", hashCode(k), last_srch_cost);
            return null;
        }
    }
    
    public static final int TBL_SZ = 20;
    public static final int N = 10;
    public static final int TRIALS = 1000000;
    public static void main(String[] args) {
        AvgCostLPrHashST<Integer, Integer> st = new AvgCostLPrHashST<>(TBL_SZ);
        Random rnd = new Random();
        for (int i = 0; i < N; ++i) {
            st.put(rnd.nextInt(TBL_SZ * 10), i);
        }
        //st.dump();
        
        double srch_cost = 0;
        for (int trial = 0; trial < TRIALS; ++trial) {
            int key = 0;
            while (true) {
                key = rnd.nextInt(TBL_SZ * 100);
                if (!st.contains(key)) { break; }
            }
            srch_cost += st.lastSearchCost();
            //StdOut.printf("%d %d\n", keys[k_i], st.lastSearchCost());
        }
        StdOut.printf("Expected: %.3f; Actual: %.3f\n",
                      st.estimateAvgSearchMissCost(), srch_cost / TRIALS);
    }
    
}
