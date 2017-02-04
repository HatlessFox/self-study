package chapter_3.section_2;

import edu.princeton.cs.algs4.StdOut;
import utils.LangUtils;

/*
 * 3.2.7
 * 
 * Add to BST a recursive method avgCompares() that computes the average number of compares required
 * by a random search hit in a given BST (the internal path length of the tree divided by its size,
 * plus one). Develop two implementations: a recursive method (which takes linear time and space
 * proportional to the height), and a method like size() that adds a field to each node in the tree
 * (and takes linear space and constant time per query).
 */
public class Task_07 {

    public static interface AvgCmpBst<Key extends Comparable<Key>, Value> {
        public Value get(Key k);
        public void put(Key k, Value v);
        public int size();
        public double avgCompares();
    }
    
    public static class RecursiveAvgCmpBst<Key extends Comparable<Key>, Value>
                        implements AvgCmpBst<Key, Value> {
        private Node root;
        private class Node {
            Key k;
            Value v;
            Node left, right;
            int sz;
            public Node(Key key, Value value) {
                k = key;
                v = value;
                sz = 1;
            }
        }
        
        @Override
        public Value get(Key k) { return get(root, k); }
        private Value get(Node node, Key k) {
            if (node == null) { return null; }
            
            int cmp_res = k.compareTo(node.k);
            if (cmp_res < 0) { return get(node.left, k); }
            else if (0 < cmp_res) { return get(node.right, k); }
            else { return node.v; }
        }
        
        @Override
        public void put(Key k, Value v) { root = put(root, k, v); }
        private Node put(Node node, Key k, Value v) {
            if (node == null) { return new Node(k, v); }
            
            int cmp_res = k.compareTo(node.k);
            if (cmp_res < 0) { node.left = put(node.left, k, v); }
            else if (0 < cmp_res) { node.right = put(node.right, k, v); }
            else { node.v = v; }
            
            node.sz = size(node.left) + 1 + size(node.right);
            return node;
        }
        
        @Override
        public int size() { return size(root); }
        private int size(Node node) { return node == null ? 0 : node.sz; }
        
        @Override
        public double avgCompares() { return 1.0 * totalCompares(root) / size(root); }
        private int totalCompares(Node node) {
            if (node == null) { return 0; }
            return totalCompares(node.left) + size(node.left) + 1 +
                   totalCompares(node.right) + size(node.right);
        }
    }
    
    public static class CachedAvgCmpBst<Key extends Comparable<Key>, Value>
                        implements AvgCmpBst<Key, Value> {
        private Node root;
        private class Node {
            Key k;
            Value v;
            Node left, right;
            int sz, total_cmp;
            public Node(Key key, Value value) {
                k = key;
                v = value;
                sz = 1;
                total_cmp = 1;
            }
        }
        
        @Override
        public Value get(Key k) { return get(root, k); }
        private Value get(Node node, Key k) {
            if (node == null) { return null; }
            
            int cmp_res = k.compareTo(node.k);
            if (cmp_res < 0) { return get(node.left, k); }
            else if (0 < cmp_res) { return get(node.right, k); }
            else { return node.v; }
        }

        @Override
        public void put(Key k, Value v) { root = put(root, k, v); }
        private Node put(Node node, Key k, Value v) {
            if (node == null) { return new Node(k, v); }
            
            int cmp_res = k.compareTo(node.k);
            if (cmp_res < 0) { node.left = put(node.left, k, v); }
            else if (0 < cmp_res) { node.right = put(node.right, k, v); }
            else { node.v = v; }
            
            node.sz = size(node.left) + 1 + size(node.right);
            node.total_cmp = compares(node.left) + size(node.left) + 1 +
                             compares(node.right) + size(node.right); 
            return node;
        }

        @Override
        public int size() { return size(root); }
        private int size(Node node) { return node == null ? 0 : node.sz; }

        @Override
        public double avgCompares() { return 1.0 * compares(root) / size(root); }
        private int compares(Node node) { return node == null ? 0 : node.total_cmp; }
    }
    
    // Tests
    private static double log2(double x) {
        return Math.log10(x) / Math.log10(2);
    }
    
    private static final int N = 10;
    private static void testUnbalanced() {
        StdOut.println("== Test Unbalanced ==");
        AvgCmpBst<Integer, Integer> rhbst = new RecursiveAvgCmpBst<>(),
                                    chbst = new CachedAvgCmpBst<>();
        for (int i = 0; i < N; ++i) {
            rhbst.put(i, i);
            chbst.put(i, i);
        }
        // 1 + 2 + ... + N = N * (N + 1) / 2 => avg = (N + 1) / 2
        StdOut.printf("Recursize: %f vs %f\n", rhbst.avgCompares(), (N + 1) / 2.0);
        StdOut.printf("Cached: %f vs %f\n", chbst.avgCompares(), (N + 1) / 2.0);
    }
    
    private static void testBalanced() {
        StdOut.println("== Test Balanced ==");
        AvgCmpBst<Integer, Integer> rhbst = new RecursiveAvgCmpBst<>(),
                                    chbst = new CachedAvgCmpBst<>();
        for (Integer i : LangUtils.ints2arr(8, 4, 12, 6, 2, 10, 14, 1, 5, 9, 13, 3, 7, 11, 15)) {
            rhbst.put(i, i);
            chbst.put(i, i);
        }
        // less, since log is max, not avg.
        StdOut.printf("Recursize: %f < %f\n", rhbst.avgCompares(), log2(15));
        StdOut.printf("Cached: %f < %f\n", chbst.avgCompares(), log2(15));
    }
    public static void main(String[] args) {
        testUnbalanced();
        testBalanced();
    }
}
