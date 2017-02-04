package chapter_3.section_4;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Queue;

import chapter_3.common.ST;
import edu.princeton.cs.algs4.StdOut;

public class Task_03 {
    public static class SChWithMultidelete<Key, Value> implements ST<Key, Value> {
        public class Node {
            public Key k;
            public Value v;
            public Node next;
            public int sz;
            public Node(Key key, Value value) {
                this(key, value, null);
            }
            public Node(Key key, Value value, Node next) {
                k = key;
                v = value;
                this.next = next;
            }
        }

        private Node[] chains;
        private int sz;
        
        protected int hashCode(Key k) { return k.hashCode() % chains.length; }

        public void dump() {
            for (int chain_i = 0; chain_i < chains.length; ++chain_i) {
                Node ptr = chains[chain_i];
                StdOut.printf("[%d]: ", chain_i);
                while (ptr != null) {
                    StdOut.printf("%s ", ptr.k);
                    ptr = ptr.next;
                }
                StdOut.println();
            }
        }
        
        @SuppressWarnings("unchecked")
        public SChWithMultidelete(int table_size) {
            chains = (Node[]) Array.newInstance(Node.class, table_size);
        }

        @Override
        public Value get(Key k) {
            Node ptr = chains[hashCode(k)];
            while (ptr != null) {
                if (k.equals(ptr.k)) { return ptr.v; }
                ptr = ptr.next;
            }
            return null;
        }
        
        @Override
        public void delete(Key k) {
            int chain_i = hashCode(k);
            chains[chain_i] = delete(k, chains[chain_i]);
        }
        protected Node delete(Key k, Node node) {
            if (node == null) { return null; }
            if (k.equals(node.k)) {
                --sz;
                return node.next;
            }
            node.next = delete(k, node.next);
            return node;
        }

        public void delete(int sz_lim) {
            for (Node ptr : chains) {
                while (ptr != null) {
                    if (sz_lim < ptr.sz) {
                        delete(ptr.k);
                    }
                    ptr = ptr.next;
                }
            }
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
            new_node.sz = sz;
            chains[chain_i] = new_node;
            ++sz;
        }

        @Override
        public int size() { return sz; }

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
    
    public static void main(String[] args) {
        SChWithMultidelete<Integer, Integer> st = new SChWithMultidelete<>(4);
        for (int i = 0; i < 20; i++) { st.put(i, i); }
        st.delete(9);
        st.dump();
    }
}
