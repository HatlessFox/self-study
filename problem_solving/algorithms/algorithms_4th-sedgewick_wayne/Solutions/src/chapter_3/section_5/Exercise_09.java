package chapter_3.section_5;

import edu.princeton.cs.algs4.StdOut;
import utils.TestUtils;

/*
 * Task 3.5.9
 * 
 * Modify BST to key duplicate keys in the tree. Return _any_ value associated with the given key
 * for get(), and remove _all_ nodes in the tree that have keys equal to the given key for delete().
 */
public class Exercise_09 {

    public static class DupBST<Key extends Comparable<Key>, Value> {
        private static final int CMP_TREE_KEY = 0; 
        private class Tree {
            Key key;
            Value val;
            Tree left, right;
            public Tree(Key k, Value v) {
                key = k;
                val =v ;
            }
        }
        private Tree root;
        
        public Value get(Key key) {
            Tree tree = root;
            while (tree != null) {
                int cmp_key = key.compareTo(tree.key);
                if (cmp_key < CMP_TREE_KEY) { tree = tree.left; }
                else if (CMP_TREE_KEY < cmp_key) { tree = tree.right; }
                else { return tree.val; }
            }
            return null;
        }
        
        public void put(Key k, Value v) { root = put(root, k, v); }
        private Tree put(Tree tree, Key key, Value val) {
            if (tree == null) { return new Tree(key, val); }
            int cmp_key = key.compareTo(tree.key);
            if (cmp_key < CMP_TREE_KEY) { tree.left = put(tree.left, key, val); }
            else if (CMP_TREE_KEY < cmp_key) { tree.right = put(tree.right, key, val); }
            else { tree.left = put(tree.left, key, val); }
            return tree;
        }
        
        public void delete(Key k) { root = delete(root, k); }
        private Tree delete(Tree tree, Key key) {
            if (tree == null) { return null; }
            int cmp_key = key.compareTo(tree.key);
            if (cmp_key < CMP_TREE_KEY) { tree.left = delete(tree.left, key); }
            else if (CMP_TREE_KEY < cmp_key) { tree.right = delete(tree.right, key); }
            else {
                if (tree.left == null) { return tree.right; }
                if (tree.right == null) { return tree.left; }
                
                Tree new_tree_root = min(tree.right);
                new_tree_root.right = deleteMin(tree.right);
                new_tree_root.left = tree.left;
                tree = new_tree_root;
            }
            return tree;
        }
        private Tree min(Tree tree) {
            if (tree == null) { return null; }
            return tree.left == null ? tree : min(tree.left);
        }
        private Tree deleteMin(Tree tree) {
            if (tree == null) { return null; }
            if (tree.left == null) { return tree.right; }
            tree.left = deleteMin(tree.left);
            return tree;
        }
        
        public boolean contains(Key key) {
            Tree tree = root;
            while (tree != null) {
                int cmp_key = key.compareTo(tree.key);
                if (cmp_key < CMP_TREE_KEY) { tree = tree.left; }
                else if (CMP_TREE_KEY < cmp_key) { tree = tree.right; }
                else { return true; }
            }
            return false;
        }
    }
    
    private static final int ELEM_NM = 5;
    private static final int DUP_NM = 3;
    public static void main(String[] args) {
        DupBST<Integer, Double> bst = new DupBST<>();
        for (int i = 0; i < ELEM_NM; ++i) {
            for (int j = 1; j < 1 + DUP_NM; ++j) {
                bst.put(i, 1.0 * i / j);
            }
        }
        for (int i = 0; i < 2*ELEM_NM; ++i) {
            TestUtils.fail_if(i < ELEM_NM && !bst.contains(i), "[+] Contains failed.");
            TestUtils.fail_if(ELEM_NM < i && bst.contains(i), "[-] Contains failed.");
        }
        for (int i = 0; i < ELEM_NM; ++i) {
            for (int j = 1; j < 1 + DUP_NM; ++j) {
                bst.delete(i);
                if (j != DUP_NM) {
                  TestUtils.fail_if(!bst.contains(i), "[del-+] Contains failed.");
                }
            }
            TestUtils.fail_if(bst.contains(i), "[del-] Contains failed.");
        }
        StdOut.println("OK");
    }
}
