package chapter_3.section_5;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import utils.TestUtils;

/*
 * Task 3.5.4
 * 
 * Develop classes HashSTint and HashSTdouble for maintaining set of keys of primitive int and
 * double types, respectively.
 * (Convert generics to primitive types in the code of LinearProbingHashST.)
 */
public class Exercise_04 {
    
    public static class HashSTint<Value> {
        private int[] keys;
        private Value[] vals;
        private int sz = 0;
        
        public HashSTint() { this(2); }

        @SuppressWarnings("unchecked")
        private HashSTint(int sz) {
            keys = new int[sz];
            vals = (Value[]) new Object[sz];
        }
        
        private void resize(int sz) {
            HashSTint<Value> new_st = new HashSTint<>(sz);
            for (int i = 0; i < keys.length; ++i) {
                if (vals[i] == null) { continue; }
                new_st.put(keys[i], vals[i]);
            }

            keys = new_st.keys;
            vals = new_st.vals;
            sz = new_st.sz;
        }
        
        private int hashCode(int k) { return (Integer.hashCode(k) & 0x7FFFFFFF) % keys.length; }
        
        private int lookupIndex(int key) {
            int table_sz = keys.length;
            int hash_i = hashCode(key);
            while (vals[hash_i] != null) {
                if (keys[hash_i] == key) { return hash_i; }
                hash_i = (hash_i + 1) % table_sz;
                //StdOut.println(hash_i);
            }
            return hash_i;
        }
        
        public void put(int key, Value val) {
            if (keys.length <= 2*sz) { resize(2*keys.length); }
            int key_ind = lookupIndex(key);

            if (vals[key_ind] == null) { ++sz; }
            keys[key_ind] = key;
            vals[key_ind] = val;
        }
        
        public void delete(int key) {
            int key_ind = lookupIndex(key);
            if (vals[key_ind] == null) { return; }
            keys[key_ind] = 0;
            vals[key_ind] = null;
            
            int table_sz = keys.length;
            while (true) {
                key_ind = (key_ind + 1) % table_sz;
                if (vals[key_ind] == null) { break; }

                int k = keys[key_ind];
                Value v = vals[key_ind];
                keys[key_ind] = 0;
                vals[key_ind] = null;
                --sz;
                put(k, v);
            }
            
            --sz;
            if (2*sz <= keys.length) { resize(keys.length / 2); }
        }
        public boolean contains(int key) { return vals[lookupIndex(key)] != null; }
        public boolean isEmpty() { return sz == 0; } 
        public int size() { return sz; }
    }
    
    public static class HashSTdouble<Value> {
        private static final double TARGET_LOAD_FACTOR = 0.25;
        private static final double ABSENT_KEY = Double.NaN;
        
        private double[] keys;
        private Value[] vals;
        private int sz = 0;
        
        public HashSTdouble() { this(2); }

        @SuppressWarnings("unchecked")
        private HashSTdouble(int sz) {
            keys = new double[sz];
            Arrays.fill(keys, ABSENT_KEY);
            vals = (Value[]) new Object[sz];
        }
        
        private void resize(int sz) {
            HashSTdouble<Value> new_st = new HashSTdouble<>(sz);
            for (int i = 0; i < keys.length; ++i) {
                if (keys[i] == ABSENT_KEY) { continue; }
                new_st.put(keys[i], vals[i]);
            }
            
            keys = new_st.keys;
            vals = new_st.vals;
            sz = new_st.sz;
        }
        
        private int hashCode(double k) {
            return (Double.hashCode(k) & 0x7FFFFFFF) % keys.length;
        }
        
        private boolean isCellOccupied(int i) { return !Double.isNaN(keys[i]); }
        
        private int lookupKeyIndex(double key) {
            int table_sz = keys.length;
            int key_i = hashCode(key) % table_sz;
            while (isCellOccupied(key_i)) {
                if (keys[key_i] == key) { return key_i; }
                key_i = (key_i + 1) % table_sz;
            }
            return key_i;
        }
        
        public void put(double key, Value val) {
            double curr_load_factor = sz / keys.length;
            if (TARGET_LOAD_FACTOR <= curr_load_factor) { resize(2*keys.length); }
            int key_i = lookupKeyIndex(key);
            if (!isCellOccupied(key_i)) { ++sz; }
            
            keys[key_i] = key;
            vals[key_i] = val;
        }
        
        private void cleanUpCell(int i) {
            keys[i] = ABSENT_KEY;
            vals[i] = null;
            --sz;
        }
        
        public void delete(double key) {
            int key_i = lookupKeyIndex(key);
            if (!isCellOccupied(key_i)) { return; }
            
            cleanUpCell(key_i);
            int table_sz = keys.length;
            while (true) {
                key_i = (key_i + 1) % table_sz;
                if (!isCellOccupied(key_i)) { break; }
                double k = keys[key_i];
                Value v = vals[key_i];
                
                cleanUpCell(key_i);
                put(k, v);
            }
            
            double curr_load_factor = sz / table_sz;
            if (curr_load_factor <= TARGET_LOAD_FACTOR / 2) { resize(table_sz / 2); }
        }
        public boolean contains(double key) { return isCellOccupied(lookupKeyIndex(key)); }
        public boolean isEmpty() { return sz == 0; } 
        public int size() { return sz; }
    }
    
    private static void testHashSTint(int elems) {
        StdOut.println("= HashSTint =");
        HashSTint<Integer> st = new HashSTint<>();
        
        TestUtils.fail_if(!st.isEmpty() || st.size() != 0, "Init size/emptiness is not correct");
        for (int i = 0; i < elems; i++) { st.put(i, i); }
        
        for (int i = 0; i < 2*elems; i++) {
            TestUtils.fail_if(i < elems && !st.contains(i),
                              String.format("ST doesn't contain element: %d", i));
            TestUtils.fail_if(elems < i && st.contains(i),
                              String.format("ST contain unexpected element: %d", i));
        }
        for (int i = 0; i < elems; i++) {
            st.delete(i);
            TestUtils.fail_if(st.contains(i) || st.size() != elems - i - 1,
                              String.format("Delete failed: %s", i));
        }
        TestUtils.fail_if(!st.isEmpty(), "ST is not empty");
        StdOut.println("OK");
    }
    
    private static void testHashSTdouble(int elems) {
        StdOut.println("= HashSTdouble =");
        HashSTdouble<Integer> st = new HashSTdouble<>();
        
        TestUtils.fail_if(!st.isEmpty() || st.size() != 0, "Init size/emptiness is not correct");
        for (int i = 0; i < elems; i++) { st.put(i / 10.0, i); }
        
        for (int i = 0; i < 2*elems; i++) {
            double k = i / 10.0;
            TestUtils.fail_if(i < elems && !st.contains(k),
                              String.format("ST doesn't contain element: %f", k));
            TestUtils.fail_if(elems < i && st.contains(k),
                              String.format("ST contain unexpected element: %f", k));
        }
        for (int i = 0; i < elems; i++) {
            double k = i / 10.0;
            st.delete(k);
            TestUtils.fail_if(st.contains(k) || st.size() != elems - i - 1,
                              String.format("Delete failed: %s", k));
        }
        TestUtils.fail_if(!st.isEmpty(), "ST is not empty");
        StdOut.println("OK");
    }
    
    private static final int ELEMS = 42;
    public static void main(String[] args) {
        testHashSTint(ELEMS);
        testHashSTdouble(ELEMS);
    }
}
