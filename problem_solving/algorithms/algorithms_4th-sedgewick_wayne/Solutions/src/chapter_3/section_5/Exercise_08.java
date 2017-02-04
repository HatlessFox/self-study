package chapter_3.section_5;

import edu.princeton.cs.algs4.StdOut;
import utils.TestUtils;

/*
 * Task 3.5.8
 * 
 * Modify LinearProbingHashST to keep duplicate keys in the table. Return _any_ value
 * associated with the given key for get(), and remove _all_ nodes in the tree that have keys equal
 * to the given key for delete().
 */
public class Exercise_08 {

    public static class DupLinearProbingHashST<Key, Value> {
        public static final double TARGET_LOAD_FACTOR = 0.5;
        
        private Key[] keys;
        private Value[] vals;
        private int sz;
        
        @SuppressWarnings("unchecked")
        private DupLinearProbingHashST(int sz) {
            keys = (Key[]) new Object[sz];
            vals = (Value[]) new Object[sz];
            sz = 0;
        }
        
        public DupLinearProbingHashST() { this(2); }
        
        private void resize(int sz) {
            DupLinearProbingHashST<Key, Value> st = new DupLinearProbingHashST<>(sz);
            for (int i = 0; i < keys.length; ++i) {
                if (keys[i] == null) { continue; }
                st.put(keys[i], vals[i]);
            }
            
            keys = st.keys;
            vals = st.vals;
            sz = st.sz;
        }
        
        private int hashCode(Key k) { return (k.hashCode() & 0x7FFFFFFF) % keys.length; }
        private int key2ind(Key k) {
            int key_i = hashCode(k);
            while (keys[key_i] != null) {
                if (keys[key_i] == k) { return key_i; }
                key_i = (key_i + 1) % keys.length;
            }
            return key_i;
        }
        
        public Value get(Key key) { return vals[key2ind(key)]; }
        
        public void put(Key k, Value v) {
            double curr_load_factor = 1.0 * sz / keys.length;
            if (TARGET_LOAD_FACTOR <= curr_load_factor) { resize(2 * keys.length); }
            
            int insert_i = hashCode(k);
            while (keys[insert_i] != null) {
                insert_i = (insert_i + 1) % keys.length;
            }
            keys[insert_i] = k;
            vals[insert_i] = v;
            ++sz;
        }
        
        private void clearCell(int i) {
            keys[i] = null;
            vals[i] = null;
            --sz;
        }
        public void delete(Key key) {
            int key_i = key2ind(key);
            if (keys[key_i] == null) { return; }
            
            clearCell(key_i);
            while (true) {
                key_i = (key_i + 1) % keys.length;
                if (keys[key_i] == null) { break; }
                Key k = keys[key_i];
                Value v = vals[key_i];
                
                clearCell(key_i);
                put(k, v);
            }
            double curr_load_factor = 1.0 * sz / keys.length;
            if (curr_load_factor <= TARGET_LOAD_FACTOR / 2) { resize(keys.length / 2); }
        }
        
        public boolean contains(Key key) { return keys[key2ind(key)] == key; }
    }
    
    private static final int ELEM_NM = 5;
    private static final int DUP_NM = 3;
    public static void main(String[] args) {
        DupLinearProbingHashST<Integer, Double> bst = new DupLinearProbingHashST<>();
        for (int i = 0; i < ELEM_NM; ++i) {
            for (int j = 1; j < 1 + DUP_NM; ++j) {
                bst.put(i, 1.0 * i / j);
            }
        }
        for (int i = 0; i < 2*ELEM_NM; ++i) {
            TestUtils.fail_if(i < ELEM_NM && !bst.contains(i), "[+] Contains failed.");
            TestUtils.fail_if(ELEM_NM < i && bst.contains(i), "[-] Contains failed.");
        }
        for (int i = 0; i < ELEM_NM; ++i) {
            for (int j = 1; j < 1 + DUP_NM; ++j) {
                bst.delete(i);
                if (j != DUP_NM) {
                  TestUtils.fail_if(!bst.contains(i), "[del-+] Contains failed.");
                }
            }
            TestUtils.fail_if(bst.contains(i), "[del-] Contains failed.");
        }
        StdOut.println("OK");
    }
}
