package chapter_3.section_5;

import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.ST;

/*
 * Task 3.5.1
 * 
 * Implement SET and HashSET as "wrapper class" clients of ST and HashST, respectively
 * (provide dummy values and ignore them).
 */
public class Exercise_01 {
    
    public static class SET<Key extends Comparable<Key>> {
        private static final Object MOCK_VALUE = new Object();
        private ST<Key, Object> st;
        
        public SET() { st = new ST<>(); }
        
        public void add(Key key) { st.put(key, MOCK_VALUE); }
        public void delete(Key key) { st.delete(key); }
        public boolean contains(Key key) { return st.contains(key); }
        public boolean isEmpty() { return st.isEmpty(); }
        public int size() { return st.size(); }
    }
    
    public static class HashSET<Key> {
        private static final Object MOCK_VALUE = new Object();
        private LinearProbingHashST<Key, Object> st;
        
        public HashSET() { st = new LinearProbingHashST<>(); }
        
        public void add(Key key) { st.put(key, MOCK_VALUE); }
        public void delete(Key key) { st.delete(key); }
        public boolean contains(Key key) { return st.contains(key); }
        public boolean isEmpty() { return st.isEmpty(); }
        public int size() { return st.size(); }
    }
}
