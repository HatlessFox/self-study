package chapter_3.section_5;

import java.util.Arrays;

import chapter_3.section_5.common.TestSet;
import edu.princeton.cs.algs4.StdOut;
import utils.TestUtils;

/*
 * Task 3.5.6
 * 
 * Develop classes HashSETint and HashSETdouble for maintaining sets of keys of primitive int and
 * double types, respectively. (Eliminate code involving values in your solution on Exercise 3.5.4.)
 */
public class Exercise_06 {

    public static class HashSETint implements TestSet.SET<Integer> {
        private static final double TARGET_LOAD_FACTOR = 0.5;
        private static final int EMPTY_KEY = -1;
        
        private int[] keys;
        private int sz;
        
        public HashSETint() { this(2); }
        private HashSETint(int sz) {
            keys = new int[sz];
            Arrays.fill(keys, EMPTY_KEY);
        }
        
        private boolean isKeyOccupied(int i) { return keys[i] != EMPTY_KEY; } 
        
        private void resize(int sz) {
            HashSETint new_set = new HashSETint(sz);
            for (int i = 0; i < keys.length; ++i) {
                if (isKeyOccupied(i)) { new_set.add(keys[i]); }
            }
            
            keys = new_set.keys;
            sz = new_set.sz;
        }
        
        private int hashCode(int key) { return (Integer.hashCode(key) & 0x7FFFFFFF) % keys.length; }
        
        private int key2ind(int key) {
            int key_i = hashCode(key);
            while (isKeyOccupied(key_i)) {
                if (keys[key_i] == key) { return key_i; }
                key_i = (key_i + 1) % keys.length;
            }
            return key_i;
        }
        
        @Override
        public void add(Integer key) {
            double curr_load_factor = 1.0 * sz / keys.length;
            if (TARGET_LOAD_FACTOR <= curr_load_factor) { resize(2 * keys.length); }
            
            int key_i = key2ind(key);
            if (!isKeyOccupied(key_i)) { ++sz; }
            
            keys[key_i] = key;
        }

        private void clearCell(int i) {
            keys[i] = EMPTY_KEY;
            --sz;
        }
        
        @Override
        public void delete(Integer key) {
            int key_i = key2ind(key);
            if (!isKeyOccupied(key_i)) { return; }
            
            clearCell(key_i);
            while (true) {
                key_i = (key_i + 1) % keys.length;
                if (!isKeyOccupied(key_i)) { break; }
                
                int key_to_reinsert = keys[key_i];
                clearCell(key_i);
                add(key_to_reinsert);
            }
            double curr_load_factor = 1.0 * sz / keys.length;
            if (2*curr_load_factor <= TARGET_LOAD_FACTOR) { resize((keys.length + 1) / 2); }
        }

        @Override
        public boolean contains(Integer key) { return isKeyOccupied(key2ind(key)); }
        @Override
        public boolean isEmpty() { return size() == 0; }
        @Override
        public int size() { return sz; }
    }
    
    public static class HashSETdouble implements TestSet.SET<Double> {
        private static final double TARGET_LOAD_FACTOR = 0.5;
        private static final double EMPTY_KEY = Double.NaN;
        
        private double[] keys;
        private int sz;
        
        public HashSETdouble() { this(2); }
        private HashSETdouble(int sz) {
            keys = new double[sz];
            Arrays.fill(keys, EMPTY_KEY);
        }
        
        private boolean isCellOccupied(int i) { return !Double.isNaN(keys[i]); } 
        
        private void resize(int sz) {
            HashSETdouble new_set = new HashSETdouble(sz);
            for (int i = 0; i < keys.length; ++i) {
                if (isCellOccupied(i)) { new_set.add(keys[i]); }
            }
            
            keys = new_set.keys;
            sz = new_set.sz;
        }
        
        private int hashCode(double key) {
            return (Double.hashCode(key) & 0x7FFFFFFF) % keys.length;
        }
        
        private int key2ind(double key) {
            int key_i = hashCode(key);
            while (isCellOccupied(key_i)) {
                if (keys[key_i] == key) { return key_i; }
                key_i = (key_i + 1) % keys.length;
            }
            return key_i;
        }
        
        @Override
        public void add(Double key) {
            double curr_load_factor = 1.0 * sz / keys.length;
            if (TARGET_LOAD_FACTOR <= curr_load_factor) { resize(2 * keys.length); }
            
            int key_i = key2ind(key);
            if (!isCellOccupied(key_i)) { ++sz; }
            
            keys[key_i] = key;
        }

        private void clearCell(int i) {
            keys[i] = EMPTY_KEY;
            --sz;
        }
        
        @Override
        public void delete(Double key) {
            int key_i = key2ind(key);
            if (!isCellOccupied(key_i)) { return; }
            
            clearCell(key_i);
            while (true) {
                key_i = (key_i + 1) % keys.length;
                if (!isCellOccupied(key_i)) { break; }
                
                double key_to_reinsert = keys[key_i];
                clearCell(key_i);
                add(key_to_reinsert);
            }
            double curr_load_factor = 1.0 * sz / keys.length;
            if (2*curr_load_factor <= TARGET_LOAD_FACTOR) { resize((keys.length + 1) / 2); }
        }

        @Override
        public boolean contains(Double key) { return isCellOccupied(key2ind(key)); }
        @Override
        public boolean isEmpty() { return size() == 0; }
        @Override
        public int size() { return sz; }
    }
    
    public static void testSetDouble(TestSet.SET<Double> set, int elems) {
        TestUtils.fail_if(!set.isEmpty() || set.size() != 0, "Init size/emptiness is not correct");
        for (int i = 0; i < elems; i++) { set.add(1.0 * i); }
        for (int i = 0; i < 2*elems; i++) {
            TestUtils.fail_if(i < elems && !set.contains(1.0 * i), "Set doesn't contain element");
            TestUtils.fail_if(elems < i && set.contains(1.0 * i), "Set contain unexpected element");
        }
        for (int i = 0; i < elems; i++) {
            set.delete(1.0 * i);
            TestUtils.fail_if(set.contains(1.0 * i) || set.size() != elems - i - 1,
                              String.format("Delete failed: %s", i));
        }
        TestUtils.fail_if(!set.isEmpty(), "Set is not empty");
        StdOut.println("OK");
    }
    
    public static void main(String[] args) {
        TestSet.testSet(new HashSETint(), 7);
        testSetDouble(new HashSETdouble(), 7);
    }
}
