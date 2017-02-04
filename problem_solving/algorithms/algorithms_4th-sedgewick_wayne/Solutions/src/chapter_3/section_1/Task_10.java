package chapter_3.section_1;

import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 3.1.10
 * 
 * Give a trace of the process of inserting the keys EASYQUESTION into an initially empty table
 * using SequentialSearchST. How many compares are involved?
 */
public class Task_10 {

    public static class SequentialSearchST<Key, Value> {
        private Node head = new Node(null, null, null);
        
        private class Node {
            Key k;
            Value v;
            Node next;
            public Node(Key k, Value v, Node next) {
                this.k = k;
                this.v = v;
                this.next = next;
            }
            
            @Override public String toString() { return String.format("%s|%s", k, v); }
        }
        
        public void put(Key k, Value v) {
            StringBuilder log = new StringBuilder();
            int cmp_nm = 0;
            Node ptr = head;
            while ((ptr = ptr.next) != null) {
                cmp_nm++;
                if (ptr.k.equals(k)) {
                    ptr.v = v;
                    log.append("[" + ptr + "] ");
                    log.insert(0, "Cmps:" + cmp_nm + "; ");
                    StdOut.println(log);
                    return;
                }
                log.append("<" + ptr + "> ");
            }
            head.next = new Node(k, v, head.next);
            log.insert(0, "+" + head.next + "+ ");
            log.insert(0, "Cmps:" + cmp_nm + "; ");
            StdOut.println(log);
        }
    }
    
    public static final String TEST_STRING = "EASYQUESTION";
    
    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        for (int i = 0; i < TEST_STRING.length(); ++i) {
            char new_key = TEST_STRING.charAt(i);
            StdOut.printf("=Add %c= ", new_key);
            st.put(Character.toString(new_key), i);
        }
    }
    
}
