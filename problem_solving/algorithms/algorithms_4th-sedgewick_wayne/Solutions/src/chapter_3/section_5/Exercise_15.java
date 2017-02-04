package chapter_3.section_5;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 3.5.15
 * 
 * Write a program that takes a string on standard input and a integer k as command-line argument
 * and puts on standard output a sorted list of the k-grams (substrings of length k) found in
 * the string, each followed by its index in the string.
 */
public class Exercise_15 {

    public static void main(String[] args) {
        StdOut.print("Enter string: ");
        String data = StdIn.readString();
        StdOut.print("Enter k: ");
        int k = StdIn.readInt();
        
        RedBlackBST<String, Integer> bst = new RedBlackBST<>();
        for (int i = 0; i < data.length() - k + 1; ++i) {
            bst.put(data.substring(i, i + k), i);
        }
        for (String key : bst.keys()) {
            StdOut.printf("%s %d\n", key, bst.get(key));
        }
    }
}
