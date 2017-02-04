package chapter_3.section_4;

import java.util.Random;

import chapter_3.section_4.Task_10.LinearProbingHashST;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 3.4.20
 * 
 * Add a method to LinearProbingHashST that computes the average cost of a search hit in the table,
 * assuming that each key in the table is equally likely to be sought;
 */
public class Task_20 {

    public static class AvgCostLPrHashST<Key, Value> extends LinearProbingHashST<Key, Value> {
        public AvgCostLPrHashST(int table_sz) { super(table_sz, false); }
        
        public double estimateAvgSearchHitCost() {
            final int keys_sz = keys.length;
            
            int lim = 0;
            while (keys[lim] != null) {
                lim++;
                if (lim == keys_sz) { return -1; } // the setup is invalid
            }
            
            double srch_hit_cost = 0;
            boolean fst_run = true;
            for (int key_i = lim; key_i != lim || fst_run; key_i = (key_i + 1) % keys_sz) {
                fst_run = false;
                if (keys[key_i] == null) { continue; }
                
                int hash_code = hashCode(keys[key_i]);
                int key_srch_cost = key_i + 1 - hash_code + (hash_code <= key_i ? 0 : keys_sz);
                //StdOut.printf("[%d]%s: %d -> %d\n", key_i, keys[key_i], hash_code, key_srch_cost);
                srch_hit_cost += key_srch_cost;
            }
            
            return srch_hit_cost / size();
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
        
        int keys[] = new int[N], keys_sz = 0;
        for (Integer k : st.keys()) { keys[keys_sz++] = k; }
        
        double srch_cost = 0;
        for (int trial = 0; trial < TRIALS; ++trial) {
            st.contains(keys[rnd.nextInt(keys_sz)]);
            srch_cost += st.lastSearchCost();
            //StdOut.printf("%d %d\n", keys[k_i], st.lastSearchCost());
        }
        StdOut.printf("Expected: %.3f; Actual: %.3f\n",
                      st.estimateAvgSearchHitCost(), srch_cost / TRIALS);
    }
}
