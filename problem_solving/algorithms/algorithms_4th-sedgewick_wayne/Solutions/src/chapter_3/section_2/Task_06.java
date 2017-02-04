package chapter_3.section_2;

import edu.princeton.cs.algs4.StdOut;
import utils.LangUtils;

/*
 * Task 3.2.6
 * 
 * Add to BST a method height() that computes the height of the tree. Develop two implementations:
 * a recursive method (which takes linear time and space proportional to the height),
 * and a method like size() that adds a field to each node in the tree (and takes linear space and
 * constant time per query).
 */
public class Task_06 {

    public static interface HeightBST<Key extends Comparable<Key>, Value> {
        public Value get(Key k);
        public void put(Key k, Value v);
        public void delete(Key k);
        
        public int height();
    }
    
    public static class RecursiveHeightBST<Key extends Comparable<Key>, Value>
                        implements HeightBST<Key, Value> {
        
        private class Node {
            public Key k;
            public Value v;
            public Node left, right;
            public Node(Key key, Value value) {
                k = key;
                v = value;
            }
        }
        
        private Node root;
        
        @Override
        public Value get(Key k) { return get(root, k); }
        protected Value get(Node tree, Key k) {
            if (tree == null) { return null; }
            int cmp_res = k.compareTo(tree.k);
            if      (cmp_res < 0) { return get(tree.left, k); }
            else if (0 < cmp_res) { return get(tree.right, k); }
            else                  { return tree.v; }
        }
        
        @Override
        public void put(Key k, Value v) { root = put(root, k, v); }
        protected Node put(Node tree, Key k, Value v) {
            if (tree == null) { return new Node(k, v); }
            int cmp_res = k.compareTo(tree.k);
            if (cmp_res < 0) { tree.left = put(tree.left, k, v); }
            else if (0 < cmp_res) { tree.right = put(tree.right, k, v); }
            else { tree.v = v; }
            return tree;
        }

        @Override
        public void delete(Key k) { root = delete(root, k); }
        protected Node delete(Node tree, Key k) {
            if (tree == null) { return null; }
            int cmp_res = k.compareTo(tree.k);
            if (cmp_res < 0) { tree.left = delete(tree.left, k); }
            else if (0 < cmp_res) { tree.right = delete(tree.right, k); }
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
        protected Node min(Node tree) {
            if (tree == null) { return null; }
            return tree.left == null ? tree : min(tree.left);
        }
        protected Node deleteMin(Node tree) {
            if (tree == null) { return null; }
            if (tree.left == null) { return tree.right; }
            tree.left = deleteMin(tree.left);
            return tree;
        }
        
        @Override
        public int height() { return height(root); }
        protected int height(Node tree) {
            if (tree == null) { return 0; }
            return 1 + Math.max(height(tree.left), height(tree.right));
        }
    }

    public static class CachingHeightBST<Key extends Comparable<Key>, Value>
                        implements HeightBST<Key, Value> {

        private class Node {
            Key k;
            Value v;
            int height;
            Node left, right;
            public Node(Key key, Value value) {
                k = key;
                v = value;
                height = 1;
            }
        }
        
        private Node root;
        
        @Override
        public Value get(Key k) { return get(root, k); }
        private Value get(Node tree, Key k) {
            if (tree == null) { return null; }
            int cmp_res = k.compareTo(tree.k);
            if (cmp_res < 0) { return get(tree.left, k); }
            else if (0 < cmp_res) { return get(tree.right, k); }
            else { return tree.v; }
        }

        @Override
        public void put(Key k, Value v) { root = put(root, k, v); }
        private Node put(Node tree, Key k, Value v) {
            if (tree == null) { return new Node(k, v); }
            int cmp_res = k.compareTo(tree.k);
            if (cmp_res < 0) { tree.left = put(tree.left, k, v); }
            else if (0 < cmp_res) { tree.right = put(tree.right, k, v); }
            else { tree.v = v; }
            tree.height = 1 + Math.max(height(tree.left), height(tree.right));
            return tree;
        }

        @Override
        public void delete(Key k) { root = delete(root, k); }
        private Node delete(Node tree, Key k) {
            if (tree == null) { return null; }
            int cmp_res = k.compareTo(tree.k);
            if (cmp_res < 0) { tree.left = delete(tree.left, k); }
            else if (0 < cmp_res) { tree.right = delete(tree.right, k); }
            else {
                if (tree.left == null) { return tree.right; }
                if (tree.right == null) { return tree.left; }
                
                Node new_tree = min(tree.right);
                new_tree.right = deleteMin(tree.right);
                new_tree.left = tree.left;
                tree = new_tree;
            }
            
            tree.height = 1 + Math.max(height(tree.left), height(tree.right));
            return tree;
        }
        private Node min(Node tree) { 
            if (tree == null) { return null; }
            return tree.left == null ? tree : min(tree.left);
        }
        private Node deleteMin(Node tree) {
            if (tree == null) { return null; }
            if (tree.left == null) { return tree.right; }
            tree.left = deleteMin(tree.left);
            return tree;
        }

        @Override
        public int height() { return height(root); }
        private int height(Node tree) { return tree == null ? 0 : tree.height; }
    }
    
    // Tests
    private static void fail(String msg) {
        StdOut.println(msg);
        System.exit(-1);
    }
    
    private static void checkHeight(String pref, HeightBST<Integer, Integer> rhbst,
                                    HeightBST<Integer, Integer> chbst, int exp_height) {
        if (rhbst.height() != exp_height) {
            fail(pref + ": RHBST height is broken " + rhbst.height() + " vs " + exp_height);
        }
        if (chbst.height() != exp_height) {
            fail(pref + ": CHBST height is broken " + chbst.height() + " vs " + exp_height);
        }
    }
    
    private static final int N = 10;
    private static void testUnbalanced() {
        StdOut.println("== Test Unbalanced ==");
        HeightBST<Integer, Integer> rhbst = new RecursiveHeightBST<>(),
                                    chbst = new CachingHeightBST<>();
        for (int i = 0; i < N; ++i) {
            rhbst.put(i, i);
            chbst.put(i, i);
            checkHeight("PUT", rhbst, chbst, i + 1);
        }
        rhbst.delete(3);
        chbst.delete(3);
        checkHeight("DEL", rhbst, chbst, N - 1);
        StdOut.println("OK");
    }
    
    private static void testBalanced() {
        StdOut.println("== Test Balanced ==");
        HeightBST<Integer, Integer> rhbst = new RecursiveHeightBST<>(),
                                    chbst = new CachingHeightBST<>();
        for (Integer i : LangUtils.ints2arr(4, 2, 6, 5, 1, 3, 7)) {
            rhbst.put(i, i);
            chbst.put(i, i);
        }
        checkHeight("POST INIT", rhbst, chbst, 3);
        
        for (Integer i : LangUtils.ints2arr(4, 7, 1)) {
            rhbst.delete(i);
            chbst.delete(i);
            checkHeight("DEL " + i, rhbst, chbst, 3);
        }
        
        rhbst.delete(3); chbst.delete(3);
        checkHeight("POST DEL", rhbst, chbst, 2);
        StdOut.println("OK");
    }

    
    public static void main(String[] args) {
        testUnbalanced();
        testBalanced();
    }
}
