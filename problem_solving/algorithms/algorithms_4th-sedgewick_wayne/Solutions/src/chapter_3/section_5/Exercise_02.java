package chapter_3.section_5;

import java.util.LinkedList;
import java.util.Queue;

import chapter_3.section_5.common.TestSet;

/*
 * Task 3.5.2
 * 
 * Develop a SET implementation SequentialSearchSET by starting with the code for SequentialSearchST
 * and eliminating all of the code involving values.
 */
public class Exercise_02 {

    public static class SequentialSearctSET<Key> implements TestSet.SET<Key>{
        private class Node {
            Key key;
            Node next;
            public Node(Key key) { this.key = key; }
            public Node(Key key, Node next) {
                this(key);
                this.next = next;
            }
        }
        
        private Node head = new Node(null);
        
        @Override
        public void add(Key key) {
            if (contains(key)) { return; }
            head.next = new Node(key, head.next);
        }
        
        @Override
        public void delete(Key key) {
            head.next = delete(head.next, key);
        }
        
        private Node delete(Node n, Key key) {
            if (n == null) { return null; }
            if (n.key.equals(key)) { return n.next; }
            n.next = delete(n.next, key);
            return n;
        }
        
        @Override
        public boolean contains(Key key) {
            Node ptr = head.next;
            while (ptr != null) {
                if (ptr.key.equals(key)) { return true; }
                ptr = ptr.next;
            }
            return false;
        }
        
        @Override
        public boolean isEmpty() { return head.next == null; }
        
        @Override
        public int size() {
            int sz = 0;
            Node ptr = head.next;
            while (ptr != null) {
                ++sz;
                ptr = ptr.next;
            }
            return sz;
        }
        
        public Iterable<Key> keys() {
            Queue<Key> itrbl = new LinkedList<>();
            Node ptr = head.next;
            while (ptr != null) {
                itrbl.add(ptr.key);
                ptr = ptr.next;
            }
            return itrbl;
        }
    }
    
    public static final int ELEMS = 5;
    public static void main(String[] args) {
        SequentialSearctSET<Integer> set = new SequentialSearctSET<>();
        TestSet.testSet(set, ELEMS);
    }
}
