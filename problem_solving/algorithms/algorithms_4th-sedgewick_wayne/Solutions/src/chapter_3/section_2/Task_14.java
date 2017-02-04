package chapter_3.section_2;

import java.util.LinkedList;
import java.util.Queue;

import chapter_3.section_1.Task_04.Event;
import chapter_3.section_2.Task_12.BST;
import chapter_3.section_2.common.BstTest;
import edu.princeton.cs.algs4.Stack;

/*
 * Task 3.2.14
 * 
 * Give non-recursive implementations of min(), max(), floor(), ceiling(), rank(), select(),
 * and keys(). 
 */
public class Task_14 {
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
            Stack<Node> todo = new Stack<>();
            
            Node tree = root;
            while (tree != null || !todo.isEmpty()) {
                if (tree != null) {
                    todo.push(tree);
                    tree = tree.left;
                } else {
                    Node n = todo.pop();
                    itrs.add(n.k);
                    tree = n.right;
                }
            }
            
            return itrs;
        }

        @Override public int size() { return size(root); }
        private int size(Node tree) { return tree == null ? 0 : tree.sz; }

        @Override public Key min() {
            if (root == null) { return null; }
            
            Node tree = root;
            while (tree.left != null) { tree = tree.left; }
            return tree.k;
        }
        
        @Override public Key max() {
            if (root == null) { return null; }
            
            Node tree = root;
            while (tree.right != null) { tree = tree.right; }
            return tree.k;
        }
        
        @Override public Key floor(Key key) {
            if (root == null) { return null; }
            
            Key floor = null;
            Node tree = root;
            while (tree != null) {
                int cmp_key = key.compareTo(tree.k);
                if (cmp_key < CMP_TREE_KEY) {
                    tree = tree.left;
                } else if (CMP_TREE_KEY < cmp_key) {
                    floor = tree.k;
                    tree = tree.right;
                } else {
                    return tree.k;
                }
            }
            return floor;
        }
        
        @Override public Key ceiling(Key key) {
            if (root == null) { return null; }
            
            Key ceiling = null;
            Node tree = root;
            while (tree != null) {
                int cmp_key = key.compareTo(tree.k);
                if (cmp_key < CMP_TREE_KEY) {
                    ceiling = tree.k;
                    tree = tree.left;
                } else if (CMP_TREE_KEY < cmp_key) {
                    tree = tree.right;
                } else {
                    return tree.k;
                }
            }
            return ceiling;
        }
        
        @Override public Key select(int k) {
            Node tree = root;
            while (tree != null) {
                int prior_nm = size(tree.left);
                if (prior_nm == k) {
                    return tree.k;
                } else if (k < prior_nm) {
                    tree = tree.left;
                } else {
                    k -= 1 + prior_nm;
                    tree = tree.right;
                }
            }
            
            return null;
        }
        @Override public int rank(Key key) {
            Node tree = root;
            int rank = 0;
            while (tree != null) {
                int cmp_key = key.compareTo(tree.k);
                if (cmp_key < CMP_TREE_KEY) {
                    tree = tree.left;
                } else if (CMP_TREE_KEY < cmp_key) {
                    rank += size(tree.left) + 1;
                    tree = tree.right;
                } else {
                    return rank;
                }
            }
            return rank;
        }
        
        @Override public void deleteMin() { }
        @Override public void deleteMax() { }
        @Override public void delete(Key key) { }
        @Override public Iterable<Key> keys(Key lo, Key hi) { return null; }
    }
    
    public static void main(String[] args) {
        BST<Event, String> bst = new BST<>();
        BstTest.addWithTestData(bst);
        BstTest.testBstReduced(bst);
    }
}
