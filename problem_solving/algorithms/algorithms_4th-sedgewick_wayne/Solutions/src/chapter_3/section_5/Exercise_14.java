package chapter_3.section_5;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 3.5.14
 * 
 * Develop and test a static method invert() that takes as argument an ST<String, Bag<String>> and
 * produces as return value the inverse of the given symbol table (a symbol table of the same type).
 */
public class Exercise_14 {

    public static ST<String, Bag<String>> invert(ST<String, Bag<String>> st) {
        ST<String, Bag<String>> inv_st = new ST<>();
        for (String key : st.keys()) {
            for (String val : st.get(key)) {
                if (!inv_st.contains(val)) { inv_st.put(val, new Bag<String>()); }
                inv_st.get(val).add(key);
            }
        }
        return inv_st;
    }
    
    private static final String[] SUFFIXES = {"", "A", "B"};
    private static Bag<String> createBag(String prefix) {
        Bag<String> bag = new Bag<>();
        for (String s : SUFFIXES) {
            bag.add(prefix + s);
        }
        return bag;
    }
    
    private static ST<String, Bag<String>> createST() {
        ST<String, Bag<String>> st = new ST<>();
        st.put("A", createBag("A"));
        st.put("AA", createBag("AA"));
        return st;
    }
    
    public static void main(String[] args) {
        ST<String, Bag<String>> inv_st = invert(createST());
        for (String key : inv_st.keys()) {
            StdOut.printf("%s: ", key);
            for (String val : inv_st.get(key)) {
                StdOut.printf("%s; ", val);
            }
            StdOut.println();
        }
    }
}
