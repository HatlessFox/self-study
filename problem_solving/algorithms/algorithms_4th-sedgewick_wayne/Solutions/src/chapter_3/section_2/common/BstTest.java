package chapter_3.section_2.common;

import chapter_3.section_1.Task_04.Event;
import edu.princeton.cs.algs4.StdOut;

public class BstTest {
    
    public static interface BST<Key extends Comparable<Key>, Value> {
        public int size();
        
        public Value get(Key key);
        public void put(Key key, Value value);
        
        public Key min();
        public Key max();
        
        public Key floor(Key key);
        public Key ceiling(Key key);
        
        public Key select(int k);
        public int rank(Key key);
        
        public void deleteMin();
        public void deleteMax();
        public void delete(Key key);
        
        public Iterable<Key> keys();
        public Iterable<Key> keys(Key lo, Key hi);
    }
    
    public static void addWithTestData(BST<Event, String> bst) {
        bst.put(new Event(9, 21, 5), "Chicago");
        bst.put(new Event(9, 25, 52), "Chicago");
        bst.put(new Event(9, 37, 44), "Phoenix");
        bst.put(new Event(9, 0, 13), "Houston");
        bst.put(new Event(9, 3, 13), "Chicago");
        bst.put(new Event(9, 14, 25), "Phoenix");
        
        bst.put(new Event(9, 10, 25), "Seattle");
        bst.put(new Event(9, 22, 54), "Seattle");
        bst.put(new Event(9, 0, 3), "Phoenix");
        bst.put(new Event(9, 1, 10), "Houston");
        bst.put(new Event(9, 19, 46), "Chicago");
        bst.put(new Event(9, 36, 14), "Seattle");
        
        bst.put(new Event(9, 00, 00), "Chicago");
        bst.put(new Event(9, 19, 32), "Chicago");
        bst.put(new Event(9, 10, 11), "Seattle");
        bst.put(new Event(9, 00, 59), "Chicago");
        bst.put(new Event(9, 35, 21), "Chicago");
        bst.put(new Event(9, 22, 43), "Seattle");
    }
    
    public static void dumpBst(BST<Event, String> bst, String sep) {
        for (Event k : bst.keys()) {
            StdOut.printf("%s: %s%s", k, bst.get(k), sep);
        }
        StdOut.println();
    }
    
    public static void testBstReduced(BST<Event, String> bst) {
        StdOut.println("== DUMP ==");
        BstTest.dumpBst(bst, "\n");
        
        StdOut.printf("min(): %s\n", bst.min());
        StdOut.printf("get(09:00:13): %s\n", bst.get(new Event(9, 0, 13)));
        StdOut.printf("floor(09:05:00): %s\n", bst.floor(new Event(9, 5, 0)));
        StdOut.printf("ceiling(09:30:00): %s\n", bst.ceiling(new Event(9, 35, 0)));
        StdOut.printf("max(): %s\n", bst.max());
        StdOut.println("== DUMP Inverval ==");
        for (Event k : bst.keys(new Event(9, 15, 0), new Event(9, 25, 0))) {
            StdOut.printf("%s %s\n", k, bst.get(k));
        }
    }
    
    public static void testBst(BST<Event, String> bst) {
        addWithTestData(bst);
        testBstReduced(bst);
        
        int sz = bst.size();
        bst.delete(new Event(9, 14, 25));
        bst.deleteMin();
        bst.deleteMax();
        StdOut.println("== DUMP after Delete ==");
        for (Event k : bst.keys()) {
            StdOut.printf("%s %s\n", k, bst.get(k));
        }
        StdOut.printf("SZ: before - %d; after - %d\n", sz, bst.size()); 
    }
}
