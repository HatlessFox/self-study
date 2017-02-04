package chapter_3.section_1;

import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 3.1.11
 * 
 * Give a trace of the process of inserting the keys EASYQUESTION into an initially empty table
 * using BinarySearchST. How many compares are involved?
 */
public class Task_11 {

    public static class BinarySearchST<Key extends Comparable<Key>, Value> {
        private Key[] keys;
        private Value[] vals;
        private int sz;
        
        private Set<Integer> cmp_keys = new HashSet<>();
        private int altered_i = -1;
        private boolean suffix_move_was_performed = false;
        
        @SuppressWarnings("unchecked")
        public BinarySearchST(int cap) {
            sz = 0;
            keys = (Key[]) new Comparable[cap];
            vals = (Value[]) new Object[cap];
        }
        
        public int size() { return sz; }
        
        public int rank(Key k) {
            cmp_keys.clear();

            int lo = 0, hi = sz - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                int cmp_res = keys[mid].compareTo(k);
                cmp_keys.add(mid);
                
                if (cmp_res < 0) { lo = mid + 1; }
                else if (0 < cmp_res) { hi = mid - 1; }
                else { return mid; }
            }
            return lo;
        }
        
        private void resize(int cap) {
            int curr_cap = keys.length;
            if (cap <= curr_cap) { return; }
            
            int new_cap = Math.max(cap, 2 * curr_cap);
            
            @SuppressWarnings("unchecked")
            Key[] new_keys = (Key[]) new Comparable[new_cap];
            System.arraycopy(keys, 0, new_keys, 0, sz);
            keys = new_keys;
            
            @SuppressWarnings("unchecked")
            Value[] new_vals = (Value[]) new Object[new_cap];
            System.arraycopy(vals, 0, new_vals, 0, sz);
            vals = new_vals;
        }
        
        public void put(Key k, Value v) {
            suffix_move_was_performed = false;
            
            int i = rank(k);
            altered_i = i;
            if (i < sz && keys[i].compareTo(k) == 0) {
                vals[i] = v;
                return;
            }
            resize(sz + 1);
            suffix_move_was_performed = true;

            int suff_sz = sz - i;
            System.arraycopy(keys, i, keys, i + 1, suff_sz);
            System.arraycopy(vals, i, vals, i + 1, suff_sz);
            
            keys[i] = k;
            vals[i] = v;
            sz += 1;
        }
        
        public void dumpLastPutLog() {
            StdOut.printf("Cmps: %d; ", cmp_keys.size());
            for (int i = 0; i < sz; i++) {
                String entry = String.format("%s|%s", keys[i], vals[i]);
                if (cmp_keys.contains(i)) {
                    entry = "<" + entry + ">";
                }
                if (i == altered_i) {
                    entry = "(" + entry + ")";
                } else if (altered_i < i && suffix_move_was_performed) {
                    entry = "[" + entry + "]";
                }
                StdOut.printf("%s ", entry);
            }
            StdOut.println();
        }
    }
    
    public static final String TEST_STRING = "EASYQUESTION";
    
    public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST<>(1);
        for (int i = 0; i < TEST_STRING.length(); ++i) {
            char new_key = TEST_STRING.charAt(i);
            StdOut.printf("= Add %c = ", new_key);
            st.put(Character.toString(new_key), i);
            st.dumpLastPutLog();
        }
    }
}
