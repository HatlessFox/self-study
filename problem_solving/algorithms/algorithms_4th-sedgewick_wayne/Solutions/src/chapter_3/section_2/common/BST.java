package chapter_3.section_2.common;

import java.util.LinkedList;
import java.util.Queue;

public class BST<Key extends Comparable<Key>, Value> implements BstTest.BST<Key, Value> {
    protected class BstNode {
        Key key;
        Value value;
        BstNode left, right;
        int sz;
        
        public BstNode(Key k, Value v) {
            key = k;
            value = v;
            sz = 1;
        }
        
        public BstNode(Key k, Value v, BstNode l, BstNode r) {
            this(k, v);
            left = l;
            right = r;
            sz += l.sz + l.sz;
        }
    }
    
    protected BstNode root;
    
    public int size() { return size(root); }
    protected int size(BstNode node) { return node == null ? 0 : node.sz; }
    
    public int size(Key lo, Key hi) {
        int sz = 0;
        for (@SuppressWarnings("unused") Key k : keys(lo, hi)) {
            sz += 1;
        }
        return sz;
    }
    
    public Value get(Key key) { return get(root, key); }
    protected Value get(BstNode tree, Key key) {
        if (tree == null) { return null; }
        int cmp_res = key.compareTo(tree.key);
        if      (cmp_res < 0) { return get(tree.left, key); }
        else if (0 < cmp_res) { return get(tree.right, key); }
        else                  { return tree.value; }
    }
    
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }
    protected BstNode put(BstNode tree, Key key, Value value) {
        if (tree == null) { return new BstNode(key, value); }
        int cmp_res = key.compareTo(tree.key);
        if      (cmp_res < 0) { tree.left = put(tree.left, key, value); }
        else if (0 < cmp_res) { tree.right = put(tree.right, key, value); }
        else                  { tree.value = value; }
        tree.sz = 1 + size(tree.left) + size(tree.right);
        return tree;
    }
    
    public Key min() {
        BstNode min_node = min(root);
        return min_node == null ? null : min_node.key;
    }
    protected BstNode min(BstNode node) { return node.left == null ? node : min(node.left); }
    
    public Key max() { 
        BstNode max_node = max(root);
        return max_node == null ? null : max_node.key;
    }
    protected BstNode max(BstNode node) { return node.right == null ? node : max(node.right); }
    
    public Key floor(Key key) {
        BstNode floor = floor(root, key);
        return floor != null ? floor.key : null;
    }
    protected BstNode floor(BstNode tree, Key key) {
        if (tree == null) { return null; }

        int cmp_res = key.compareTo(tree.key);
        if (cmp_res < 0)  { return floor(tree.left, key); }
        if (cmp_res == 0) { return tree; }
        BstNode floor_node = floor(tree.right, key);
        return floor_node == null ? tree : floor_node;
    }
    
    public Key ceiling(Key key) {
        BstNode ceiling_node = ceiling(key, root);
        return ceiling_node != null ? ceiling_node.key : null;
    }
    protected BstNode ceiling(Key key, BstNode tree) {
        if (tree == null) { return tree; }
        
        int cmp_res = key.compareTo(tree.key);
        if (cmp_res == 0) { return tree; }
        if (0 < cmp_res) { return ceiling(key, tree.right); }
        BstNode ceiling_node = ceiling(key, tree.left);
        return ceiling_node == null ? tree : ceiling_node;
    }
    
    public Key select(int k) {
        BstNode key_node = select(root, k);
        return key_node == null ? null : key_node.key;
    }
    protected BstNode select(BstNode tree, int k) {
        if (tree == null) { return null; }
        
        int prior_nm = size(tree.left);
        if      (k < prior_nm)  { return select(tree.left, k); }
        else if (prior_nm == k) { return tree; }
        else                    { return select(tree.right, k - prior_nm - 1); }
    }
    
    public int rank(Key key) { return rank(root, key); }
    protected int rank(BstNode tree, Key key) {
        if (tree == null) { return 0; }
        
        int cmp_res = key.compareTo(tree.key);
        if      (cmp_res < 0)  { return rank(tree.left, key); }
        else if (cmp_res == 0) { return size(tree.left); }
        else                   { return size(tree.left) + 1 + rank(tree.right, key); }
    }
    
    public void deleteMin() { root = deleteMin(root); }
    protected BstNode deleteMin(BstNode tree) {
        if (tree == null) { return null; }
        
        if (tree.left == null) { return tree.right; }
        tree.left = deleteMin(tree.left);
        tree.sz = size(tree.left) + 1 + size(tree.right);
        return tree;
    }
    
    public void deleteMax() { root = deleteMax(root); }
    protected BstNode deleteMax(BstNode tree) {
        if (tree == null) { return null; }
        
        if (tree.right == null) { return tree.left; }
        tree.right = deleteMax(tree.right);
        tree.sz = size(tree.left) + 1 + size(tree.right);
        return tree;
    }
    
    public void delete(Key key) { root = delete(key, root); }
    protected BstNode delete(Key key, BstNode tree) {
        if (tree == null) { return null; }
        
        int cmp_res = key.compareTo(tree.key);
        if      (cmp_res < 0) { tree.left = delete(key, tree.left); }
        else if (0 < cmp_res) { tree.right = delete(key, tree.right); }
        else {
            if      (tree.left == null)  { return tree.right; }
            else if (tree.right == null) { return tree.left; }
            else {
                BstNode new_tree_root = min(tree.right);
                new_tree_root.right = deleteMin(tree.right);
                new_tree_root.left = tree.left;
                tree = new_tree_root;
            }
        }
        tree.sz = size(tree.left) + 1 + size(tree.right);
        return tree;
    }
    
    public Iterable<Key> keys() {
        Queue<Key> keys = new LinkedList<>();
        keys(root, keys);
        return keys;
    }
    protected void keys(BstNode tree, Queue<Key> itrs) {
        if (tree == null) { return; }
        
        keys(tree.left, itrs);
        itrs.add(tree.key);
        keys(tree.right, itrs);
    }
    
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> keys = new LinkedList<>();
        keys(root, keys, lo, hi);
        return keys;
    }
    protected void keys(BstNode tree, Queue<Key> itrs, Key lo, Key hi) {
        if (tree == null) { return; }
        
        int cmp_lo = lo.compareTo(tree.key);
        int cmp_hi = hi.compareTo(tree.key);
        
        if (cmp_lo < 0)                 { keys(tree.left, itrs, lo, hi); }
        if (cmp_lo <= 0 && 0 <= cmp_hi) { itrs.add(tree.key); }
        if (0 < cmp_hi)                 { keys(tree.right, itrs, lo, hi); }
    }
    
    public static void main(String[] args) {
        BstTest.testBst(new BST<>());
    }
}
