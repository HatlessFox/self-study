package chapter_3.section_2;

import java.util.LinkedList;
import java.util.Queue;

import chapter_3.section_1.Task_04.Event;
import chapter_3.section_2.Task_12.BST;
import chapter_3.section_2.common.BstTest;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 3.2.13
 * 
 * Give non-recursive implementations of get() and put() for BST.
 */
public class Task_13 {

    public static class NonrecBst<Key extends Comparable<Key>, Value>
                        implements BstTest.BST<Key, Value> {
        private static final int CMP_TREE_KEY = 0;
        
        private class Node {
            public Key k;
            public Value v;
            public Node left, right;
            int sz;
            public Node(Key key, Value value) {
                k = key;
                v = value;
                sz = 1;
            }
        }
        
        private Node root;
        
        @Override
        public Value get(Key key) {
            Node value_node = get(root, key);
            return value_node == null ? null : value_node.v;
        }
        private Node get(Node tree, Key key) {
            while (tree != null) {
                int cmp_key = key.compareTo(tree.k);
                if (cmp_key < CMP_TREE_KEY) { tree = tree.left; }
                else if (CMP_TREE_KEY < cmp_key) { tree = tree.right; }
                return tree;
            }
            return null;
        }

        @Override
        public void put(Key key, Value value) {
            Node val_node = get(root, key);
            if (val_node != null) {
                val_node.v = value;
                return;
            }
            
            Node tree = root, parent = null;
            boolean is_left = false;
            while (tree != null) {
                tree.sz += 1;
                parent = tree;
                int cmp_key = key.compareTo(tree.k);
                if (cmp_key < CMP_TREE_KEY) {
                    tree = tree.left;
                    is_left = true;
                } else if (CMP_TREE_KEY < cmp_key) {
                    tree = tree.right;
                    is_left = false;
                } else {
                    throw new RuntimeException("Unexpected key. Should be updated previously.");
                }
            }
            Node new_node = new Node(key, value);
            if (parent == null) {
                root = new_node;
            } else {
                if (is_left) { parent.left = new_node; }
                else         { parent.right = new_node; }
            }
        }
        
        @Override
        public Iterable<Key> keys() {
            Queue<Key> itrs = new LinkedList<>();
            keys(root, itrs);
            return itrs;
        }
        private void keys(Node tree, Queue<Key> itrs) {
            if (tree == null) { return; }
            keys(tree.left, itrs);
            itrs.add(tree.k);
            keys(tree.right, itrs);
        }
        
        @Override public int size() { return size(root); }
        private int size(Node tree) { return tree == null ? 0 : tree.sz; }
        
        @Override public Key min() { return null; }
        @Override public Key max() { return null; }
        @Override public Key floor(Key key) { return null; }
        @Override public Key ceiling(Key key) { return null; }
        @Override public Key select(int k) { return null; }
        @Override public int rank(Key key) { return 0; }
        @Override public void deleteMin() { }
        @Override public void deleteMax() { }
        @Override public void delete(Key key) { }
        @Override public Iterable<Key> keys(Key lo, Key hi) { return null; }
        
    }
    
    public static void main(String[] args) {
        BST<Event, String> bst = new BST<>();
        BstTest.addWithTestData(bst);
        
        StdOut.println("== DUMP ==");
        BstTest.dumpBst(bst, "\n");
        
        StdOut.printf("get(09:00:13): %s\n", bst.get(new Event(9, 0, 13)));
        bst.put(new Event(9, 00, 59), "Dallas");
        StdOut.println("== DUMP ==");
        BstTest.dumpBst(bst, "\n");
        StdOut.printf("SZ: %d\n", bst.size());
    }
}
