package chapter_3.section_1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import chapter_3.common.OST;
import chapter_3.common.ST;
import chapter_3.common.Utils;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 3.1.3
 * 
 * Develop a symbol-table implementation OrderedSequentialSearchST that uses an ordered linked list
 * as the underlying data structure to implement our ordered symbol-table API.
 */
public class Task_03 {
    
    public static class OrderedSequentialSearchST<Key extends Comparable<Key>, Value>
                                                 implements OST<Key, Value> {
        Node<Key, Value> first;
        
        private static class Node<Key, Value> {
            Key k;
            Value v;
            Node<Key, Value> next;
            Node(Key key, Value value, Node<Key, Value> next) {
                k = key;
                v = value;
                this.next = next;
            }
        }
        
        // OST interface implementation

        @Override
        public void put(Key key, Value v) {
            if (isEmpty() || 0 < first.k.compareTo(key)) {
                if (v != null) {
                    first = new Node<>(key, v, first);
                }
                return;
            }
            if (first.k.compareTo(key) == 0) {
                if (v == null) { first = first.next; }
                else { first.v = v; }
                return;
            }
            
            Node<Key, Value> prev = first, ptr = prev.next;
            while (ptr != null) {
                if (ptr.k.compareTo(key) == 0) {
                    if (v != null) { ptr.v = v; }
                    else { prev.next = ptr.next; }
                    return;
                } else if (0 < ptr.k.compareTo(key)) {
                    Node<Key, Value> node = new Node<>(key, v, ptr);
                    prev.next = node;
                    return;
                } else {
                    prev = ptr;
                }
                ptr = ptr.next;
            }
            
            Node<Key, Value> node = new Node<>(key, v, null);
            prev.next = node;
        }

        @Override
        public Value get(Key key) {
            Node<Key, Value> ptr = first;
            while (ptr != null) {
                if (ptr.k.compareTo(key) == 0) {
                    return ptr.v;
                } else if (0 < ptr.k.compareTo(key)) {
                    break;
                }
                ptr = ptr.next;
            }
            return null;
        }

        @SuppressWarnings("unused")
        @Override
        public int size() {
            int sz = 0;
            for (Key k : keys()) { sz++; }
            return sz;
        }

        @Override
        public Iterable<Key> keys() {
            List<Key> itrbl = new LinkedList<>();
            Node<Key, Value> ptr = first;
            while (ptr != null) {
                itrbl.add(ptr.k);
                ptr = ptr.next;
            }
            return itrbl;
        }

        @Override
        public Key min() {
            return select(0);
        }

        @Override
        public Key max() {
            return select(size() - 1);
        }
        
        @Override
        public Key floor(Key key) {
            return select(rank(key) - 1);
        }

        @Override
        public Key ceiling(Key key) {
            return select(rank(key));
        }

        @Override
        public int rank(Key k) {
            int rank = 0;
            for (Key key : keys()) {
                if (0 <= key.compareTo(k)) { break; }
                rank++;
            }
            return rank;
        }

        @Override
        public Key select(int k) {
            if (k < 0) return null;
            for (Key key : keys()) {
                if (k-- == 0) { return key; }
            }
            return null;
        }

        @Override
        public Iterable<Key> keys(Key lo, Key hi) {
            List<Key> itrbl = new ArrayList<>();
            for (Key k : keys()) {
                if (0 <= k.compareTo(lo) && k.compareTo(hi) <= 0) {
                    itrbl.add(k);
                } else if (0 < k.compareTo(hi)) {
                    break;
                }
            }
            return itrbl;
        }
        
    }

    public static void dump(ST<String, Integer> st) {
        for (String k : st.keys()) {
            StdOut.printf("%s: %d; ", k, st.get(k));
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        // @see Task_04 for more tests
        OST<String, Integer> ost = new OrderedSequentialSearchST<>();
        
        ost.put("a", null);
        ost.put("A", 1);
        ost.put("M", 2);
        ost.put("P", 3);
        ost.put("M", null);
        ost.put("D", 2);
        ost.put("L", 3);
        ost.put("A", 5);
        ost.put("B", 8);
        ost.delete("A");
        Utils.dumpST(ost, "; ");
        
        StdOut.printf("MIN: %s, MAX: %s\n", ost.min(), ost.max());
        StdOut.printf("SEL 3: %s, RANK L: %s\n", ost.select(3), ost.rank("L"));
        StdOut.printf("CEIL Y: %s, FLOOR F: %s\n", ost.ceiling("Y"), ost.floor("F"));
        for (String k : ost.keys("O", "W")) {
            StdOut.printf("%s ", k);
        }
        StdOut.println();
    }
}
