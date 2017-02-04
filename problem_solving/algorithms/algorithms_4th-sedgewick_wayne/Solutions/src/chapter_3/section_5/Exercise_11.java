package chapter_3.section_5;

import edu.princeton.cs.algs4.StdOut;
import utils.TestUtils;

/*
 * Task 3.5.11
 * 
 * Develop a MultiSET class that is like SET, but allows equal keys
 * and thus implements a mathematical _multiset_.
 */
public class Exercise_11 {

    public static class MultiSET<Key> {
        private static final double TARGET_LOAD_FACTOR = 0.5;
        private Key[] keys;
        private int sz;
        
        public MultiSET() { this(2); }
        @SuppressWarnings("unchecked") private MultiSET(int sz) { keys = (Key[]) new Object[sz]; }
        
        private void resize(int sz) {
            MultiSET<Key> new_set = new MultiSET<>(sz);
            for (int i = 0; i < keys.length; ++i) {
                if (keys[i] == null) { continue; }
                new_set.add(keys[i]);
            }
            
            keys = new_set.keys;
            sz = new_set.sz;
        }
        
        private int hashCode(Key key) { return (key.hashCode() & 0x7FFFFFFF) % keys.length; }
        
        private int key2ind(Key key) {
            int key_i = hashCode(key);
            while (keys[key_i] != null) {
                if (keys[key_i] == key) { return key_i; }
                key_i = (key_i + 1) % keys.length;
            }
            return key_i;
        }
        
        public void add(Key key) {
            double curr_load_factor = 1.0 * sz / keys.length;
            if (TARGET_LOAD_FACTOR <= curr_load_factor) { resize(2*keys.length); }
            
            int key_i = hashCode(key);
            while (keys[key_i] != null) {
                key_i = (key_i + 1) % keys.length;
            }
            
            keys[key_i] = key;
            ++sz;
        }
        
        private void clearCell(int i) {
            keys[i] = null;
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
                clearCell(key_i);
                add(k);
             }
            
            double curr_load_factor = 1.0 * sz / keys.length;
            if (2.0 * curr_load_factor <= TARGET_LOAD_FACTOR) { resize((keys.length + 1) / 2); }
        }
        
        public boolean contains(Key key) { return keys[key2ind(key)] != null; }
        public boolean isEmpty() { return size() == 0; }
        public int size() { return sz; }
    }
    
    private static final int ELEM_NM = 7;
    private static final int DUP_NM = 5;
    public static void main(String[] args) {
        MultiSET<Integer> mset = new MultiSET<>();
        for (int i = 0; i < ELEM_NM; ++i) {
            for (int j = 1; j < 1 + DUP_NM; ++j) {
                mset.add(i);
            }
        }
        for (int i = 0; i < 2*ELEM_NM; ++i) {
            TestUtils.fail_if(i < ELEM_NM && !mset.contains(i), "[+] Contains failed.");
            TestUtils.fail_if(ELEM_NM < i && mset.contains(i), "[-] Contains failed.");
        }
        for (int i = 0; i < ELEM_NM; ++i) {
            for (int j = 1; j < 1 + DUP_NM; ++j) {
                mset.delete(i);
                if (j != DUP_NM) {
                  TestUtils.fail_if(!mset.contains(i), "[del-+] Contains failed.");
                }
            }
            TestUtils.fail_if(mset.contains(i), "[del-] Contains failed.");
        }
        StdOut.println("OK");
    }
}
