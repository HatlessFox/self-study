package chapter_3.section_5.common;

import edu.princeton.cs.algs4.StdOut;
import utils.TestUtils;

public class TestSet {

    public static interface SET<Key> {
        public void add(Key key);
        public void delete(Key key);
        public boolean contains(Key key);
        public boolean isEmpty();
        public int size();
    }
    
    public static void testSet(SET<Integer> set, int elems) {
        TestUtils.fail_if(!set.isEmpty() || set.size() != 0, "Init size/emptiness is not correct");
        for (int i = 0; i < elems; i++) { set.add(i); }
        
        for (int i = 0; i < 2*elems; i++) {
            TestUtils.fail_if(i < elems && !set.contains(i), "Set doesn't contain element");
            TestUtils.fail_if(elems < i && set.contains(i), "Set contain unexpected element");
        }
        for (int i = 0; i < elems; i++) {
            set.delete(i);
            TestUtils.fail_if(set.contains(i) || set.size() != elems - i - 1,
                              String.format("Delete failed: %s", i));
        }
        TestUtils.fail_if(!set.isEmpty(), "Set is not empty");
        StdOut.println("OK");
    }
}
