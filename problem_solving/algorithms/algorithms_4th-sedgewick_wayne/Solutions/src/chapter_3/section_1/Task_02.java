package chapter_3.section_1;

import java.util.ArrayList;
import java.util.List;

import chapter_3.common.ST;
import chapter_3.common.Utils;

/*
 * Task 3.1.2
 * 
 * Develop a symbol-table implementation ArrayST that uses an (unordered) array as the underlying
 * data structures to implement our basic symbol-table API.
 */
public class Task_02 {
    
    public static class ArrayST<Key, Value> implements ST<Key, Value> {
        private static final int ABSENT_KEY_POS = -1;
        private Value[] vals;
        private Key[] keys;
        private int n;
        
        @SuppressWarnings("unchecked")
        public ArrayST(int cap) {
            vals = (Value[])new Object[cap];
            keys = (Key[]) new Object[cap];
            n = 0;
        }
        
        private int pos(Key k) {
            for (int i = 0; i < n; ++i) {
                if (!k.equals(keys[i])) { continue; }
                return i;
            }
            return ABSENT_KEY_POS;
        }
        
        private void set(int i, Key k, Value v) {
            keys[i] = k;
            vals[i] = v;
         }
        
        private void reserve(int c) {
            if (c <= keys.length) { return; }
            
            @SuppressWarnings("unchecked")
            Key[] new_keys = (Key[]) new Object[Math.min(2*n, c)];
            System.arraycopy(keys, 0, new_keys, 0, n);
            keys = new_keys;
            
            @SuppressWarnings("unchecked")
            Value[] new_vals = (Value[]) new Object[Math.min(2*n, c)];
            System.arraycopy(vals, 0, new_vals, 0, n);
            vals = new_vals;
        }
        
        @Override
        public void put(Key k, Value v) {
            int k_pos = pos(k);
            if (k_pos == ABSENT_KEY_POS) {
                reserve(n + 1);
                set(n++, k, v);
            } else {
                if (v == null) {
                    int suff_to_be_move_sz = n - k_pos - 1; 
                    System.arraycopy(keys, k_pos + 1, keys, k_pos, suff_to_be_move_sz);
                    System.arraycopy(vals, k_pos + 1, vals, k_pos, suff_to_be_move_sz);
                    set(--n, null, null);
                } else {
                    set(k_pos, k, v);
                }
            }
        }

        @Override
        public Value get(Key k) {
            int i = pos(k);
            return i == ABSENT_KEY_POS ? null : vals[i];
        }

        @Override public int size() { return n; }

        @Override
        public Iterable<Key> keys() {
            List<Key> itrbl = new ArrayList<Key>(n);
            for (int i = 0; i < n; i++) {
                itrbl.add(keys[i]);
            }
            return itrbl;
        }
    } // class ArrayST
    
    public static void main(String[] args) {
        ST<String, Integer> st = new ArrayST<>(3);
        
        st.put("A", 1);
        st.put("B", 2);
        st.put("C", 3);
        st.put("B", null);
        st.put("D", 2);
        st.put("E", 3);
        st.put("A", 5);
        st.put("B", 8);
        Utils.dumpST(st, "; "); // A: 5, C: 3, D: 2, E:3, B: 8
    }
}
