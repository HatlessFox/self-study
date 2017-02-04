package chapter_3.section_1;

import java.util.ArrayList;
import java.util.List;

import chapter_3.common.ST;
import chapter_3.common.Utils;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 3.1.5
 * 
 * Implement size(), delete(), and keys() for SequentialSearchST
 */
public class Task_05 {

    public static class SequentialSearchST<Key, Value> implements ST<Key, Value> {
        private Node<Key, Value> head = new Node<Key, Value>(null, null, null);
        
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
        
        @Override
        public void put(Key k, Value v) {
            if (v == null) {
                delete(k);
                return;
            }
            Node<Key, Value> ptr = head;
            while ((ptr = ptr.next) != null) {
                if (k.equals(ptr.k)) {
                    ptr.v = v;
                    return;
                }
            }
            Node<Key, Value> new_node = new Node<>(k, v, head.next);
            head.next = new_node;
        }
        
        @Override
        public Value get(Key k) {
            Node<Key, Value> ptr = head;
            while ((ptr = ptr.next) != null) {
                if (k.equals(ptr.k)) {
                    return ptr.v;
                }
            }
            return null;
        }

        private Node<Key, Value> delete(Node<Key, Value> lst, Key k) {
            if (lst == null) { return null; }
            if (lst.k.equals(k)) {
                return lst.next;
            }
            lst.next = delete(lst.next, k);
            return lst;
        }
        
        @Override
        public void delete(Key k) {
            delete(head.next, k);
        }
        
        @Override
        public int size() {
            int sz = 0;
            Node<Key, Value> ptr = head;
            while ((ptr = ptr.next) != null) {
                sz += 1;
            }
            return sz;
        }

        @Override
        public Iterable<Key> keys() {
            List<Key> itrbl = new ArrayList<Key>();
            Node<Key, Value> ptr = head;
            while ((ptr = ptr.next) != null) {
                itrbl.add(ptr.k);
            }
            return itrbl;
        }
    }
    
    public static void main(String[] args) {
        ST<String, Integer> st = new SequentialSearchST<>();
        
        st.put("A", 1);
        st.put("B", 2);
        st.put("C", 3);
        st.put("B", null);
        st.put("D", 2);
        st.put("E", 3);
        st.put("A", 5);
        st.put("B", 8);
        st.delete("C");
        Utils.dumpST(st, "; "); // A: 5, D: 2, E:3, B: 8
        StdOut.printf("size = %d\n", st.size());
        
    }
}
