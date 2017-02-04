package chapter_3.section_2;

import chapter_3.section_2.common.BST;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 3.2.15
 * 
 * Give the sequences of nodes examined when the methods in BST are used to compute each of
 * the following quantities for the tree drawn at right. (page 418).
 */
public class Task_15 {

    public static void initBst(BST<String, String> bst) {
        bst.put("E", "E");
        
        bst.put("D", "D");
        bst.put("A", "A");
        
        bst.put("Q", "Q");
        bst.put("J", "J");
        bst.put("M", "M");
        
        bst.put("T", "T");
        bst.put("S", "S");
    }
    
    public static void main(String[] args) {
        BST<String, String> bst = new BST<>();
        initBst(bst);
        
        StdOut.printf("floor(\"Q\"): %s\n", bst.floor("Q"));
        StdOut.printf("select(5): %s\n", bst.select(5));
        StdOut.printf("ceiling(\"Q\"): %s\n", bst.ceiling("Q"));
        
        StdOut.printf("rank(\"J\"): %s\n", bst.rank("J"));
        StdOut.printf("size(\"D\", \"T\"): %s\n", bst.size("D", "T"));
        StdOut.printf("== keys(\"D\", \"T\") ==\n");
        for (String k : bst.keys("D", "T")) {
            StdOut.printf("%s - %s\n", k, bst.get(k));
        }
    }
}
