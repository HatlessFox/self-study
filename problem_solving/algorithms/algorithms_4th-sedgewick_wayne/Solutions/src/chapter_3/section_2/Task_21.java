package chapter_3.section_2;

import java.util.Random;

import chapter_3.section_1.Task_04.Event;
import chapter_3.section_2.common.BstTest;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 3.2.21
 * 
 * Add a BST method randomKey() that returns a random key from the symbol table in time proportional
 * to the tree height, in the worst case.
 */
public class Task_21 {

    public static class RandomKeyBst<Key extends Comparable<Key>>
                        implements BstTest.BST<Key, String> {
        private static final int CMP_TREE_KEY = 0;
        private Node root;
        private Random rnd = new Random();
        private class Node {
            Key k;
            Node left, right;
            public Node(Key key) {
                k = key;
            }
        }

        @Override
        public void put(Key key, String value) { root = put(root, key, value); }
        private Node put(Node tree, Key key, String value) {
            if (tree == null) { return new Node(key); }
            
            int cmp_key = key.compareTo(tree.k);
            if (cmp_key < CMP_TREE_KEY) { tree.left = put(tree.left, key, value); }
            else if (CMP_TREE_KEY < cmp_key) { tree.right = put(tree.right, key, value); }
            return tree;
        }

        public Key randomKey() { return root != null ? randomKey(root) : null; }
        public Key randomKey(Node tree) {
            if (tree.left == null && tree.right == null) { return tree.k; }
            if (rnd.nextBoolean()) { return tree.k; }
            
            if (tree.left == null) { return randomKey(tree.right); }
            if (tree.right == null) { return randomKey(tree.left); }
            return randomKey(rnd.nextBoolean() ? tree.left : tree.right);
        }
        
        @Override public int size() { return 0; }
        @Override public String get(Key key) { return null; }
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
        @Override public Iterable<Key> keys() { return null; }
    }
    
    public static void main(String[] args) {
        RandomKeyBst<Event> bst = new RandomKeyBst<>();
        BstTest.addWithTestData(bst);
        
        StdOut.println("== Random Keys ==");
        for (int i = 0; i < 5; ++i) {
            StdOut.printf("%s\n", bst.randomKey());
        }
    }
}
