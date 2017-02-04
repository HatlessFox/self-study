package chapter_3.section_4;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Queue;

import chapter_3.common.ST;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 3.4.2
 * 
 * Develop an alternate implementation of SeparateChainingHashST that directly uses the linked-list
 * code from SequentialSearchST.
 */
public class Task_02 {

    public static class SeparateChainingHashST<Key, Value> implements ST<Key, Value> {

        public class Node {
            public Key k;
            public Value v;
            public Node next;
            public Node(Key key, Value value) {
                this(key, value, null);
            }
            public Node(Key key, Value value, Node next) {
                k = key;
                v = value;
                this.next = next;
            }
        }

        protected Node[] chains;
        
        protected int hashCode(Key k) { return k.hashCode() % chains.length; }

        public void dump() { dump(false); }
        public void dump(boolean show_value) {
            StdOut.printf("Size: %d\n", size());
            for (int chain_i = 0; chain_i < chains.length; ++chain_i) {
                Node ptr = chains[chain_i];
                StdOut.printf("[%d]: ", chain_i);
                while (ptr != null) {
                    if (show_value) {
                        StdOut.printf("(%s, %s) ", ptr.k, ptr.v);
                    } else {
                        StdOut.printf("%s ", ptr.k);
                    }
                    if (ptr.next != null) {
                        StdOut.print("-> ");
                    }
                    ptr = ptr.next;
                }
                StdOut.println();
            }
        }
        
        @SuppressWarnings("unchecked")
        public SeparateChainingHashST(int table_size) {
            chains = (Node[]) Array.newInstance(Node.class, table_size);
        }

        @Override
        public Value get(Key k) {
            Node ptr = chains[hashCode(k)];
            while (ptr != null) {
                if (k.equals(ptr.k)) {
                    return ptr.v;
                }
                ptr = ptr.next;
            }
            return null;
        }

        @Override
        public void put(Key k, Value v) {
            int chain_i = hashCode(k);
            Node ptr = chains[chain_i];
            while (ptr != null) {
                if (k.equals(ptr.k)) {
                    ptr.v = v;
                    return;
                }
                ptr = ptr.next;
            }
            Node new_node = new Node(k, v, chains[chain_i]);
            chains[chain_i] = new_node;
        }

        @Override
        public int size() {
            int sz = 0;
            for (@SuppressWarnings("unused") Key k : keys()) { sz += 1; }
            return sz;
        }

        @Override
        public Iterable<Key> keys() {
            Queue<Key> keys = new LinkedList<>();
            for (Node ptr : chains) {
                while (ptr != null) {
                    keys.add(ptr.k);
                    ptr = ptr.next;
                }
            }
            return keys;
        }
    }
    
    private static final int N = 4;
    
    public static void main(String[] args) {
        SeparateChainingHashST<Integer, Integer> st = new SeparateChainingHashST<>(2);
        for (int i = 0; i < N; ++i) { st.put(i, i); }
        for (int i = 0; i < 2*N; ++i) {
            if (i < N && !st.contains(i)) {
                StdOut.printf("[?!] %d isn't contained.\n", i);
                System.exit(-1);
            } else if (N <= i && st.contains(i)) {
                StdOut.printf("[?!] %d is contained.\n", i);
                System.exit(-1);
            }
        }
        st.delete(3);
        if (st.contains(3)) {
            StdOut.print("Fail to delete 3 key");
        }
        st.dump(true);
        StdOut.println("OK");
    }
}
