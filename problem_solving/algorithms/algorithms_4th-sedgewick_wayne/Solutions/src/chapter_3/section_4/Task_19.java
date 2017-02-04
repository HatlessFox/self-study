package chapter_3.section_4;

import java.util.LinkedList;
import java.util.Queue;

import chapter_3.section_4.Task_02.SeparateChainingHashST;
import chapter_3.section_4.Task_10.LinearProbingHashST;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 3.4.19
 * 
 * Implement keys for SeparateChainingHashST and LinearProbinigHashST. 
 */
public class Task_19 {

    public static class KeySChHashST<Key, Value> extends SeparateChainingHashST<Key, Value> {
        public KeySChHashST() { super(15); }

        @Override
        public Iterable<Key> keys() {
            Queue<Key> keys = new LinkedList<>();
            for (Node ptr : chains) {
                while (ptr != null) {
                    keys.add(ptr.k);
                    ptr = ptr.next;
                }
            }
            return keys;
        }
    }
    
    public static class KeyLPrHashST<Key, Value> extends LinearProbingHashST<Key, Value> {
        public KeyLPrHashST() { super(15); }

        @Override
        public Iterable<Key> keys() {
            Queue<Key> itrbl_keys = new LinkedList<>();
            for (int i = 0; i < keys.length; ++i) {
                if (keys[i] == null) { continue; }
                itrbl_keys.add(keys[i]);
            }
            return itrbl_keys;
        }
    }
    
    public static final char[] CHRS = {'E', 'A', 'S', 'Y', 'Q', 'U', 'T', 'I', 'O', 'N'};
    public static void main(String[] args) {
        KeySChHashST<Character, Character> sch = new KeySChHashST<>();
        KeyLPrHashST<Character, Character> lpr = new KeyLPrHashST<>();
        
        for (char ch : CHRS) {
            sch.put(ch, ch);
            lpr.put(ch, ch);
        }
        
        StdOut.println("== Separate Chaining Keys ==");
        for (Character k : sch.keys()) { StdOut.printf("%c ", k); }
        StdOut.println();
        
        StdOut.println("== Linear Probing Keys ==");
        for (Character k : lpr.keys()) { StdOut.printf("%c ", k); }
        StdOut.println();
    }
}
