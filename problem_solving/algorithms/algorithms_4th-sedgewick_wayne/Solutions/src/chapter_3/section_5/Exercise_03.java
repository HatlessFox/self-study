package chapter_3.section_5;

import chapter_3.section_5.common.TestSet;

/*
 * Task 3.5.3
 * 
 * Develop a SET implementation BinarySearchSET by starting with code for BinarySearchST and
 * eliminating all of the code involving values.
 */
public class Exercise_03 {

    public static class BinarySearctSET<Key extends Comparable<Key>> implements TestSet.SET<Key>{
        private static final int CMP_TREE_KEY = 0;
        
        private class TreeNode {
            Key key;
            TreeNode left, right;
            public TreeNode(Key key) { this.key = key; }
        }

        private TreeNode root;
        
        @Override
        public void add(Key key) { root = add(root, key); }
        private TreeNode add(TreeNode tree, Key key) {
            if (tree == null) { return new TreeNode(key); }
            int cmp_key = key.compareTo(tree.key);
            if (cmp_key < CMP_TREE_KEY) { tree.left = add(tree.left, key); }
            else if (CMP_TREE_KEY < cmp_key) { tree.right = add(tree.right, key); }
            return tree;
        }

        @Override
        public void delete(Key key) { root = delete(root, key); }
        private TreeNode delete(TreeNode tree, Key key) {
            if (tree == null) { return null; }
            int cmp_key = key.compareTo(tree.key);
            if (cmp_key < CMP_TREE_KEY) { return delete(tree.left, key); }
            else if (CMP_TREE_KEY < cmp_key) { return delete(tree.right, key); }
            else {
                if (tree.left == null) { return tree.right; }
                if (tree.right == null) { return tree.left; }
                
                TreeNode new_local_root = min(tree);
                new_local_root.right = deleteMin(tree);
                new_local_root.left = tree.left;
                tree = new_local_root;
            }
            return tree;
        }
        private TreeNode min(TreeNode tree) {
            if (tree == null) { return null; }
            return tree.left == null ? tree : min(tree.left);
        }
        private TreeNode deleteMin(TreeNode tree) {
            if (tree == null) { return null; }
            if (tree.left == null) { return tree.right; }
            tree.right = deleteMin(tree.left);
            return tree;
        }
        
        @Override
        public boolean contains(Key key) { return contains(root, key); }
        private boolean contains(TreeNode tree, Key key) {
            if (tree == null) { return false; }
            int cmp_key = key.compareTo(tree.key);
            if (cmp_key < CMP_TREE_KEY) { return contains(tree.left, key); }
            else if (CMP_TREE_KEY < cmp_key) { return contains(tree.right, key); }
            return true;
        }

        @Override
        public boolean isEmpty() { return root == null; }

        @Override
        public int size() { return size(root); }
        private int size(TreeNode tree) {
            if (tree == null) { return 0; }
            return size(tree.left) + 1 + size(tree.right);
        }
    }
    
    private static final int ELEM_NM = 8;
    public static void main(String[] args) {
        BinarySearctSET<Integer> set = new BinarySearctSET<>();
        TestSet.testSet(set, ELEM_NM);
    }
}
