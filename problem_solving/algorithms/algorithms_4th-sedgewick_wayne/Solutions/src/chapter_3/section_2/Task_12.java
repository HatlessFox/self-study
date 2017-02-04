package chapter_3.section_2;

import java.util.LinkedList;
import java.util.Queue;

import chapter_3.section_1.Task_04.Event;
import chapter_3.section_2.common.BstTest;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 3.2.12
 * 
 * Develop a BST implementation that omits rank() and select() and does not use a count field in
 * Node.
 */
public class Task_12 {
    
    public static class BST<Key extends Comparable<Key>, Value> implements BstTest.BST<Key, Value> {
        private static final int CMP_TREE_KEY = 0; 

        private Node root;
        private class Node {
            Key k;
            Value v;
            Node left, right;
            public Node(Key key, Value value) {
                k = key;
                v = value;
            }
        }
        
        private Key node2key(Node n) { return n == null ? null : n.k; }
        
        @Override
        public Value get(Key key) { return get(root, key); }
        private Value get(Node tree, Key key) {
            if (tree == null) { return null; }
            
            int cmp_key = key.compareTo(tree.k);
            if      (cmp_key < CMP_TREE_KEY) { return get(tree.left, key); }
            else if (CMP_TREE_KEY < cmp_key) { return get(tree.right, key); }
            else                             { return tree.v; }
        }
        
        @Override
        public void put(Key key, Value value) { root = put(root, key, value); }
        private Node put(Node tree, Key k, Value v) {
            if (tree == null) { return new Node(k, v); }
            
            int cmp_key = k.compareTo(tree.k);
            if      (cmp_key < CMP_TREE_KEY) { tree.left = put(tree.left, k, v); }
            else if (CMP_TREE_KEY < cmp_key) { tree.right = put(tree.right, k, v); }
            else                             { tree.v = v; }
            return tree;
        }
        
        @Override
        public Key min() { return node2key(min(root)); }
        private Node min(Node tree) { 
            if (tree == null) { return null; }
            return tree.left == null ? tree : min(tree.left); 
        }
        
        @Override
        public Key max() { return node2key(max(root)); }
        private Node max(Node tree) {
            if (tree == null) { return null; }
            return tree.right == null ? tree : max(tree.right);
        }
        
        @Override
        public Key floor(Key key) { return node2key(floor(root, key)); }
        private Node floor(Node tree, Key key) {
            if (tree == null) { return null; }
            
            int cmp_key = key.compareTo(tree.k);
            if      (cmp_key < CMP_TREE_KEY)  { return floor(tree.left, key); }
            else if (cmp_key == CMP_TREE_KEY) { return tree; }
            Node right_tree_floor = floor(tree.right, key);
            return right_tree_floor == null ? tree : right_tree_floor;
        }
        
        @Override
        public Key ceiling(Key key) { return node2key(ceiling(root, key)); }
        private Node ceiling(Node tree, Key key) {
            if (tree == null) { return null; }
            
            int cmp_key = key.compareTo(tree.k);
            if      (cmp_key == CMP_TREE_KEY) { return tree; }
            else if (CMP_TREE_KEY < cmp_key)  { return ceiling(tree.right, key); }
            Node left_tree_ceiling = ceiling(tree.left, key);
            return left_tree_ceiling == null ? tree : left_tree_ceiling;
        }
        
        @Override
        public void deleteMin() { root = deleteMin(root); }
        private Node deleteMin(Node tree) {
            if (tree == null) { return null; }
            if (tree.left == null) { return tree.right; }
            tree.left = deleteMin(tree.left);
            return tree;
        }
        
        @Override
        public void deleteMax() { root = deleteMax(root); }
        private Node deleteMax(Node tree) {
            if (tree == null) { return null; }
            if (tree.right == null) { return tree.left; }
            tree.right = deleteMax(tree.right);
            return tree;
        }
        
        @Override
        public void delete(Key key) { root = delete(root, key); }
        private Node delete(Node tree, Key key) {
            if (tree == null) { return null; }
            
            int cmp_key = key.compareTo(tree.k);
            if      (cmp_key < CMP_TREE_KEY) { tree.left = delete(tree.left, key); }
            else if (CMP_TREE_KEY < cmp_key) { tree.right = delete(tree.right, key); }
            else {
                if (tree.left == null) { return tree.right; }
                if (tree.right == null) { return tree.left; }
                Node new_tree = min(tree.right);
                new_tree.right = deleteMin(tree.right);
                new_tree.left = tree.left;
                tree = new_tree;
            }
            return tree;
        }
        
        @Override
        public Iterable<Key> keys() { return keys(min(), max()); }
        
        @Override
        public Iterable<Key> keys(Key lo, Key hi) {
            Queue<Key> itrs = new LinkedList<>();
            keys(root, itrs, lo, hi);
            return itrs;
        }

        private void keys(Node tree, Queue<Key> itrs, Key lo, Key hi) {
            if (tree == null) { return; }
            
            int cmp_lo = lo.compareTo(tree.k), cmp_hi = hi.compareTo(tree.k);
            if (cmp_lo < CMP_TREE_KEY) { keys(tree.left, itrs, lo, hi); }
            if (cmp_lo <= CMP_TREE_KEY && CMP_TREE_KEY <= cmp_hi) { itrs.add(tree.k); }
            if (CMP_TREE_KEY < cmp_hi) { keys(tree.right, itrs, lo, hi); }
        }

        @Override
        public int size() { return size(root); }
        private int size(Node tree) {
            if (tree == null) { return 0; }
            return size(tree.left) + 1 + size(tree.right);
        }

        @Override
        public Key select(int k) { return null; }

        @Override
        public int rank(Key key) { return 0; }
    }
    
    public static void dumpBst(BST<Event, String> bst, String sep) {
        for (Event k : bst.keys()) {
            StdOut.printf("%s: %s%s", k, bst.get(k), sep);
        }
        StdOut.println();
    }
    
    public static void main(String[] args) {
        BST<Event, String> bst = new BST<>();
        BstTest.addWithTestData(bst);
        BstTest.testBstReduced(bst);
        
        bst.delete(new Event(9, 14, 25));
        bst.deleteMin();
        bst.deleteMax();
        StdOut.println("== DUMP after Delete (min, max, 09:14:25) ==");
        BstTest.dumpBst(bst, "\n");
    }
}
